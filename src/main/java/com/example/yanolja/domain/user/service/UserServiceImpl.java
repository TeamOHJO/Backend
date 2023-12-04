package com.example.yanolja.domain.user.service;


import com.example.yanolja.domain.user.dto.ChangePasswordRequest;
import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;
import com.example.yanolja.domain.user.dto.UpdateUserRequest;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.exception.EmailDuplicateException;
import com.example.yanolja.domain.user.exception.InvalidEmailException;
import com.example.yanolja.domain.user.exception.InvalidPasswordException;
import com.example.yanolja.domain.user.exception.InvalidPhonenumberException;
import com.example.yanolja.domain.user.exception.UserNotFoundException;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.util.ResponseDTO;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private static final String EMAIL_PATTERN =
        "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static final String PHONENUMBER_REGEX =
        "^010[0-9]{8}$";
    private static final Pattern emailPattern = Pattern.compile(EMAIL_PATTERN);
    private static final Pattern phonenumberPattern = Pattern.compile(PHONENUMBER_REGEX);

    private static boolean isValidEmail(String email) {
        Matcher matcher = emailPattern.matcher(email);
        return matcher.matches();
    }

    private static boolean isValidPhonenumber(String phonenumber) {
        Matcher matcher = phonenumberPattern.matcher(phonenumber);
        return matcher.matches();
    }

    @Override
    public ResponseDTO<?> signup(CreateUserRequest createUserRequest) {

        Optional<User> softDeletedUser = userRepository.findSoftDeletedByEmail(
            createUserRequest.email(), LocalDateTime.now().minusYears(1));
        if (softDeletedUser.isPresent()) {
            User user = softDeletedUser.get();
            user.restore();
            userRepository.save(user);
            return ResponseDTO.res(HttpStatus.OK, "기존 계정 복구 완료",
                CreateUserResponse.fromEntity(user));
        } else {

            //이메일 유효성 검사
            if (!isValidEmail(createUserRequest.email())) {
                throw new InvalidEmailException();
            }
            //휴대폰 번호 유효성 검사
            if (!isValidPhonenumber(createUserRequest.phonenumber())) {
                throw new InvalidPhonenumberException();
            }
            //이메일 중복 검사
            if (userRepository.findByEmail(createUserRequest.email()).isPresent()) {
                throw new EmailDuplicateException();
            }

            User newUser = createUserRequest.toEntity();
            userRepository.save(newUser);
            return ResponseDTO.res(HttpStatus.CREATED, "회원 가입 성공",
                CreateUserResponse.fromEntity(newUser));
        }
    }


    public ResponseDTO<Object> deleteUser(Long userId) {
        try {
            User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UserNotFoundException());
            user.delete(LocalDateTime.now());
            userRepository.save(user);
            return ResponseDTO.res(HttpStatus.OK, "회원 탈퇴 처리 완료");
        } catch (UserNotFoundException e) {
            return ResponseDTO.res(HttpStatus.NOT_FOUND, e.getMessage());
        } catch (Exception e) {
            return ResponseDTO.res(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러: " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO<?> updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

        if (!isValidPhonenumber(updateUserRequest.getPhonenumber())) {
            throw new InvalidPhonenumberException();
        }

        boolean isUpdated = false;
        if (!user.getUsername().equals(updateUserRequest.getUsername())) {
            user.setUsername(updateUserRequest.getUsername());
            isUpdated = true;
        }

        if (!user.getPhonenumber().equals(updateUserRequest.getPhonenumber())) {
            user.setPhonenumber(updateUserRequest.getPhonenumber());
            isUpdated = true;
        }

        if (isUpdated) {
            userRepository.save(user);
        }

        return ResponseDTO.res(HttpStatus.OK, "사용자 정보 업데이트 성공");
    }


    @Override
    public ResponseDTO<?> changePassword(Long userId, ChangePasswordRequest changePasswordRequest) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        if (passwordEncoder.matches(changePasswordRequest.getNewPassword(), user.getPassword())) {
            return ResponseDTO.res(HttpStatus.BAD_REQUEST, "새 비밀번호는 현재 비밀번호와 달라야 합니다.");
        }

        user.setPassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);

        return ResponseDTO.res(HttpStatus.OK, "비밀번호 변경 성공");
    }


}
