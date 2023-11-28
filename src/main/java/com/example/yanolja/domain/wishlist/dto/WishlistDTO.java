package com.example.yanolja.domain.wishlist.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WishlistDTO {

    private String image;
    private String name;
    private String category;
    private boolean isLiked;
    private String location;
    private int price;
}
