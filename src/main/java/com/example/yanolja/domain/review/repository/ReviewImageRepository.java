package com.example.yanolja.domain.review.repository;

import com.example.yanolja.domain.review.entity.ReviewImages;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImageRepository extends JpaRepository<ReviewImages, Long> {

    List<ReviewImages> findAllByReviewReviewId(Long reviewId);

}
