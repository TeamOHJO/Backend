package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event.ID;

@Repository
public interface AccommodationRoomImageRepository extends JpaRepository<AccommodationRoomImages, ID> {


  List<AccommodationRoomImages> findByRoomId(Long roomId);

}
