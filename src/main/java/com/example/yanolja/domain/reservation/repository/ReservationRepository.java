package com.example.yanolja.domain.reservation.repository;
import com.example.yanolja.domain.reservation.entity.Reservations;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository
    extends JpaRepository<Reservations, Long> {

    List<Reservations> findByRoomId(Long roomId);
    Optional<Reservations> findByUserIdAndRoomRoomIdAndStartDateAndEndDateAndPaymentCompleted(
        Long userId, Long roomId, LocalDate startTime, LocalDate endTime, boolean paymentCompleted);

    List<Reservations> findAllByUserIdAndPaymentCompleted(Long userId, boolean paymentCompleted);
}
