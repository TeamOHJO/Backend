package com.example.yanolja.domain.wishlist.dto;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
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

    public static WishlistDTO from(AccommodationLikes like, String imageUrl, int lowestPrice) {
        Accommodation accommodation = like.getAccommodation();
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
