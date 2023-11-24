package com.example.yanolja.domain.basket.service;

import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomImagesRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import com.example.yanolja.domain.basket.dto.CreateBasketRequest;
import com.example.yanolja.domain.basket.dto.CreateBasketResponse;
import com.example.yanolja.domain.basket.dto.GetBasketResponse;
import com.example.yanolja.domain.basket.error.DuplicatedBasketException;
import com.example.yanolja.domain.basket.repository.BasketRepository;
import com.example.yanolja.domain.reservation.dto.CreateReservationRequest;
import com.example.yanolja.domain.reservation.entity.Reservations;
import com.example.yanolja.domain.reservation.exception.InvalidAccommodationRoomIdException;
import com.example.yanolja.domain.reservation.repository.ReservationRepository;
import com.example.yanolja.domain.user.entity.User;
import com.example.yanolja.domain.user.repository.UserRepository;
import com.example.yanolja.global.exception.ErrorCode;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class BasketServiceImpl implements BasketService {

    private final UserRepository userRepository;
    private final AccommodationRepository accommodationRepository;
    private final AccommodationRoomRepository accommodationRoomRepository;
    private final ReservationRepository reservationRepository;
    private final BasketRepository basketRepository;
    private final AccommodationRoomImagesRepository accommodationRoomImagesRepository;

    @Override
    public ResponseDTO<?> addBasket(CreateBasketRequest createBasketRequest, User user,
        long roomsId) {

        Optional<AccommodationRooms> accommodationRooms =
            accommodationRoomRepository.findById(roomsId);

        //roomsId에 해당하는 방이 존재하지 않는 경우
        AccommodationRooms rooms = accommodationRooms.orElseThrow(() -> {
            throw new InvalidAccommodationRoomIdException(ErrorCode.INVALID_ACCOMMODATION_ID);
        });

        //장바구니에 같은 값을 다시 넣으려고 하는경우
        Optional<Reservations> checkDuplicate =
            reservationRepository.findByUserIdAndRoomRoomIdAndStartDateAndEndDateAndPaymentCompleted(
                user.getUserId(), roomsId, createBasketRequest.startDate(),
                createBasketRequest.endDate(), false);
        if (checkDuplicate.isPresent()) {
            throw new DuplicatedBasketException(ErrorCode.DUPLICATED_BASKET_CONTENT);
        }

        //장바구니에 추가는 reservation table에 paymentCompleted가 false로 기록되어야 한다.
        CreateReservationRequest createReservationRequest = new CreateReservationRequest(
            createBasketRequest.startDate(), createBasketRequest.endDate(),
            createBasketRequest.numberOfPerson()
        );

        Reservations reservations =
            reservationRepository.save(
                createReservationRequest.toEntity(user, rooms, false));

        basketRepository.save(
            createBasketRequest.toEntity(user, rooms, reservations));

        return ResponseDTO.res(HttpStatus.CREATED, "장바구니 추가 성공",
            CreateBasketResponse.fromEntity(user, rooms, reservations));
    }

    @Override
    public ResponseDTO<?> getBasket(User user) {

        //paymentCompleted가false인 항목은 장바구니에 있는 항목
        List<Reservations> reservations =
            reservationRepository.findAllByUserIdAndPaymentCompleted(user.getUserId(), false);

        List<GetBasketResponse> getBasketResponses = new ArrayList<>();

        for (Reservations reservationContent : reservations) {
            List<String> imageList = new ArrayList<>();

            List<AccommodationRoomImages> accommodationRoomImages =
                accommodationRoomImagesRepository.findAllByAccommodationRoomsRoomId(
                    reservationContent.getRoom().getRoomId());

            for (AccommodationRoomImages accommodationRoomImagesContent : accommodationRoomImages) {
                imageList.add(accommodationRoomImagesContent.getImage());
            }

            boolean canReserve = false;

            //TODO :: 예약 중복조회해서 canReserve 값 변경 할 수 있도록 해야함
            //TODO :: 예약 중복조회해서 canReserve 값 변경 할 수 있도록 해야함
            //TODO :: 예약 중복조회해서 canReserve 값 변경 할 수 있도록 해야함
            //TODO :: 예약 중복조회해서 canReserve 값 변경 할 수 있도록 해야함

            getBasketResponses.add(GetBasketResponse.fromEntity(reservationContent,
                reservationContent.getRoom(), imageList, canReserve));
        }

        return ResponseDTO.res(HttpStatus.CREATED, "장바구니 조회 성공",
            getBasketResponses);
    }
}

