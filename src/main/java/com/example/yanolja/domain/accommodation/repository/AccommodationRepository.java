package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{


    List<Accommodation> findByCategory(AccommodationCategory category);
    List<Accommodation> findByIsDomestic(boolean isDomestic);
    List<Accommodation> findByCategoryAndIsDomestic(AccommodationCategory category, boolean isDomestic);
}
