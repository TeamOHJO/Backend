package com.example.yanolja.domain.accommodation.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QAccommodationRoomImages is a Querydsl query type for AccommodationRoomImages
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QAccommodationRoomImages extends EntityPathBase<AccommodationRoomImages> {

    private static final long serialVersionUID = -1181884197L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QAccommodationRoomImages accommodationRoomImages = new QAccommodationRoomImages("accommodationRoomImages");

    public final com.example.yanolja.global.entity.QBaseTimeEntity _super = new com.example.yanolja.global.entity.QBaseTimeEntity(this);

    public final QAccommodationRooms accommodationRooms;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> deletedAt = _super.deletedAt;

    public final StringPath image = createString("image");

    public final NumberPath<Long> imageId = createNumber("imageId", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QAccommodationRoomImages(String variable) {
        this(AccommodationRoomImages.class, forVariable(variable), INITS);
    }

    public QAccommodationRoomImages(Path<? extends AccommodationRoomImages> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QAccommodationRoomImages(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QAccommodationRoomImages(PathMetadata metadata, PathInits inits) {
        this(AccommodationRoomImages.class, metadata, inits);
    }

    public QAccommodationRoomImages(Class<? extends AccommodationRoomImages> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.accommodationRooms = inits.isInitialized("accommodationRooms") ? new QAccommodationRooms(forProperty("accommodationRooms"), inits.get("accommodationRooms")) : null;
    }

}

