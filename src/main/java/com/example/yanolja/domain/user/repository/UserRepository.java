package com.example.yanolja.domain.user.repository;

import com.example.yanolja.domain.user.entity.User;
import java.time.LocalDateTime;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.deletedAt is null")
    Optional<User> findNotDeletedUserByEmail(String email);

    Optional<User> findByUserId(Long userId);


    void deleteByDeletedAtBefore(LocalDateTime dateTime);

    @Query("SELECT u FROM User u WHERE u.email = :email AND u.deletedAt > :dateTime")
    Optional<User> findSoftDeletedByEmail(@Param("email") String email,
        @Param("dateTime") LocalDateTime dateTime);

}
