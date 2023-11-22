package com.example.yanolja.domain.accommodation.service;

import com.example.yanolja.domain.accommodation.dto.AccommodationFindResponse;
import com.example.yanolja.domain.accommodation.dto.AccommodationListFindResponse;
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
        Accommodation foundAccommodation = accommodationRepository.findById(accommodationId).orElseThrow(); //todo optional?
        return AccommodationFindResponse.fromEntity(foundAccommodation);
    }

//    @Transactional
//    public Accommodation getAccommodationByName(){
//
//    }
//
//    @Transactional
//    public AccommodationRooms getAccommodationRoomsById(){
//
//    }

    @Transactional
    public List<AccommodationListFindResponse> searchAccommodation(String accommodationName){
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
