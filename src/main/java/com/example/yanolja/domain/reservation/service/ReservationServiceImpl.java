package com.example.yanolja.domain.reservation.service;

import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomImagesRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.basket.repository.BasketRepository;
import com.example.yanolja.domain.reservation.dto.CreateReservationRequest;
import com.example.yanolja.domain.reservation.dto.CreateReservationResponse;
import com.example.yanolja.domain.reservation.dto.GetReservationDetailsResponse;
import com.example.yanolja.domain.reservation.dto.GetUsersReservationResponse;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.reservation.exception.InvalidAccommodationRoomIdException;
import com.example.yanolja.domain.reservation.exception.InvalidCancelReservationRequestException;
import com.example.yanolja.domain.reservation.exception.ReservationConflictException;
import com.example.yanolja.domain.reservation.repository.ReservationRepository;
import com.example.yanolja.domain.review.repository.ReviewRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.global.exception.ErrorCode;
import com.example.yanolja.global.util.ResponseDTO;
import com.example.yanolja.global.util.ReviewRatingUtils;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final AccommodationRoomRepository accommodationRoomRepository;
    private final ReservationRepository reservationRepository;
    private final AccommodationRoomImagesRepository accommodationRoomImagesRepository;
    private final BasketRepository basketRepository;
    private final ReviewRepository reviewRepository;

    @Override
    public ResponseDTO<CreateReservationResponse> createReservation(
        CreateReservationRequest createReservationRequest,
        User user, long roomId) {

        // 방 정보 확인
        AccommodationRooms rooms = accommodationRoomRepository.findById(roomId)
            .orElseThrow(() -> new InvalidAccommodationRoomIdException(ErrorCode.INVALID_ACCOMMODATION_ID));

        // 예약 충돌 체크
        if (reservationRepository.findConflictingReservations(
            roomId, createReservationRequest.startDate(), createReservationRequest.endDate()
        ).isPresent()) {
            throw new ReservationConflictException(ErrorCode.RESERVATION_CONFLICT);
        }

        //장바구니에 있는 상품을 예약하는것인지, 바로 예약을 하는건지 체크
        Optional<Reservations> reservationsInBasket =
            reservationRepository.findByUserIdAndRoomRoomIdAndStartDateAndEndDateAndPaymentCompleted(
                user.getUserId(), roomId, createReservationRequest.startDate(),
                createReservationRequest.endDate(), false
            );

        //장바구니에 있던 상품이라면 reseravtion테이블에 paymentCompleted만 변경해주면 된다.
        if (reservationsInBasket.isPresent()) {
            Reservations reservationInBasket = reservationsInBasket.get();
            reservationInBasket.changePaymentCompletedStatus();
            reservationRepository.save(reservationInBasket);

            //결제가 완료된 상품이기에 장바구니에서 삭제
            basketRepository.delete(
                basketRepository.findByReservationReservationId(reservationInBasket.getReservationId())
            );

            return ResponseDTO.res(HttpStatus.CREATED, "예약 성공",
                CreateReservationResponse.fromEntity(user, rooms, reservationInBasket));

        } else {
            Reservations reservations =
                reservationRepository.save(
                    createReservationRequest.toEntity(user, rooms, true));

            return ResponseDTO.res(HttpStatus.CREATED, "예약 성공",
                CreateReservationResponse.fromEntity(user, rooms, reservations));
        }
    }

    @Override
    public ResponseDTO<GetReservationDetailsResponse> getReservationDetails(long roomId) {

        Optional<AccommodationRooms> accommodationRooms =
            accommodationRoomRepository.findById(roomId);

        //roomsId에 해당하는 방이 존재하지 않는 경우
        AccommodationRooms rooms = accommodationRooms.orElseThrow(
            () -> new InvalidAccommodationRoomIdException(
                ErrorCode.INVALID_ACCOMMODATION_ID));

        AccommodationRoomImages accommodationRoomImages =
            accommodationRoomImagesRepository.findFirstByAccommodationRoomsRoomId(roomId);

        return ResponseDTO.res(HttpStatus.OK, "예약 상세페이지 조회 성공",
            GetReservationDetailsResponse.fromEntity(rooms.getAccommodation(),
                rooms, accommodationRoomImages.getImage()));
    }

    @Override
    public ResponseDTO<Void> cancelReservation(User user, long reservationId) {

        Reservations reservation =
            reservationRepository.findByIdAndDeletedAt(reservationId).orElseThrow(() -> new
                InvalidCancelReservationRequestException
                (ErrorCode.INVALID_CANCEL_RESERVATION_REQUEST));

        reservation.delete(LocalDateTime.now());
        reservationRepository.save(reservation);

        return ResponseDTO.res(HttpStatus.NO_CONTENT, "예약 취소 완료",null);
    }

    @Override
    public ResponseDTO<List<GetUsersReservationResponse>> getUsersReservation(User user) {

        List<Reservations> reservationsList =
            reservationRepository.findUsersReservation(user.getUserId());

        return ResponseDTO.res(HttpStatus.OK, "예약 내역 조회 완료",
            reservationsList.stream().map(i ->
                GetUsersReservationResponse.fromEntity(
                    i.getRoom().getAccommodation(),
                    i.getRoom(),
                    i.getRoom().getAccommodation().getImagelist().get(0).getImage(),
                    i,
                    ReviewRatingUtils.calculateAverageRating(
                        i.getRoom().getAccommodation().getAccommodationId(), reviewRepository)
                )).collect(Collectors.toList()));
    }

    @Override
    public ResponseDTO<List<GetUsersReservationResponse>> getUsersCanceledReservation(User user) {
        List<Reservations> reservationsList =
            reservationRepository.findUsersCanceledReservation(user.getUserId());

        return ResponseDTO.res(HttpStatus.OK, "예약 취소 내역 조회 완료",
            reservationsList.stream().map(i ->
                GetUsersReservationResponse.fromEntity(
                    i.getRoom().getAccommodation(),
                    i.getRoom(),
                    i.getRoom().getAccommodation().getImagelist().get(0).getImage(),
                    i,
                    ReviewRatingUtils.calculateAverageRating(
                        i.getRoom().getAccommodation().getAccommodationId(), reviewRepository)
                )).collect(Collectors.toList()));
    }
}


