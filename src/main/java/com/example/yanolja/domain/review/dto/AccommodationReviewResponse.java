package com.example.yanolja.domain.review.dto;

import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationReviewResponse {

    private Long reviewId;
    private Long roomId;
    private String username;
    private String accommodationName;
    private String roomName;
    private AccommodationCategory category;
    private String reviewContent;
    private int star;
    private List<String> images;
    private LocalDateTime updatedAt;
}
