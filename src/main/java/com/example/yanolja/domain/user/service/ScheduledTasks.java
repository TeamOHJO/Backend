package com.example.yanolja.domain.user.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduledTasks {

    private final UserService userService;


    @Scheduled(cron = "0 0 0 * * ?")
    public void deleteExpiredMembers() {
        userService.deleteExpiredSoftDeletedUsers();
    }
}