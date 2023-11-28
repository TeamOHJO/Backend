package com.example.yanolja.domain.wishlist.service;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.exception.AccommodationNotFoundException;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
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
    private final AccommodationRepository accommodationRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final AccommodationRoomImagesRepository accommodationRoomImagesRepository;
    private final UserRepository userRepository;
    private final AccommodationService accommodationService;

    public WishlistServiceImpl(AccommodationLikesRepository accommodationLikesRepository,
        AccommodationRepository accommodationRepository,
        AccommodationRoomRepository accommodationRoomRepository,
        AccommodationRoomImagesRepository accommodationRoomImagesRepository,
        UserRepository userRepository, AccommodationService accommodationService) {
        this.accommodationLikesRepository = accommodationLikesRepository;
        this.accommodationRepository = accommodationRepository;
        this.accommodationRoomRepository = accommodationRoomRepository;
        this.accommodationRoomImagesRepository = accommodationRoomImagesRepository;
        this.userRepository = userRepository;
        this.accommodationService = accommodationService;
    }

    @Override
    public List<WishlistDTO> getUserWishlist(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));

        // 사용자가 좋아요 표시한 숙박업체 목록 조회
        List<AccommodationLikes> likes = accommodationLikesRepository.findByUserAndIsLike(user,
            true);

        return likes.stream()
            .filter(like -> like.getAccommodation() != null) // 좋아요 표시된 숙박업체가 있는지 확인!
            .map(this::convertToDTO)
            .collect(Collectors.toList());
    }

    private WishlistDTO convertToDTO(AccommodationLikes like) {
        Accommodation accommodation = like.getAccommodation(); // 좋아요 표시된 숙박업체

        List<AccommodationRoomImages> images = accommodationRoomImagesRepository
            .findByAccommodationRooms_Accommodation_AccommodationId(accommodation.getAccommodationId());
        String imageUrl = !images.isEmpty() ? images.get(0).getImage() : null;

        int lowestPrice = accommodation.getRoomlist().stream()
            .mapToInt(AccommodationRooms::getPrice)
            .min()
            .orElse(0);     // 가격정보가 없는 경우

        return new WishlistDTO(
            imageUrl,
            accommodation.getAccommodationName(),
            accommodation.getCategory().getCategory(),
            like.getIsLike(),
            accommodation.getLocation(),
            lowestPrice
        );
    }

    @Override
    public void toggleLikeStatus(Long userId, Long accommodationId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND.getMessage()));
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(AccommodationNotFoundException::new);

        AccommodationLikes like = accommodationLikesRepository.findByUser_UserIdAndAccommodation_AccommodationId(userId, accommodationId)
            .orElse(new AccommodationLikes(user, accommodation, false));  // 새로운  AccommodationLikes 객체 생성 시 '좋아요'는 기본적으로 비활성화 상태로 설정
        like.setIsLike(!like.getIsLike());  // 좋아요 상태 반전
        accommodationLikesRepository.save(like);
    }

}