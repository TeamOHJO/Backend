package com.example.yanolja.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewCreateRequest {

    @NotNull(message = "User Id는 null이 될 수 없습니다.")
    private Long userId;

    @NotNull(message = "Reservation Id는 null이 될 수 없습니다.")
    private Long reservationId;

    @NotBlank(message = "리뷰 내용에 공백이 있으면 안됩니다.")
    @Size(max = 500, message = "리뷰 내용은 500자를 넘으면 안됩니다.")
    private String reviewContent;

    private String image;

    @Min(1)
    @Max(5)
    private int star;

}
