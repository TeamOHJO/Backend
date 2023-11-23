
package com.example.yanolja.domain.accommodation.entity;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
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

    @Column(name = "tag", nullable = false)
    private String tag;

    @Column(name = "explanation", nullable = false)
    private String explanation;

    @Column(name = "serviceinfo", nullable = false)
    private String serviceInfo;


}
