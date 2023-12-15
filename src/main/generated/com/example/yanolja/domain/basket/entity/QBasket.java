package com.example.yanolja.domain.basket.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBasket is a Querydsl query type for Basket
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBasket extends EntityPathBase<Basket> {

    private static final long serialVersionUID = 1093072622L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBasket basket = new QBasket("basket");

    public final com.example.yanolja.global.entity.QBaseTimeEntity _super = new com.example.yanolja.global.entity.QBaseTimeEntity(this);

    public final NumberPath<Long> basketId = createNumber("basketId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final BooleanPath isSelected = createBoolean("isSelected");

    public final com.example.yanolja.domain.reservation.entity.QReservations reservation;

    public final com.example.yanolja.domain.accommodation.entity.QAccommodationRooms room;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.example.yanolja.domain.user.entity.QUser user;

    public QBasket(String variable) {
        this(Basket.class, forVariable(variable), INITS);
    }

    public QBasket(Path<? extends Basket> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBasket(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBasket(PathMetadata metadata, PathInits inits) {
        this(Basket.class, metadata, inits);
    }

    public QBasket(Class<? extends Basket> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.reservation = inits.isInitialized("reservation") ? new com.example.yanolja.domain.reservation.entity.QReservations(forProperty("reservation"), inits.get("reservation")) : null;
        this.room = inits.isInitialized("room") ? new com.example.yanolja.domain.accommodation.entity.QAccommodationRooms(forProperty("room"), inits.get("room")) : null;
        this.user = inits.isInitialized("user") ? new com.example.yanolja.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

