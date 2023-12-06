package com.example.yanolja.domain.wishlist.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDTO {

    private Long accommodationId;
    private String image;
    private String name;
    private String category;
    private boolean isLiked;
    private String location;
    private int price;

    public static WishlistDTO from(AccommodationLikes like, AccommodationImageRepository accommodationImageRepository, AccommodationRoomRepository accommodationRoomRepository) {
        Accommodation accommodation = like.getAccommodation();

        List<AccommodationImages> images = accommodationImageRepository
            .findByAccommodation_AccommodationId(accommodation.getAccommodationId());
        String imageUrl = images.stream()
            .flatMap(img -> Arrays.stream(img.getImage().split(",\\s*")))
            .findFirst()
            .orElse(null);

        int lowestPrice = accommodationRoomRepository.selectMinPrice(accommodation.getAccommodationId());

        return new WishlistDTO(
            accommodation.getAccommodationId(),
            imageUrl,
            accommodation.getAccommodationName(),
            accommodation.getCategory().getCategory(),
            like.getIsLike(),
            accommodation.getLocation(),
            lowestPrice
        );
    }
}
