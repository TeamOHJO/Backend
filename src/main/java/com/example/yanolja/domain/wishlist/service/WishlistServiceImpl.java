package com.example.yanolja.domain.wishlist.service;

import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.exception.RoomNotFoundException;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomImagesRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.accommodation.service.AccommodationService;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.domain.wishlist.dto.WishlistDTO;
import com.example.yanolja.domain.user.exception.UserNotFoundException;
import com.example.yanolja.global.exception.ErrorCode;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final AccommodationLikesRepository accommodationLikesRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final AccommodationRoomImagesRepository accommodationRoomImagesRepository;
    private final UserRepository userRepository;
    private final AccommodationService accommodationService;

    public WishlistServiceImpl(AccommodationLikesRepository accommodationLikesRepository,
        AccommodationRoomRepository accommodationRoomRepository,
        AccommodationRoomImagesRepository accommodationRoomImagesRepository,
        UserRepository userRepository, AccommodationService accommodationService) {
        this.accommodationLikesRepository = accommodationLikesRepository;
        this.accommodationRoomRepository = accommodationRoomRepository;
        this.accommodationRoomImagesRepository = accommodationRoomImagesRepository;
        this.userRepository = userRepository;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<WishlistDTO> getUserWishlist(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // 사용자가 좋아요 표시한 방 목록 조회
        List<AccommodationLikes> likes = accommodationLikesRepository.findByUserAndIsLike(user,
            true);

        return likes.stream()
            .filter(like -> like.getAccommodationRoom() != null) // 좋아요 표시된 방이 있는지 확인
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private WishlistDTO convertToDTO(AccommodationLikes like) {
        AccommodationRooms room = like.getAccommodationRoom(); // 좋아요 표시된 방
        AccommodationRoomImages representativeImage = accommodationRoomImagesRepository
            .findFirstByAccommodationRoomsRoomId(room.getRoomId());

        String imageUrl = representativeImage != null ? representativeImage.getImage() : null;

        return new WishlistDTO(
            imageUrl,
            room.getName(),
            room.getAccommodation().getCategory().getCategory(),
            like.getIsLike(),
            room.getAccommodation().getLocation(),
            room.getPrice()
        );
    }

    @Override
    public void toggleLikeStatus(Long userId, Long roomId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND.getMessage()));
        AccommodationRooms room = accommodationRoomRepository.findById(roomId)
            .orElseThrow(RoomNotFoundException::new);

        AccommodationLikes like = accommodationLikesRepository.findByUser_UserIdAndAccommodationRoom_RoomId(userId, roomId)
            .orElse(new AccommodationLikes(user, room.getAccommodation(), false));  // 새로운  AccommodationLikes 객체 생성 시 '좋아요'는 기본적으로 비활성화 상태로 설정
        like.setIsLike(!like.getIsLike());  // 좋아요 상태 반전
        accommodationLikesRepository.save(like);
    }

}