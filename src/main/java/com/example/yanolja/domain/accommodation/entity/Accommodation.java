
package com.example.yanolja.domain.accommodation.entity;

import com.example.yanolja.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Accommodation extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accommodationId")
    private Long accommodationId;

    @Column(name = "category", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccommodationCategory category;

    @Column(name = "accommodationName", nullable = false)
    private String accommodationName;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "tag", nullable = false)
    private String tag;

    @Column(name = "isDomestic", nullable = false)
    private boolean isDomestic;

    @Column(name = "explanation", nullable = false)
    private String explanation;

    @Column(name = "cancelInfo", nullable = false)
    private String cancelInfo;

    @Column(name = "useGuide", nullable = false)
    private String useGuide;

    @Column(name = "reservationNotice", nullable = false)
    private String reservationNotice;
}
