package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.QAccommodation;
import com.example.yanolja.domain.accommodation.entity.QAccommodationRooms;
import com.example.yanolja.domain.reservation.entity.QReservations;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

public class AccommodationRepositoryImpl implements AccommodationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public AccommodationRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Page<Long> findAccommodationIds(AccommodationCategory category, boolean isDomestic,
        Pageable pageable,LocalDate startDate, LocalDate endDate,int numberOfPeople) {
        QAccommodation a = QAccommodation.accommodation;
        QAccommodationRooms ar = QAccommodationRooms.accommodationRooms;
        QReservations r = QReservations.reservations;

        BooleanExpression reservationCondition = r.startDate.after(LocalDate.now())
            .or(r.startDate.isNull());

        BooleanExpression conflictingCondition = r.deletedAt.isNotNull()
            .or(r.paymentCompleted.isFalse().or(r.paymentCompleted.isNull()))
            .or(r.endDate.before(startDate))
                .or(r.startDate.after(endDate));

        BooleanExpression capacityCondition =
            ar.minCapacity.loe(numberOfPeople).and(ar.maxCapacity.goe(numberOfPeople));

        List<Long> result = queryFactory.selectDistinct(a.accommodationId)
            .from(ar)
            .join(a).on(ar.accommodation.accommodationId.eq(a.accommodationId))
            .leftJoin(r).on(ar.roomId.eq(r.room.roomId).and(reservationCondition))
            .where(a.category.eq(category)
                .and(a.isDomestic.eq(isDomestic))
                .and(conflictingCondition)
                .and(capacityCondition))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();

        return new PageImpl<>(result, pageable, result.size());
    }
}
