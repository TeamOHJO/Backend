package com.example.yanolja.domain.basket.service;


import com.example.yanolja.domain.basket.dto.CreateBasketRequest;
import com.example.yanolja.domain.basket.dto.CreateBasketResponse;
import com.example.yanolja.domain.basket.dto.GetBasketResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;

public interface BasketService {

    ResponseDTO<CreateBasketResponse> addBasket(CreateBasketRequest createBasketRequest, User user,
        long roomsId);

    ResponseDTO<List<GetBasketResponse>> getBasket(User user);

    ResponseDTO<Void> deleteBasket(User user, long basketId);
}
