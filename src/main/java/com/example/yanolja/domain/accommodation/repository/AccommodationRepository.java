package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    List<Accommodation> findByNameContains(String accommodationName);
}
