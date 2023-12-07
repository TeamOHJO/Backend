package com.example.yanolja.domain.wishlist.controller;

import com.example.yanolja.domain.wishlist.dto.WishlistDTO;
import com.example.yanolja.domain.wishlist.service.WishlistService;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
@RequiredArgsConstructor
public class WishlistController {

    private final WishlistService wishlistService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<WishlistDTO>>> getUserWishlist(
        @AuthenticationPrincipal PrincipalDetails principalDetails) {

        List<WishlistDTO> wishlist = wishlistService.getUserWishlist(
            principalDetails.getUser());
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "위시리스트 조회 성공", wishlist));
    }
}
