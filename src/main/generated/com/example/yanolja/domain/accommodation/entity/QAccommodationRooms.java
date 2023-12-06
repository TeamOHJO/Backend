package com.example.yanolja.domain.accommodation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccommodationRooms is a Querydsl query type for AccommodationRooms
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccommodationRooms extends EntityPathBase<AccommodationRooms> {

    private static final long serialVersionUID = -55709616L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccommodationRooms accommodationRooms = new QAccommodationRooms("accommodationRooms");

    public final com.example.yanolja.global.entity.QBaseTimeEntity _super = new com.example.yanolja.global.entity.QBaseTimeEntity(this);

    public final QAccommodation accommodation;

    public final StringPath checkinExplanation = createString("checkinExplanation");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final NumberPath<Integer> discountPercentage = createNumber("discountPercentage", Integer.class);

    public final StringPath explanation = createString("explanation");

    public final NumberPath<Integer> maxCapacity = createNumber("maxCapacity", Integer.class);

    public final NumberPath<Integer> minCapacity = createNumber("minCapacity", Integer.class);

    public final StringPath name = createString("name");

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Long> roomId = createNumber("roomId", Long.class);

    public final StringPath serviceInfo = createString("serviceInfo");

    public final StringPath tag = createString("tag");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAccommodationRooms(String variable) {
        this(AccommodationRooms.class, forVariable(variable), INITS);
    }

    public QAccommodationRooms(Path<? extends AccommodationRooms> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccommodationRooms(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccommodationRooms(PathMetadata metadata, PathInits inits) {
        this(AccommodationRooms.class, metadata, inits);
    }

    public QAccommodationRooms(Class<? extends AccommodationRooms> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accommodation = inits.isInitialized("accommodation") ? new QAccommodation(forProperty("accommodation")) : null;
    }

}

