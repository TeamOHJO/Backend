package com.example.yanolja.domain.basket.controller;

import com.example.yanolja.domain.basket.dto.CreateBasketRequest;
import com.example.yanolja.domain.basket.dto.CreateBasketResponse;
import com.example.yanolja.domain.basket.dto.GetBasketResponse;
import com.example.yanolja.domain.basket.service.BasketService;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
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
    @PostMapping("/rooms/{roomsId}")
    public ResponseEntity<ResponseDTO<CreateBasketResponse>> addBasket(
        @PathVariable long roomsId,
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @Valid @RequestBody CreateBasketRequest createBasketRequest) {
        User user = principalDetails.getUser();

        ResponseDTO<CreateBasketResponse> response = basketService.addBasket(
            createBasketRequest, user, roomsId);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //장바구니 조회
    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<GetBasketResponse>>> getBasket(
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        ResponseDTO<List<GetBasketResponse>> response = basketService.getBasket(user);
        return ResponseEntity.status(response.getCode()).body(response);
    }

    //장바구니 삭제
    @DeleteMapping("/{basketId}")
    public ResponseEntity<ResponseDTO<Void>> deleteBasket(
        @PathVariable long basketId,
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        User user = principalDetails.getUser();

        ResponseDTO<Void> response = basketService.deleteBasket(user, basketId);
        return ResponseEntity.status(response.getCode()).body(response);
    }
}
