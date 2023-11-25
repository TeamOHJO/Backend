package com.example.yanolja.domain.accommodation.service;

import com.example.yanolja.domain.accommodation.dto.AccommodationFindResponse;
import com.example.yanolja.domain.accommodation.dto.RoomsFindResponse;
import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.exception.AccommodationNotFoundException;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomsRepository;
import com.example.yanolja.domain.reservation.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationRoomsRepository accommodationRoomsRepository;
    private final ReservationRepository reservationRepository;
    private final AccommodationImageRepository accommodationImageRepository;
    private final AccommodationRoomImageRepository accommodationRoomImageRepository;

/*
    @Transactional  //필터 조건에 맞춰 숙소 리스트 조회
    public List<AccommodationFindResponse> searchAccommodationsWithFilters(
        List<String> categories, Boolean isDomestic, LocalDate startDate, LocalDate endDate, Integer numberOfPerson) {
        List<Accommodation> allAccommodations = accommodationRepository.findAll();

        // 1. 카테고리 필터링
        List<Accommodation> filteredByCategories = (categories == null || categories.isEmpty())
            ? allAccommodations
            : filterByCategory(allAccommodations, categories);

        // 2. 국내/해외 필터링
        List<Accommodation> filteredByDomestic = (isDomestic == null)
            ? filteredByCategories
            : filterByDomestic(filteredByCategories, isDomestic);

        // 3. 인원 수 필터링
        List<Accommodation> filteredByCapacity = (numberOfPerson == null)
            ? filteredByDomestic
            : filterByCapacity(filteredByDomestic, numberOfPerson);

        // 4. 날짜 필터링
        List<Accommodation> finalFilteredAccommodations = (startDate == null || endDate == null)
            ? filteredByCapacity
            : filterAccommodationsWithAvailableRooms(filteredByCapacity, startDate, endDate);

        // 5. 결과 반환
        return listToResponse(finalFilteredAccommodations);
        //todo 반환 리스트(페이지)에 예약여부, 별점평균, 좋아요 포함 로직..
//        List<AccommodationFindResponse> responses = new ArrayList<>();
//        for (Accommodation accommodation : finalFilteredAccommodations) {
//            AccommodationFindResponse response = AccommodationFindResponse.fromEntity(accommodation);
//            response.isBookable(isBookable(accommodation.getId()));
//            response.setAverageReviewScore(getAverageReviewScore(accommodation.getId()));
//            response.isLiked(getLikeStatus(accommodation.getId()));
//            responses.add(response);
//        }
//
//        return responses;
    }


    @Transactional  //카테고리에 따라 숙소 목록 반환
    public List<Accommodation> filterByCategory(List<Accommodation> accommodations, List<String> category) {
        List<Accommodation> filteredAccommodations = new ArrayList<>();
        for (Accommodation accommodation : accommodations) {
            if (accommodation.getCategory().equals(category)) {
                filteredAccommodations.add(accommodation);
            }
        }
        return filteredAccommodations;
    }


    @Transactional  //국내/국외 여부에 따라 숙소 목록 반환
    public List<Accommodation> filterByDomestic(List<Accommodation> accommodations, boolean isDomestic) {
        List<Accommodation> filteredAccommodations = new ArrayList<>();
        for (Accommodation accommodation : accommodations) {
            if (accommodation.isDomestic() == isDomestic) {
                filteredAccommodations.add(accommodation);
            }
        }
        return filteredAccommodations;
    }


    @Transactional  //여행자 수 필터(인원수에 따라 숙소 목록 반환)
    public List<Accommodation> filterByCapacity(List<Accommodation> accommodations, int numberOfPerson) {
        List<Accommodation> filteredAccommodations = new ArrayList<>();
        for (Accommodation accommodation : accommodations) {
            if (hasRoomForCapacity(accommodation, numberOfPerson)) {
                filteredAccommodations.add(accommodation);
            }
        }
        return filteredAccommodations;
    }


    @Transactional  //날짜에 예약 가능한 숙소 목록 필터
    public List<Accommodation> filterAccommodationsWithAvailableRooms(List<Accommodation> accommodations, LocalDate startDate, LocalDate endDate) {
        List<Accommodation> filteredAccommodations = new ArrayList<>();
        for (Accommodation accommodation : accommodations) {
            if (isAccommodationAvailable(accommodation, startDate, endDate)) {
                filteredAccommodations.add(accommodation);
            }
        }
        return filteredAccommodations;
    }

    @Transactional //특정 숙소가 주어진 날짜에 예약 가능한지 여부를 반환
    public boolean isAccommodationAvailable(Accommodation accommodation, LocalDate startDate, LocalDate endDate) {
        for (AccommodationRooms room : accommodation.getRoomlist()) {
            if (isRoomAvailableForDateRange(room, startDate, endDate)) {
                return true;
            }
        }
        return false;
    }


    @Transactional// 방이 주어진 날짜에 예약 가능한지 확인
    public boolean isRoomAvailableForDateRange(AccommodationRooms room, LocalDate startDate, LocalDate endDate) {
        List<Reservations> reservations = reservationRepository.findByAccommodations_RoomId(room.getRoomId());

        for (Reservations reservation : reservations) {
            if (startDate != null && reservation.getEndDate().isBefore(startDate)) {
                continue; // 예약 종료 날짜가 검색 시작 날짜 이전인 경우
            }
            if (endDate != null && reservation.getStartDate().isAfter(endDate)) {
                continue; // 예약 시작 날짜가 검색 종료 날짜 이후인 경우
            }
            return false;
        }
        return true; // 겹치는 예약이 없는 경우
    }


    @Transactional
    public boolean hasRoomForCapacity(Accommodation accommodation, int numberOfPersons) {
        for (AccommodationRooms room : accommodation.getRoomlist()) {
            if (room.getMinCapacity() <= numberOfPersons && room.getMaxCapacity() >= numberOfPersons) {
                return true;
            }
        }
        return false;
    }*/


    @Transactional  //모든 숙소 목록 조회
    public List<AccommodationFindResponse> getAllAccommodation(){
        List<Accommodation> foundAccommodationList = accommodationRepository.findAll();
        return listToResponse(foundAccommodationList);
    }


    @Transactional  //특정 ID 숙소 조회
    public AccommodationFindResponse getAccommodationById(Long accommodationId){
        Accommodation foundAccommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(); //todo 각종 예외처리 미완성

        List<String> imageList = getImagesForAccommodation(accommodationId);
        return AccommodationFindResponse.fromEntity(foundAccommodation, imageList);
    }

    private List<String> getImagesForAccommodation(Long accommodationId) {
        List<AccommodationImages> images = accommodationImageRepository.findByAccommodation_AccommodationId(accommodationId);
        List<String> imageList = new ArrayList<>();
        for (AccommodationImages image : images) {
            imageList.add(image.getImage());
        }
        return imageList;
    }
    private List<String> getImagesForAccommodationRoom(Long RoomId) {
        List<AccommodationRoomImages> images = accommodationRoomImageRepository.findByAccommodationRooms_RoomId(RoomId);
        List<String> imageList = new ArrayList<>();
        for (AccommodationRoomImages image : images) {
            imageList.add(image.getImage());
        }
        return imageList;
    }

    @Transactional
    public List<AccommodationFindResponse> getAccommodationsByCategory(String categoryStr) {
        AccommodationCategory category = AccommodationCategory.valueOf(categoryStr.toUpperCase());

        List<Accommodation> accommodations = accommodationRepository.findByCategory(category);
        return listToResponse(accommodations);
    }

    @Transactional  //특정 숙소의 모든 방정보 조회
    public List<RoomsFindResponse> getRoomsByAccommodationId(Long accommodationId){
        Accommodation foundAccommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow();

        List<RoomsFindResponse> roomFindResponses = new ArrayList<>();
        for (AccommodationRooms room : foundAccommodation.getRoomlist()) {
            RoomsFindResponse roomFindResponse = RoomsFindResponse.fromEntity(room);
            roomFindResponses.add(roomFindResponse);
        }

        return roomFindResponses;
    }

    @Transactional
    public List<RoomsFindResponse> findAllRoomsInAccommodations(List<AccommodationFindResponse> accommodations) {
        List<RoomsFindResponse> allRooms = new ArrayList<>();
        for (AccommodationFindResponse accommodationResponse : accommodations) {
            Accommodation accommodation = accommodationRepository.findById(accommodationResponse.getAccommodationId())
                .orElseThrow(); // 예외 처리 필요
            for (AccommodationRooms room : accommodation.getRoomlist()) {
                RoomsFindResponse roomResponse = RoomsFindResponse.fromEntity(room);
                allRooms.add(roomResponse);
            }
        }
        return allRooms;
    }

    private List<AccommodationFindResponse> listToResponse(List<Accommodation> accommodationList){
        List<AccommodationFindResponse> responseList = new ArrayList<>();
        for(Accommodation found : accommodationList){

            AccommodationFindResponse accommodatonListFindResponse = AccommodationFindResponse.builder()
                    .accommodationId(found.getAccommodationId())
                    .accommodationId(found.getAccommodationId())
                    .accommodationName(found.getAccommodationName())
                    .location(found.getLocation())
                    .tag(found.getTag())
                    .isDomestic(found.isDomestic())
                    .explanation(found.getExplanation())
                    .cancelInfo(found.getCancelInfo())
                    .useGuide(found.getUseGuide())
                    .reservationNotice(found.getReservationNotice())
                    .serviceInfo(found.getServiceInfo())
                    .build();

            responseList.add(accommodatonListFindResponse);
        }
        return responseList;
    }



}
