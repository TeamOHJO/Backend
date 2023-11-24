package com.example.yanolja.domain.review.repository;

import com.example.yanolja.domain.review.entity.Review;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    // 방Id를 기반으로 리뷰 전체 조회


    // 숙박업체Id를 기반으로 리뷰 전체 조회
    List<Review> findByAccommodationId(Long accommodationId);

}
