package com.example.yanolja.domain.user.repository;

import com.example.yanolja.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    Optional<User> findByUserId(Long userId);

    Optional<User> findByNickname(String nickname);



    boolean existsByEmail(String email);

    void deleteByDeletedAtBefore(LocalDateTime dateTime);

    @Query("SELECT m FROM User m WHERE m.email = :email AND m.deletedAt > :dateTime")
    Optional<User> findSoftDeletedByEmail(@Param("email") String email,
        @Param("dateTime") LocalDateTime dateTime);

}
