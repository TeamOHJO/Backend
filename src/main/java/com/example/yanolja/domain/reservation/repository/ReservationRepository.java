package com.example.yanolja.domain.reservation.repository;

import com.example.yanolja.domain.reservation.entity.Reservations;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository
    extends JpaRepository<Reservations, Long> {

    Optional<Reservations> findByUserIdAndRoomRoomIdAndStartDateAndEndDateAndPaymentCompleted(
        Long userId, Long roomId, LocalDate startTime, LocalDate endTime, boolean paymentCompleted);
}
