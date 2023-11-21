package com.example.yanolja.domain.user.entity;

import com.example.yanolja.global.entity.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;

    private String password;

    private String username;

    //@Column(name = "phonenumber", nullable = false)
    //private String phonenumber;

    @Builder
    public User(String email, String username, String password, String authority,
        LocalDateTime createdAt, LocalDateTime deletedAt) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.authority = authority;
        super.createdAt = createdAt;
        super.deletedAt = deletedAt;
    }

    @Override
    public void delete(LocalDateTime currentTime) {
        super.delete(currentTime);
    }

    @Override
    public void restore() {
        super.restore();
    }

    public Long getId() {
        return this.userId;
    }


    private String authority;

}
