package com.example.yanolja.domain.user.service;

import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;
import com.example.yanolja.domain.user.error.EmailDuplicateError;
import com.example.yanolja.domain.user.error.InvalidEmailException;
import com.example.yanolja.domain.user.error.InvalidPhonenumberException;
import com.example.yanolja.domain.user.repository.UserRepository;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private static final String EMAIL_PATTERN =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONENUMBER_REGEX =
        "^010[0-9]{8}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern phonenumberPattern = Pattern.compile(PHONENUMBER_REGEX);

    @Override
    public CreateUserResponse signup(CreateUserRequest createUserRequest) {

        //이메일 유효성 검사
        if (!isValidEmail(createUserRequest.email())) {
            throw new InvalidEmailException();
        }

        //휴대폰 번호 유효성 검사
        if (!isValidPhonenumber(createUserRequest.phonenumber())) {
            throw new InvalidPhonenumberException();
        }

        //TODO ::이메일 인증
        //TODO ::이메일 인증
        //TODO ::이메일 인증
        //TODO ::이메일 인증
        //TODO ::이메일 인증

        if (userRepository.findByEmail(createUserRequest.email()).isPresent()) {
            throw new EmailDuplicateError();
        }

        return CreateUserResponse.fromEntity(
            userRepository.save(createUserRequest.toEntity()));
    }

    private static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidPhonenumber(String phonenumber) {
        Matcher matcher = phonenumberPattern.matcher(phonenumber);
        return matcher.matches();
    }
}
