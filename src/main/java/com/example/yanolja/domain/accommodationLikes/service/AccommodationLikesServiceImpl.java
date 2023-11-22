package com.example.yanolja.domain.accommodationLikes.service;

import com.example.yanolja.domain.accommodationLikes.entity.AccommodationLikes;
import com.example.yanolja.domain.accommodationLikes.repository.AccommodationLikesRepository;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccommodationLikesServiceImpl implements AccommodationLikesService {

    private final AccommodationLikesRepository accommodationLikesRepository;

    public AccommodationLikesServiceImpl(
        AccommodationLikesRepository accommodationLikesRepository) {
        this.accommodationLikesRepository = accommodationLikesRepository;
    }


    @Override
    @Transactional
    public boolean toggleAccommodationLike(Long userId, Long accommodationId) {
        Optional<AccommodationLikes> existingLike = accommodationLikesRepository
            .findByUser_UserIdAndAccommodation_AccommodationId(userId, accommodationId);

        if (existingLike.isPresent()) {
            accommodationLikesRepository.deleteById(existingLike.get().getId());
            return false;
        } else {
            AccommodationLikes newLike = AccommodationLikes.createInstance();
            // User와 Accommodation 객체 설정 부분
            // newLike.setUser(new User(userId));
            // newLike.setAccommodation(new Accommodation(accommodationId));
            newLike.setLike(true);
            accommodationLikesRepository.save(newLike);

            return true;

        }

    }

}
