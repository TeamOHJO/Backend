package com.example.yanolja.domain.user.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import com.example.yanolja.domain.user.request.EmailRequest;
import com.example.yanolja.domain.user.service.EmailService;

@RestController
@RequiredArgsConstructor
public class EmailController {

    private final EmailService emailService;

    private final HttpSession session;

    @PostMapping("/user/email/confirmation")
    public ResponseEntity<String> mailConfirm(@RequestBody EmailRequest emailRequest)
        throws Exception {
        String code = emailService.sendSimpleMessage(emailRequest.getEmail());
        session.setAttribute("emailCode", code);

        return ResponseEntity.ok("success");
    }

    @GetMapping("/user/verify/{code}")
    public ResponseEntity<Map<String, Object>> verifyEmail(@PathVariable("code") String code,
        HttpSession session) {
        Map<String, Object> resultMap = new HashMap<>();
        session.setMaxInactiveInterval(30 * 60);

        String sessionCode = (String) session.getAttribute("emailCode");
        if (sessionCode != null && sessionCode.equals(code)) {

            resultMap.put("success", true);
            return ResponseEntity.ok(resultMap);
        } else {

            resultMap.put("success", false);
            return ResponseEntity.ok(resultMap);
        }
    }

}