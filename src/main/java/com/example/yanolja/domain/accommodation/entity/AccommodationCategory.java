
package com.example.yanolja.domain.accommodation.entity;

import lombok.Getter;

@Getter
public enum AccommodationCategory {
    HOTEL_RESORT("호텔/리조트"),
    HANOK("한옥"),
    PENSION_POOLVILLA("펜션/풀빌라"),
    MOTEL("모텔"),
    GUESTHOUSE("게스트하우스")
    ;
    private final String category;

    AccommodationCategory(String category) {
        this.category = category;
    }
}

