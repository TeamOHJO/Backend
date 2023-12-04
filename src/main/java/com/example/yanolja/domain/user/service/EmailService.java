package com.example.yanolja.domain.user.service;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional
public class EmailService {

    private final JavaMailSender javaMailSender;

    @Value("${TEST_ID_EMAIL}")
    private String TEST_ID_EMAIL;

    private final Map<String, String> verificationCodes = new HashMap<>();

    public String sendVerificationEmail(String to) throws Exception {
        String authCode = generateAuthCode();
        MimeMessage message = createMessage(to, authCode);
        javaMailSender.send(message);
        verificationCodes.put(to, authCode);
        return authCode;
    }

    public boolean verifyEmailCode(String email, String code) {
        String storedCode = verificationCodes.get(email);
        return storedCode != null && storedCode.equals(code);
    }

    private String generateAuthCode() {
        Random random = new Random();
        StringBuilder key = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            int index = random.nextInt(3);
            switch (index) {
                case 0:
                    key.append((char) (random.nextInt(26) + 97));
                    break;
                case 1:
                    key.append((char) (random.nextInt(26) + 65));
                    break;
                case 2:
                    key.append(random.nextInt(10));
                    break;
            }
        }
        return key.toString();
    }

    private MimeMessage createMessage(String to, String authCode)
        throws MessagingException, UnsupportedEncodingException {
        String setFrom = TEST_ID_EMAIL;
        String title = "회원가입 인증 번호";

        MimeMessage message = javaMailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, to);
        message.setSubject(title);
        message.setFrom(new InternetAddress(setFrom, "Your Name", "UTF-8"));

        String htmlContent = "<div style='margin:100px;'>"
            + "<h1>안녕하세요 OHNOLZA 입니다.</h1>"
            + "<p>아래 코드를 회원가입 창으로 돌아가 입력해주세요.</p><br>"
            + "<div align='center' style='border:1px solid black; font-family:verdana'>"
            + "<h3 style='color:#9AC1D1;'>회원가입 인증 코드</h3>"
            + "<div style='font-size:130%'>CODE : <strong>" + authCode + "</strong></div><br/>"
            + "</div>";

        message.setContent(htmlContent, "text/html; charset=utf-8");
        return message;
    }
}