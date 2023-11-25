package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{


    Page<Accommodation> findByCategory(AccommodationCategory category, Pageable pageable);
    Page<Accommodation> findByIsDomestic(boolean isDomestic,  Pageable pageable);
    Page<Accommodation> findByCategoryAndIsDomestic(AccommodationCategory category, boolean isDomestic,  Pageable pageable);
}
