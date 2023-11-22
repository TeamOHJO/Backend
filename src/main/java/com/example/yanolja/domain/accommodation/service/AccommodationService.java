package com.example.yanolja.domain.accommodation.service;

import com.example.yanolja.domain.accommodation.dto.AccommodationFindResponse;
import com.example.yanolja.domain.accommodation.dto.AccommodationListFindResponse;
import com.example.yanolja.domain.accommodation.dto.RoomsFindResponse;
import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;


    @Transactional
    public List<AccommodationListFindResponse> getAllAccommodation(){
        List<Accommodation> foundAccommodationList = accommodationRepository.findAll();
        return listToResponse(foundAccommodationList);
    }

    @Transactional
    public AccommodationFindResponse getAccommodationById(Long accommodationId){
        Accommodation foundAccommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(); //todo 예외처리 미완성
        return AccommodationFindResponse.fromEntity(foundAccommodation);
    }

//    @Transactional
//    public Accommodation getAccommodationByName(){
//
//    }
//

    @Transactional
    public List<RoomsFindResponse> getRoomsByAccommodationId(Long accommodationId){
        Accommodation foundAccommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow(); //todo 예외처리 미완성

        List<RoomsFindResponse> roomFindResponses = new ArrayList<>();
        for (AccommodationRooms room : foundAccommodation.getRoomlist()) {
            RoomsFindResponse roomFindResponse = RoomsFindResponse.fromEntity(room);
            roomFindResponses.add(roomFindResponse);
        }

        return roomFindResponses;
    }

    @Transactional
    public List<AccommodationListFindResponse> searchAccommodationByName(String accommodationName){
        List<Accommodation> foundAccommodationList = accommodationRepository.findByNameContains(accommodationName);
        return listToResponse(foundAccommodationList);
    }



    private List<AccommodationListFindResponse> listToResponse(List<Accommodation> accommodationList){
        List<AccommodationListFindResponse> responseList = new ArrayList<>();
        for(Accommodation found : accommodationList){

            AccommodationListFindResponse accommodatonListFindResponse = AccommodationListFindResponse.builder()
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
