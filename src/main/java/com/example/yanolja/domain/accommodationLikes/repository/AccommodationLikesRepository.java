package com.example.yanolja.domain.accommodationLikes.repository;

import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationLikesRepository extends JpaRepository<AccommodationLikes, Long> {

    Optional<AccommodationLikes> findByUser_UserIdAndAccommodation_AccommodationId(Long userId,
        Long accommodationId);


}