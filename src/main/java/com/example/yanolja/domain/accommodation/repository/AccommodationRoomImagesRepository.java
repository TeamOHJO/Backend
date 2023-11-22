package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRoomImagesRepository
    extends JpaRepository<AccommodationRoomImages, Long> {

    List<AccommodationRoomImages> findAllByAccommodationRoomsRoomId(Long roomId);

}
