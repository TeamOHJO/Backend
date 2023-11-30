package com.example.yanolja.domain.user.service;

import com.example.yanolja.domain.user.repository.UserRepository;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTasks {

    private final UserRepository userRepository;

    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredMembers() {
        userRepository.deleteByDeletedAtBefore(LocalDateTime.now().minusYears(1));
    }
}
