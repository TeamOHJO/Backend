package com.example.yanolja.domain.reservation.service;

import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.reservation.dto.CreateReservationRequest;
import com.example.yanolja.domain.reservation.dto.CreateReservationResponse;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.reservation.exception.InvalidAccommodationRoomIdException;
import com.example.yanolja.domain.reservation.repository.ReservationRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.exception.ErrorCode;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public ResponseDTO<?> createReservation(CreateReservationRequest createReservationRequest,
        User user, long roomsId) {

        Optional<AccommodationRooms> accommodationRooms =
            accommodationRoomRepository.findById(roomsId);

        //roomsId에 해당하는 방이 존재하지 않는 경우
        AccommodationRooms rooms = accommodationRooms.orElseThrow(() -> {
            throw new InvalidAccommodationRoomIdException(ErrorCode.INVALID_ACCOMMODATION_ID);
        });

        //TODO :: 이미해당날짜에 예약이 차있는 경우
        //TODO :: 이미해당날짜에 예약이 차있는 경우
        //TODO :: 이미해당날짜에 예약이 차있는 경우
        //TODO :: 이미해당날짜에 예약이 차있는 경우
        //TODO :: 이미해당날짜에 예약이 차있는 경우

        Reservations reservations =
            reservationRepository.save(
                createReservationRequest.toEntity(user, rooms, true));

        return ResponseDTO.res(HttpStatus.CREATED, "예약 성공",
            CreateReservationResponse.fromEntity(user, rooms, reservations));

    }
}


