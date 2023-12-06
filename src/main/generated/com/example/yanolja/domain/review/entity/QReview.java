package com.example.yanolja.domain.review.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QReview is a Querydsl query type for Review
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QReview extends EntityPathBase<Review> {

    private static final long serialVersionUID = -949400942L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QReview review = new QReview("review");

    public final com.example.yanolja.global.entity.QBaseTimeEntity _super = new com.example.yanolja.global.entity.QBaseTimeEntity(this);

    public final com.example.yanolja.domain.accommodation.entity.QAccommodation accommodation;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final com.example.yanolja.domain.reservation.entity.QReservations reservation;

    public final StringPath reviewContent = createString("reviewContent");

    public final NumberPath<Long> reviewId = createNumber("reviewId", Long.class);

    public final ListPath<ReviewImages, QReviewImages> reviewImages = this.<ReviewImages, QReviewImages>createList("reviewImages", ReviewImages.class, QReviewImages.class, PathInits.DIRECT2);

    public final com.example.yanolja.domain.accommodation.entity.QAccommodationRooms room;

    public final NumberPath<Integer> star = createNumber("star", Integer.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public final com.example.yanolja.domain.user.entity.QUser user;

    public QReview(String variable) {
        this(Review.class, forVariable(variable), INITS);
    }

    public QReview(Path<? extends Review> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QReview(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QReview(PathMetadata metadata, PathInits inits) {
        this(Review.class, metadata, inits);
    }

    public QReview(Class<? extends Review> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accommodation = inits.isInitialized("accommodation") ? new com.example.yanolja.domain.accommodation.entity.QAccommodation(forProperty("accommodation")) : null;
        this.reservation = inits.isInitialized("reservation") ? new com.example.yanolja.domain.reservation.entity.QReservations(forProperty("reservation"), inits.get("reservation")) : null;
        this.room = inits.isInitialized("room") ? new com.example.yanolja.domain.accommodation.entity.QAccommodationRooms(forProperty("room"), inits.get("room")) : null;
        this.user = inits.isInitialized("user") ? new com.example.yanolja.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

