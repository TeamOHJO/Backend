package com.example.yanolja.domain.basket.controller;

import com.example.yanolja.domain.basket.dto.CreateBasketRequest;
import com.example.yanolja.domain.basket.service.BasketService;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/basket")
@RequiredArgsConstructor
public class BasketController {

    private final BasketService basketService;

    //장바구니 추가
    @PostMapping("/{roomsId}")
    public ResponseEntity<ResponseDTO<?>> addBasket(
        @PathVariable long roomsId,
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @Valid @RequestBody CreateBasketRequest createBasketRequest) {
        User user = principalDetails.getUser();

        ResponseDTO<?> response = basketService.addBasket(
            createBasketRequest, user, roomsId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //장바구니 조회
    @GetMapping("")
    public ResponseEntity<ResponseDTO<?>> getBasket(
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        ResponseDTO<?> response = basketService.getBasket(user);
        return ResponseEntity.status(response.getCode()).body(response);
    }

}
