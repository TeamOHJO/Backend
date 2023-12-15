
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
public class AccommodationImages {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;

    @ManyToOne
    @JoinColumn(name = "accommodationId", nullable = false)
    private Accommodation accommodation;

    @Column(name = "image", nullable = false)
    private String image;


    @Builder
    public AccommodationImages(Long imageId, Accommodation accommodation, String image) {
        this.imageId = imageId;
        this.accommodation = accommodation;
        this.image = image;
    }
}
