
package com.example.yanolja.domain.accommodation.entity;

import com.example.yanolja.global.entity.BaseTimeEntity;
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
public class AccommodationRooms extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomId")
    private Long roomId;

    @ManyToOne
    @JoinColumn(name = "accommodationId", nullable = false)
    private Accommodation accommodation;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "discountPercentage", nullable = false)
    private int discountPercentage;

    @Column(name = "checkinExplanation", nullable = false)
    private String checkinExplanation;

    @Column(name = "minCapacity", nullable = false)
    private int minCapacity;

    @Column(name = "maxCapacity", nullable = false)
    private int maxCapacity;

    @Column(name = "tag")
    private String tag;

    @Column(name = "explanation", nullable = false)
    private String explanation;

    @Column(name = "serviceInfo")
    private String serviceInfo;


    @Builder
    public AccommodationRooms(Long roomId, Accommodation accommodation, String name, int price,
        int discountPercentage, String checkinExplanation, int minCapacity, int maxCapacity,
        String tag,
        String explanation, String serviceInfo) {
        this.roomId = roomId;
        this.accommodation = accommodation;
        this.name = name;
        this.price = price;
        this.discountPercentage = discountPercentage;
        this.checkinExplanation = checkinExplanation;
        this.minCapacity = minCapacity;
        this.maxCapacity = maxCapacity;
        this.tag = tag;
        this.explanation = explanation;
        this.serviceInfo = serviceInfo;
    }
}
