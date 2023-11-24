package com.example.yanolja.domain.reservation.entity;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.entity.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reservations extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservationId")
    private Long reservationId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private AccommodationRooms room;

    @Column(name = "startDate", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @Column(name = "endDate", nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    @Column(name = "numberOfPerson", nullable = false)
    private int numberOfPerson;

    @Column(name = "paymentCompleted", nullable = false)
    private boolean paymentCompleted;

    @Builder
    public Reservations(
        User user, AccommodationRooms rooms, LocalDate startDate,
        LocalDate endDate, int numberOfPerson, boolean paymentCompleted) {
        this.user = user;
        this.room = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.numberOfPerson = numberOfPerson;
        this.paymentCompleted = paymentCompleted;
    }

    @Override
    public void delete(LocalDateTime currentTime) {
        super.delete(currentTime);
    }
}
