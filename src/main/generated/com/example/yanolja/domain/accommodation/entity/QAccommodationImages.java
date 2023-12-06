package com.example.yanolja.domain.accommodation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccommodationImages is a Querydsl query type for AccommodationImages
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccommodationImages extends EntityPathBase<AccommodationImages> {

    private static final long serialVersionUID = -1986930656L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccommodationImages accommodationImages = new QAccommodationImages("accommodationImages");

    public final com.example.yanolja.global.entity.QBaseTimeEntity _super = new com.example.yanolja.global.entity.QBaseTimeEntity(this);

    public final QAccommodation accommodation;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath image = createString("image");

    public final NumberPath<Long> imageId = createNumber("imageId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAccommodationImages(String variable) {
        this(AccommodationImages.class, forVariable(variable), INITS);
    }

    public QAccommodationImages(Path<? extends AccommodationImages> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccommodationImages(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccommodationImages(PathMetadata metadata, PathInits inits) {
        this(AccommodationImages.class, metadata, inits);
    }

    public QAccommodationImages(Class<? extends AccommodationImages> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accommodation = inits.isInitialized("accommodation") ? new QAccommodation(forProperty("accommodation")) : null;
    }

}

