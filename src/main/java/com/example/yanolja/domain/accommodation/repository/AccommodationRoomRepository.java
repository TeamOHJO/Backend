package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRoomRepository extends JpaRepository<AccommodationRooms, Long> {
    List<AccommodationRooms> findAllByAccommodation_AccommodationId(Long accommodationId);
}