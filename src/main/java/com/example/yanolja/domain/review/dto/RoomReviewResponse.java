package com.example.yanolja.domain.review.dto;

import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomReviewResponse {

    private Long reviewId;
    private Long roomId;
    private String username;
    private String name;
    private AccommodationCategory category;
    private String reviewContent;
    private String image;
    private int star;
    private LocalDateTime updatedAt;    // 리뷰 수정 시간

}
