package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event.ID;

@Repository
public interface AccommodationImageRepository extends JpaRepository<AccommodationImages, ID> {

  List<AccommodationImages> findByAccommodation_AccommodationId(Long accommodationId);

}
