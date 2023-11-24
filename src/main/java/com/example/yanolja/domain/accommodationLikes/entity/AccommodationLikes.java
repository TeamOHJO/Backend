package com.example.yanolja.domain.accommodationLikes.entity;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Builder
public class AccommodationLikes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "accommodationId", referencedColumnName = "accommodationId")
    private Accommodation accommodation;

    @Column(name = "isLike")
    private boolean isLike;

    public void setIsLike(boolean isLike) {
        this.isLike = isLike;
    }

    public boolean getIsLike() {
        return this.isLike;
    }

    public AccommodationLikes(User user, Accommodation accommodation, boolean isLike) {
        this.user = user;
        this.accommodation = accommodation;
        this.isLike = isLike;
    }


}
