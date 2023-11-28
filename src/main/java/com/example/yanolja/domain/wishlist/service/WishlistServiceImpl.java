package com.example.yanolja.domain.wishlist.service;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.domain.wishlist.dto.WishlistDTO;
import com.example.yanolja.domain.user.exception.UserNotFoundException;
import com.example.yanolja.global.exception.ErrorCode;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WishlistServiceImpl implements WishlistService {

    private final AccommodationLikesRepository accommodationLikesRepository;
    private final AccommodationImageRepository accommodationImageRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final UserRepository userRepository;

    public WishlistServiceImpl(AccommodationLikesRepository accommodationLikesRepository,
        AccommodationImageRepository accommodationImageRepository,
        AccommodationRoomRepository accommodationRoomRepository,
        UserRepository userRepository) {
        this.accommodationLikesRepository = accommodationLikesRepository;
        this.accommodationImageRepository = accommodationImageRepository;
        this.accommodationRoomRepository = accommodationRoomRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<WishlistDTO> getUserWishlist(Long userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND));

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

        List<AccommodationImages> images = accommodationImageRepository
            .findByAccommodation_AccommodationId(accommodation.getAccommodationId());
        // 이미지 URL 문자열을 쉼표로 분리하고 첫 번째 URL 선택
        String imageUrl = images.stream()
            .flatMap(img -> Arrays.stream(img.getImage().split(",\\s*")))
            .findFirst()
            .orElse(null);

        int lowestPrice = accommodationRoomRepository.selectMinPrice(
            accommodation.getAccommodationId());

        return new WishlistDTO(
            imageUrl,
            accommodation.getAccommodationName(),
            accommodation.getCategory().getCategory(),
            like.getIsLike(),
            accommodation.getLocation(),
            lowestPrice
        );
    }

}