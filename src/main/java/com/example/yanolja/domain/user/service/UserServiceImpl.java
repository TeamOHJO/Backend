package com.example.yanolja.domain.user.service;


import com.example.yanolja.domain.user.dto.ChangePasswordRequest;
import com.example.yanolja.domain.user.dto.CreateUserRequest;
import com.example.yanolja.domain.user.dto.CreateUserResponse;
import com.example.yanolja.domain.user.dto.UpdateUserRequest;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.exception.EmailDuplicateException;
import com.example.yanolja.domain.user.exception.InvalidPasswordException;
import com.example.yanolja.domain.user.exception.UserNotFoundException;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.util.ResponseDTO;
import java.time.LocalDateTime;
import java.util.Optional;
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
        }

        userRepository.findByEmail(createUserRequest.email()).ifPresent(user -> {
            throw new EmailDuplicateException();
        });

        String encodedPassword = passwordEncoder.encode(createUserRequest.password());

        User newUser = User.builder()
            .email(createUserRequest.email())
            .username(createUserRequest.username())
            .password(encodedPassword)
            .phonenumber(createUserRequest.phonenumber())
            .authority("ROLE_USER")
            .updatedAt(LocalDateTime.now())
            .build();

        userRepository.save(newUser);
        return ResponseDTO.res(HttpStatus.CREATED, "회원 가입 성공",
            CreateUserResponse.fromEntity(newUser));
    }


    public ResponseDTO<Object> deleteUser(Long userId) {
        User user = userRepository.findByUserId(userId)
            .orElseThrow(() -> new UserNotFoundException());
        user.delete(LocalDateTime.now());
        userRepository.save(user);
        return ResponseDTO.res(HttpStatus.OK, "회원 탈퇴 처리 완료");
    }

    @Override
    public ResponseDTO<?> updateUser(Long userId, UpdateUserRequest updateUserRequest) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

        user.updateUserInfo(updateUserRequest.getUsername(), updateUserRequest.getPhonenumber());
        userRepository.save(user);
        return ResponseDTO.res(HttpStatus.OK, "사용자 정보 업데이트 성공");
    }

    @Override
    public ResponseDTO<?> changePassword(Long userId, ChangePasswordRequest changePasswordRequest) {

        User user = userRepository.findById(userId)
            .orElseThrow(UserNotFoundException::new);

        if (!passwordEncoder.matches(changePasswordRequest.getOldPassword(), user.getPassword())) {
            throw new InvalidPasswordException();
        }

        if (passwordEncoder.matches(changePasswordRequest.getNewPassword(), user.getPassword())) {
            return ResponseDTO.res(HttpStatus.BAD_REQUEST, "새 비밀번호는 현재 비밀번호와 달라야 합니다.");
        }

        user.changePassword(passwordEncoder.encode(changePasswordRequest.getNewPassword()));
        userRepository.save(user);
        return ResponseDTO.res(HttpStatus.OK, "비밀번호 변경 성공");
    }

}