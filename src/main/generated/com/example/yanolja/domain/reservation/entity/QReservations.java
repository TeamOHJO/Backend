package com.example.yanolja.domain.reservation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReservations is a Querydsl query type for Reservations
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReservations extends EntityPathBase<Reservations> {

    private static final long serialVersionUID = -1425169045L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReservations reservations = new QReservations("reservations");

    public final com.example.yanolja.global.entity.QBaseTimeEntity _super = new com.example.yanolja.global.entity.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final DatePath<java.time.LocalDate> endDate = createDate("endDate", java.time.LocalDate.class);

    public final NumberPath<Integer> numberOfPerson = createNumber("numberOfPerson", Integer.class);

    public final BooleanPath paymentCompleted = createBoolean("paymentCompleted");

    public final NumberPath<Long> reservationId = createNumber("reservationId", Long.class);

    public final com.example.yanolja.domain.accommodation.entity.QAccommodationRooms room;

    public final DatePath<java.time.LocalDate> startDate = createDate("startDate", java.time.LocalDate.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.example.yanolja.domain.user.entity.QUser user;

    public QReservations(String variable) {
        this(Reservations.class, forVariable(variable), INITS);
    }

    public QReservations(Path<? extends Reservations> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReservations(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReservations(PathMetadata metadata, PathInits inits) {
        this(Reservations.class, metadata, inits);
    }

    public QReservations(Class<? extends Reservations> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.room = inits.isInitialized("room") ? new com.example.yanolja.domain.accommodation.entity.QAccommodationRooms(forProperty("room"), inits.get("room")) : null;
        this.user = inits.isInitialized("user") ? new com.example.yanolja.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

