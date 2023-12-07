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

    private String phonenumber;

    private String authority;

    private String provider;  //어떤 소셜로그인인지

    @Builder
    public User(String email, String username, String password, String phonenumber,
        String authority, LocalDateTime updatedAt, LocalDateTime deletedAt, String provider) {
        this.email = email;
        this.username = username;
        this.password = password;
        this.phonenumber = phonenumber;
        this.authority = authority;
        this.provider = provider;
        super.updatedAt = updatedAt;
        super.deletedAt = deletedAt;
    }

    public void updateUserInfo(String username, String phonenumber) {
        this.username = username;
        this.phonenumber = phonenumber;
    }

    public void changePassword(String newPassword) {
        this.password = newPassword;
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
}
