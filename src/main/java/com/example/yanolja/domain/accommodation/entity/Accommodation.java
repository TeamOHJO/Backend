
package com.example.yanolja.domain.accommodation.entity;

import com.example.yanolja.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
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

    @Column(name = "accommodationName", nullable = false, length = 50)
    private String accommodationName;

    @Column(name = "location", nullable = false, length = 300)
    private String location;

    @Column(name = "tag", length = 30)
    private String tag;

    @Column(name = "isDomestic", nullable = false)
    private boolean isDomestic;

    @Column(name = "explanation", nullable = false, length = 1000)
    private String explanation;

    @Column(name = "cancelInfo", nullable = false , length = 1000)
    private String cancelInfo;

    @Column(name = "useGuide", nullable = false, length = 1000)
    private String useGuide;

    @Column(name = "reservationNotice", nullable = false, length = 1000)
    private String reservationNotice;

    @Column(name = "serviceInfo", length = 100)
    private String serviceInfo;

    @OneToMany(mappedBy = "accommodation")
    private List<AccommodationRooms> roomlist = new ArrayList<>();

    @Builder
    public Accommodation(Long accommodationId, AccommodationCategory category,
        String accommodationName,
        String location, String tag, boolean isDomestic, String explanation, String cancelInfo,
        String useGuide, String reservationNotice, String serviceInfo,
        List<AccommodationRooms> roomlist) {
        this.accommodationId = accommodationId;
        this.category = category;
        this.accommodationName = accommodationName;
        this.location = location;
        this.tag = tag;
        this.isDomestic = isDomestic;
        this.explanation = explanation;
        this.cancelInfo = cancelInfo;
        this.useGuide = useGuide;
        this.reservationNotice = reservationNotice;
        this.serviceInfo = serviceInfo;
        this.roomlist = roomlist;
    }
}
