package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>{

    List<Accommodation> findByNameContains(String accommodationName);
    List<Accommodation> findByCategoryIn(String category);
    List<Accommodation> findByCategoryIn(List<String> categories);
    Page<Accommodation> findAll(Pageable pageable);

}
