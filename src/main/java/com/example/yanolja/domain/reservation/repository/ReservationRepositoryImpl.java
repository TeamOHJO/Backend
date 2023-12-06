package com.example.yanolja.domain.reservation.repository;

import com.example.yanolja.domain.reservation.entity.QReservations;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.Optional;

public class ReservationRepositoryImpl implements ReservationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ReservationRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Reservations> findConflictingReservations(Long roomId, LocalDate startDate,
        LocalDate endDate) {
        QReservations r = QReservations.reservations;

        BooleanExpression conflictingCondition = r.room.roomId.eq(roomId)
            .and(r.paymentCompleted.eq(true))
            .and(r.deletedAt.isNull())
            .and(
                r.endDate.goe(startDate).and(r.startDate.loe(endDate))
                    .or(r.startDate.loe(startDate).and(r.endDate.goe(endDate)))
                    .or(r.startDate.goe(startDate).and(r.endDate.loe(endDate)))
            );

        return Optional.ofNullable(queryFactory.selectFrom(r)
            .where(conflictingCondition)
            .fetchFirst());
    }
}