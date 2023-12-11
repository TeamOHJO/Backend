
package com.example.yanolja.domain.accommodation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class AccommodationRoomImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private AccommodationRooms accommodationRooms;

    @Column(name = "image", nullable = false)
    private String image;


    @Builder
    public AccommodationRoomImages(Long imageId, AccommodationRooms accommodationRooms,
        String image) {
        this.imageId = imageId;
        this.accommodationRooms = accommodationRooms;
        this.image = image;
    }
}
