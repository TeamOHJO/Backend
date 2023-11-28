package com.example.yanolja.domain.wishlist.controller;

import com.example.yanolja.domain.wishlist.dto.WishlistDTO;
import com.example.yanolja.domain.wishlist.service.WishlistService;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import java.security.Principal;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<List<WishlistDTO>>> getUserWishlist(Principal principal) {
        if (principal == null) {
            // 타입을 ResponseDTO<List<WishlistDTO>>로 일치시키기 위해 빈 리스트를 사용.
            List<WishlistDTO> emptyList = Collections.emptyList();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(ResponseDTO.res(HttpStatus.UNAUTHORIZED, "인증 정보가 없습니다.", emptyList));
        }

        PrincipalDetails principalDetails = (PrincipalDetails) ((UsernamePasswordAuthenticationToken) principal).getPrincipal();
        Long userId = principalDetails.getUser().getId();

        List<WishlistDTO> wishlist = wishlistService.getUserWishlist(userId);
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, "위시리스트 조회 성공", wishlist));
    }

}
