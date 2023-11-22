package com.example.yanolja.domain.accommodationLikes.service;

import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.Optional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class AccommodationLikesServiceImpl implements AccommodationLikesService {

    private final AccommodationLikesRepository accommodationLikesRepository;

    public AccommodationLikesServiceImpl(AccommodationLikesRepository accommodationLikesRepository) {
        this.accommodationLikesRepository = accommodationLikesRepository;
    }

    @Override
    @Transactional
    public boolean toggleAccommodationLike(Long userId, Long accommodationId) {
        Optional<AccommodationLikes> existingLike = accommodationLikesRepository
            .findByUser_UserIdAndAccommodation_AccommodationId(userId, accommodationId);

        if (existingLike.isPresent()) {
            AccommodationLikes like = existingLike.get();
            if (like.getAccommodation() == null) {
                throw new EntityNotFoundException("숙소 ID " + accommodationId + "를 찾지 못했습니다.");
            }
            accommodationLikesRepository.deleteById(like.getId());
            return false;
        } else {
            try {
                AccommodationLikes newLike = AccommodationLikes.createInstance();
                // User와 Accommodation 객체 설정 부분
                // newLike.setUser(new User(userId));
                // newLike.setAccommodation(new Accommodation(accommodationId));
                newLike.setLike(true);
                accommodationLikesRepository.save(newLike);
                return true;
            } catch (DataIntegrityViolationException e) {
                throw new IllegalArgumentException("제공된 사용자 ID 또는 숙소 ID가 유효하지 않습니다.", e);
            }
        }
    }
}

