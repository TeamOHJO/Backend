package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{

    List<Accommodation> findByCategoryIn(List<String> categories);

    List<Accommodation> findByCategory(AccommodationCategory category);


}
