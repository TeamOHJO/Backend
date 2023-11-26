package com.example.yanolja.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewUpdateRequest {

    @NotBlank(message = "리뷰 내용이 공백이면 안됩니다.")
    @Size(max = 500, message = "리뷰 내용은 500자를 넘길 수 없습니다.")
    private String reviewContent;

    private String image;

    @Min(1)
    @Max(5)
    private int star;

}
