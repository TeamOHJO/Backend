package com.example.yanolja.domain.accommodation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccommodation is a Querydsl query type for Accommodation
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccommodation extends EntityPathBase<Accommodation> {

    private static final long serialVersionUID = -1994248312L;

    public static final QAccommodation accommodation = new QAccommodation("accommodation");

    public final com.example.yanolja.global.entity.QBaseTimeEntity _super = new com.example.yanolja.global.entity.QBaseTimeEntity(this);

    public final NumberPath<Long> accommodationId = createNumber("accommodationId", Long.class);

    public final StringPath accommodationName = createString("accommodationName");

    public final StringPath cancelInfo = createString("cancelInfo");

    public final EnumPath<AccommodationCategory> category = createEnum("category", AccommodationCategory.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath explanation = createString("explanation");

    public final ListPath<AccommodationImages, QAccommodationImages> imagelist = this.<AccommodationImages, QAccommodationImages>createList("imagelist", AccommodationImages.class, QAccommodationImages.class, PathInits.DIRECT2);

    public final BooleanPath isDomestic = createBoolean("isDomestic");

    public final StringPath location = createString("location");

    public final StringPath reservationNotice = createString("reservationNotice");

    public final ListPath<com.example.yanolja.domain.review.entity.Review, com.example.yanolja.domain.review.entity.QReview> reviews = this.<com.example.yanolja.domain.review.entity.Review, com.example.yanolja.domain.review.entity.QReview>createList("reviews", com.example.yanolja.domain.review.entity.Review.class, com.example.yanolja.domain.review.entity.QReview.class, PathInits.DIRECT2);

    public final ListPath<AccommodationRooms, QAccommodationRooms> roomlist = this.<AccommodationRooms, QAccommodationRooms>createList("roomlist", AccommodationRooms.class, QAccommodationRooms.class, PathInits.DIRECT2);

    public final StringPath serviceInfo = createString("serviceInfo");

    public final StringPath tag = createString("tag");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final StringPath useGuide = createString("useGuide");

    public QAccommodation(String variable) {
        super(Accommodation.class, forVariable(variable));
    }

    public QAccommodation(Path<? extends Accommodation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAccommodation(PathMetadata metadata) {
        super(Accommodation.class, metadata);
    }

}

