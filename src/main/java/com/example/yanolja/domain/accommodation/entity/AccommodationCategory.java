
package com.example.yanolja.domain.accommodation.entity;

import lombok.Getter;

@Getter
public enum AccommodationCategory {
    HOTEL("호텔"),
    HANOK("한옥"), ;
    private final String category;

    AccommodationCategory(String category) {
        this.category = category;
    }
}

