package com.example.yanolja.domain.reservation.repository;

import com.example.yanolja.domain.reservation.entity.Reservations;
import java.time.LocalDate;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepositoryCustom {

    Optional<Reservations> findConflictingReservations(Long roomId,
        LocalDate startDate, LocalDate endDate);
}
