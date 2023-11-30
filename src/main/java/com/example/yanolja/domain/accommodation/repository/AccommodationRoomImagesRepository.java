package com.example.yanolja.domain.accommodation.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccommodationRoomImagesRepository extends JpaRepository<AccommodationRoomImages, Long> {

    List<AccommodationRoomImages> findAllByAccommodationRoomsRoomId(Long roomId);
    AccommodationRoomImages findFirstByAccommodationRoomsRoomId(long roomId);

    List<AccommodationRoomImages> findByAccommodationRooms_Accommodation_AccommodationId(Long accommodationId);     // 위시리스트에서 대표 이미지 찾을 때 필요.
}
