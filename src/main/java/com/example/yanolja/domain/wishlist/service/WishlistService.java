package com.example.yanolja.domain.wishlist.service;

import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.wishlist.dto.WishlistDTO;
import java.util.List;

public interface WishlistService {

    List<WishlistDTO> getUserWishlist(User user);

}
