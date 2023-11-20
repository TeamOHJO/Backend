
package com.example.yanolja.domain.accommodation.entity;

import lombok.Getter;

@Getter
public enum AccommodationCategory {
    HOTEL("호텔"),
    HOTEL2("호텔2");
    private final String category;

    AccommodationCategory(String category) {
        this.category = category;
    }
}

