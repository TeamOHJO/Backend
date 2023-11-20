package com.example.yanolja.domain.user.service;

import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;
import com.example.yanolja.domain.user.error.EmailDuplicateError;
import com.example.yanolja.domain.user.error.InvalidEmailException;
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
    private static final Pattern pattern = Pattern.compile(EMAIL_PATTERN);

    @Override
    public CreateUserResponse signup(CreateUserRequest createUserRequest) {

        //이메일 유효성 검사
        if (!isValidEmail(createUserRequest.email())) {
            throw new InvalidEmailException();
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
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
