package com.example.yanolja.domain.reservation.service;

import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomImagesRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.reservation.dto.CreateReservationRequest;
import com.example.yanolja.domain.reservation.dto.CreateReservationResponse;
import com.example.yanolja.domain.reservation.dto.GetReservationDetailsResponse;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.reservation.exception.InvalidAccommodationRoomIdException;
import com.example.yanolja.domain.reservation.exception.InvalidCancelReservationRequestException;
import com.example.yanolja.domain.reservation.exception.ReservationConflictException;
import com.example.yanolja.domain.reservation.repository.ReservationRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.exception.ErrorCode;
import com.example.yanolja.global.util.ResponseDTO;
import java.time.LocalDateTime;
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
    private final AccommodationRoomImagesRepository accommodationRoomImagesRepository;

    @Override
    public ResponseDTO<?> createReservation(CreateReservationRequest createReservationRequest,
        User user, long roomId) {

        Optional<AccommodationRooms> accommodationRooms =
            accommodationRoomRepository.findById(roomId);

        //roomsId에 해당하는 방이 존재하지 않는 경우
        AccommodationRooms rooms = accommodationRooms.orElseThrow(() -> {
            throw new InvalidAccommodationRoomIdException(ErrorCode.INVALID_ACCOMMODATION_ID);
        });

        Optional<Reservations> conflictingReservations =
            reservationRepository.findConflictingReservations(
                roomId,
                createReservationRequest.startDate(), createReservationRequest.endDate()
            );
        if (conflictingReservations.isPresent()) {
            throw new ReservationConflictException(ErrorCode.RESERVATION_CONFLICT);
        }

        Reservations reservations =
            reservationRepository.save(
                createReservationRequest.toEntity(user, rooms, true));

        return ResponseDTO.res(HttpStatus.CREATED, "예약 성공",
            CreateReservationResponse.fromEntity(user, rooms, reservations));

    }

    @Override
    public ResponseDTO<?> getReservationDetails(long roomId) {

        Optional<AccommodationRooms> accommodationRooms =
            accommodationRoomRepository.findById(roomId);

        //roomsId에 해당하는 방이 존재하지 않는 경우
        AccommodationRooms rooms = accommodationRooms.orElseThrow(() -> {
            throw new InvalidAccommodationRoomIdException(ErrorCode.INVALID_ACCOMMODATION_ID);
        });

        AccommodationRoomImages accommodationRoomImages =
            accommodationRoomImagesRepository.findFirstByAccommodationRoomsRoomId(roomId);

        return ResponseDTO.res(HttpStatus.OK, "예약 상세페이지 조회 성공",
            GetReservationDetailsResponse.fromEntity(rooms.getAccommodation(),
                rooms, accommodationRoomImages.getImage()));
    }

    @Override
    public ResponseDTO<?> cancelReservation(User user, long reservationId) {

        Reservations reservation =
            reservationRepository.findByIdAndDeletedAt(reservationId).orElseThrow(() -> {
                throw new
                    InvalidCancelReservationRequestException
                    (ErrorCode.INVALID_CANCEL_RESERVATION_REQUEST);
            });

        reservation.delete(LocalDateTime.now());
        reservationRepository.save(reservation);

        return ResponseDTO.res(HttpStatus.NO_CONTENT, "예약 취소 완료");
    }
}


