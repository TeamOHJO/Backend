package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AccommodationRoomRepository extends JpaRepository<AccommodationRooms, Long> {
    List<AccommodationRooms> findAllByAccommodation_AccommodationId(Long accommodationId);

    @Query("SELECT MIN(r.price) FROM AccommodationRooms r WHERE r.accommodation.accommodationId = :id")
    int selectMinPrice(@Param("id") Long id);
}