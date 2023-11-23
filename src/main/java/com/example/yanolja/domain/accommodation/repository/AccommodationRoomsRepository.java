package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRoomsRepository extends JpaRepository<AccommodationRooms, Long> {

}
