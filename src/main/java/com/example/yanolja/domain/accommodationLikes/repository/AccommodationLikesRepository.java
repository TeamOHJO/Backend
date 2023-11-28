package com.example.yanolja.domain.accommodationLikes.repository;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.user.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AccommodationLikesRepository extends JpaRepository<AccommodationLikes, Long> {

    Optional<AccommodationLikes> findByUser_UserIdAndAccommodation_AccommodationId(Long userId,
        Long accommodationId);

    @Query("SELECT a FROM AccommodationLikes a WHERE a.user = :user AND a.isLike = :isLike")
    List<AccommodationLikes> findByUserAndIsLike(@Param("user") User user, @Param("isLike") boolean isLike);

    // 성능 최적화 시 변경 가능성 (여러개의 isLike 상태 처리)
//    @Transactional
//    @Modifying
//    @Query("UPDATE AccommodationLikes a SET a.isLike = :isLike WHERE a.user = :user AND a.accommodationRoom = :room")
//    void updateLikeStatus(@Param("user") User user, @Param("room") AccommodationRooms room, @Param("isLike") boolean isLike);

    Optional<AccommodationLikes> findByUser_UserIdAndAccommodationRoom_RoomId(Long userId, Long roomId);

}