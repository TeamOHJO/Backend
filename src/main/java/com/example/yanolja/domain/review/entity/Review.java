package com.example.yanolja.domain.review.entity;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Review extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewId", nullable = false)
    private long reviewId;

    @ManyToOne
    @JoinColumn(name = "roomId", referencedColumnName = "roomId")
    private AccommodationRooms room;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "reservationId", referencedColumnName = "reservationId")
    private Reservations reservation;

    @OneToMany(mappedBy = "review")
    private List<ReviewImages> reviewImages = new ArrayList<>();

    @Column(name = "reviewContent", nullable = false, length = 500)
    private String reviewContent;

    @Column(name = "star", nullable = false)
    private int star;

    @ManyToOne
    @JoinColumn(name = "accommodationId")
    private Accommodation accommodation;

    @Builder
    public Review(AccommodationRooms accommodationRooms,User user,
        Reservations reservations,Accommodation accommodation,
        String reviewContent,int star
    ) {
        this.room = accommodationRooms;
        this.user = user;
        this.reservation =reservations;
        this.reviewContent = reviewContent;
        this.star = star;
        this.accommodation = accommodation;
    }
}

