package com.example.yanolja.domain.reservation.repository;

import com.example.yanolja.domain.reservation.entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository
    extends JpaRepository<Reservations, Long> {

}
