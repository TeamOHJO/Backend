package com.example.yanolja.domain.basket.repository;

import com.example.yanolja.domain.basket.entity.Basket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BasketRepository
    extends JpaRepository<Basket, Long> {

}
