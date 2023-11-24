package com.example.yanolja.domain.reservation.repository;

import com.example.yanolja.domain.reservation.entity.Reservations;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReservationRepository
    extends JpaRepository<Reservations, Long> {

    Optional<Reservations> findByUserIdAndRoomRoomIdAndStartDateAndEndDateAndPaymentCompleted(
        Long userId, Long roomId, LocalDate startTime, LocalDate endTime, boolean paymentCompleted);

    List<Reservations> findAllByUserIdAndPaymentCompleted(Long userId, boolean paymentCompleted);

    @Query("SELECT r FROM Reservations r " +
        "WHERE r.room.roomId = :roomId " +
        "AND (:startDate <= r.endDate AND :endDate>= r.startDate " +
        "OR :startDate >= r.startDate AND :endDate <= r.endDate) " +
        "AND r.paymentCompleted = true")
    Optional<Reservations> findConflictingReservations(@Param("roomId") Long roomId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate);
}
