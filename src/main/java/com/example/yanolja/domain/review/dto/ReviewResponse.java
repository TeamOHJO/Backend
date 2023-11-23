package com.example.yanolja.domain.review.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponse {
    private Long reviewId;
    private Long roomId;
    private Long userId;
    private String reviewContent;
    private String image;
    private int star;
    private LocalDateTime createdAt;    // 리뷰 작성 시간
    private LocalDateTime updatedAt;    // 리뷰 수정 시간
}
