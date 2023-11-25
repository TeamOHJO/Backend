package com.example.yanolja.domain.basket.service;


import com.example.yanolja.domain.basket.dto.CreateBasketRequest;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.util.ResponseDTO;

public interface BasketService {

    ResponseDTO<?> addBasket(CreateBasketRequest createBasketRequest, User user,
        long roomsId);

    ResponseDTO<?> getBasket(User user);

    ResponseDTO<?> deleteBasket(User user, long basketId);
}
