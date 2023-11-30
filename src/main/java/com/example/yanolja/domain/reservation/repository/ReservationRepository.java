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
        "AND r.paymentCompleted = true AND r.deletedAt is NULL")
    Optional<Reservations> findConflictingReservations(@Param("roomId") Long roomId,
        @Param("startDate") LocalDate startDate,
        @Param("endDate") LocalDate endDate);

    @Query("SELECT r FROM Reservations r WHERE r.reservationId = :id AND (r.deletedAt IS NULL)")
    Optional<Reservations> findByIdAndDeletedAt(@Param("id") Long id);

    @Query("SELECT r FROM Reservations r WHERE r.reservationId = :id AND (r.deletedAt IS NULL)" +
        "AND r.paymentCompleted = true AND r.user.userId = :userId")
    Optional<Reservations> checkReviewPermission(@Param("id") Long id,
        @Param("userId") Long userId);

    @Query("SELECT r FROM Reservations r WHERE r.user.userId = :id AND (r.deletedAt IS NULL)"
        + "AND r.paymentCompleted = true")
    List<Reservations> findUsersReservation(@Param("id") Long id);

    @Query("SELECT r FROM Reservations r WHERE r.user.userId = :id AND (r.deletedAt IS NOT NULL)"
        + "AND r.paymentCompleted = true")
    List<Reservations> findUsersCanceledReservation(@Param("id") Long id);
}
