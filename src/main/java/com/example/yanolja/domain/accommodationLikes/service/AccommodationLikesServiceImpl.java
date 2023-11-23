package com.example.yanolja.domain.accommodationLikes.service;

import static com.example.yanolja.global.exception.ErrorCode.ACCOMMODATION_NOT_FOUND;

import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodationLikes.dto.AccommodationLikesResponse;
import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.accommodationLikes.exception.UserIdAndAccommodationIdNotFoundException;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
public class AccommodationLikesServiceImpl implements AccommodationLikesService {

    private final AccommodationLikesRepository accommodationLikesRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ResponseDTO<Boolean> toggleAccommodationLike(User user, Long accommodationId) {

//        // 사용자 유효성 검사
//        if (user == null || !userRepository.existsById(user.getUserId())) {
//            throw new UserIdAndAccommodationIdNotFoundException(MEMBER_NOT_FOUND);
//        }

        // 숙소 존재 여부 확인
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(
                () -> new UserIdAndAccommodationIdNotFoundException(ACCOMMODATION_NOT_FOUND));

        // 좋아요 상태 확인 및 변경
        Optional<AccommodationLikes> existingLike = accommodationLikesRepository
            .findByUser_UserIdAndAccommodation_AccommodationId(user.getId(), accommodationId);

        if (existingLike.isPresent()) {
            AccommodationLikes like = existingLike.get();
            like.setIsLike(!like.getIsLike()); // 현재 상태를 반전 (true -> false, false -> true)
            accommodationLikesRepository.save(like);
            return ResponseDTO.res(HttpStatus.OK, like.getIsLike() ? "좋아요 추가됨" : "좋아요 해제됨",
                like.getIsLike());
        } else {
            // 좋아요 상태가 존재하지 않는 경우, 새로운 객체를 생성하고 저장
            AccommodationLikesResponse accommodationLikesResponse = new AccommodationLikesResponse(
                accommodationId, true);
            AccommodationLikes newLike = accommodationLikesResponse.toEntity(user, accommodation,
                true);
            accommodationLikesRepository.save(newLike);
            return ResponseDTO.res(HttpStatus.CREATED, "좋아요 상태 생성", true);
        }

    }
}




