package com.example.yanolja.domain.accommodationLikes.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccommodationLikes is a Querydsl query type for AccommodationLikes
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccommodationLikes extends EntityPathBase<AccommodationLikes> {

    private static final long serialVersionUID = -717912074L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccommodationLikes accommodationLikes = new QAccommodationLikes("accommodationLikes");

    public final com.example.yanolja.domain.accommodation.entity.QAccommodation accommodation;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isLike = createBoolean("isLike");

    public final com.example.yanolja.domain.user.entity.QUser user;

    public QAccommodationLikes(String variable) {
        this(AccommodationLikes.class, forVariable(variable), INITS);
    }

    public QAccommodationLikes(Path<? extends AccommodationLikes> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccommodationLikes(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccommodationLikes(PathMetadata metadata, PathInits inits) {
        this(AccommodationLikes.class, metadata, inits);
    }

    public QAccommodationLikes(Class<? extends AccommodationLikes> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accommodation = inits.isInitialized("accommodation") ? new com.example.yanolja.domain.accommodation.entity.QAccommodation(forProperty("accommodation")) : null;
        this.user = inits.isInitialized("user") ? new com.example.yanolja.domain.user.entity.QUser(forProperty("user")) : null;
    }

}

