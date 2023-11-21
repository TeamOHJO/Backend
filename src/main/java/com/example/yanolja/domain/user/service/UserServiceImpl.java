package com.example.yanolja.domain.user.service;

import static com.example.yanolja.global.exception.ErrorCode.USER_ALREADY_REGISTERED;

import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.exception.EmailDuplicateError;
import com.example.yanolja.domain.user.exception.InvalidEmailException;
import com.example.yanolja.domain.user.exception.UserNotFoundException;
import com.example.yanolja.domain.user.repository.UserRepository;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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
            throw new EmailDuplicateError(USER_ALREADY_REGISTERED);
        }

        return CreateUserResponse.fromEntity(
            userRepository.save(createUserRequest.toEntity()));
    }

    private static boolean isValidEmail(String email) {
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }


    @Override
    public ResponseDTO<Object> join(CreateUserRequest createUserRequest) {
        // 이메일 유효성 검사
        if (!isValidEmail(createUserRequest.getEmail())) {
            throw new InvalidEmailException();
        }

        // 소프트 삭제된 사용자 복구 또는 새 사용자 생성
        User user;
        Optional<User> softDeletedUser = userRepository.findSoftDeletedByEmail(
            createUserRequest.getEmail(), LocalDateTime.now().minusYears(1));
        if (softDeletedUser.isPresent()) {
            user = softDeletedUser.get();
            user.restore();
        } else {
            user = createUserRequest.toEntity();
        }

        // 사용자 정보 저장
        userRepository.save(user);

        // 성공 응답 반환
        return ResponseDTO.res(HttpStatus.OK, "회원 가입 성공");
    }


    @Override
    public void deleteUser(Long userId) {
        User user = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UserNotFoundException("사용자를 찾을 수 없습니다."));
        user.delete(LocalDateTime.now());
        userRepository.save(user);
    }


}
