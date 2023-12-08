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
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private final AccommodationLikesRepository accommodationLikesRepository;
    private final AccommodationImageRepository accommodationImageRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;

    @Override
    public List<WishlistDTO> getUserWishlist(User user) {

        List<AccommodationLikes> likes = accommodationLikesRepository.findByUserAndIsLike(user,
            true);

        return likes.stream()
            .filter(like -> like.getAccommodation() != null)
            .map(like -> WishlistDTO.from(like, accommodationImageRepository, accommodationRoomRepository))
            .collect(Collectors.toList());
    }
}