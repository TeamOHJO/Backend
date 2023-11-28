package com.example.yanolja.domain.wishlist.controller;

import com.example.yanolja.domain.wishlist.dto.WishlistDTO;
import com.example.yanolja.domain.wishlist.service.WishlistService;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    private final WishlistService wishlistService;

    public WishlistController(WishlistService wishlistService) {
        this.wishlistService = wishlistService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<ResponseDTO<List<WishlistDTO>>> getUserWishlist(
        @PathVariable Long userId) {
        List<WishlistDTO> wishlist = wishlistService.getUserWishlist(userId);
        return ResponseEntity.ok(ResponseDTO.res(HttpStatus.OK, wishlist));
    }

    @PostMapping("/toggle-like/{userId}/{accommodationId}")
    public ResponseEntity<ResponseDTO<String>> toggleLike(@PathVariable Long userId, @PathVariable Long accommodationId) {
        try {
            wishlistService.toggleLikeStatus(userId, accommodationId);
            ResponseDTO<String> response = ResponseDTO.<String>builder()
                .code(HttpStatus.OK.value())
                .message("좋아요 상태 조작에 성공하였습니다.")
                .data("")
                .build();
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            ResponseDTO<String> errorResponse = ResponseDTO.<String>builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .data("")
                .build();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
        }
    }


}
