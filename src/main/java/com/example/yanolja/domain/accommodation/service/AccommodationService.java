package com.example.yanolja.domain.accommodation.service;

import com.example.yanolja.domain.accommodation.dto.AccommodationFindResponse;
import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final AccommodationImageRepository accommodationImageRepository;




    @Transactional  //모든 숙소 목록 조회
    public List<AccommodationFindResponse> getAllAccommodation(){
        List<Accommodation> foundAccommodationList = accommodationRepository.findAll();
        return listToResponse(foundAccommodationList);
    }


    @Transactional  //특정 ID 숙소 조회
    public AccommodationFindResponse getAccommodationById(Long accommodationId){
        Accommodation foundAccommodation = accommodationRepository.findById(accommodationId)
            .orElseThrow();

        List<String> imageList = getImagesForAccommodation(accommodationId);
        return AccommodationFindResponse.fromEntity(foundAccommodation, imageList);
    }


    @Transactional  //카테고리별 숙소 조회
    public List<AccommodationFindResponse> getAccommodationsByCategory(String categoryStr) {
        AccommodationCategory category = AccommodationCategory.valueOf(categoryStr.toUpperCase());

        List<Accommodation> accommodations = accommodationRepository.findByCategory(category);

        return listToResponse(accommodations);
    }

    @Transactional  //국내/국외별 숙소 조회
    public List<AccommodationFindResponse> getAccommodationsByDomestic(boolean isDomestic) {
        List<Accommodation> accommodations = accommodationRepository.findByIsDomestic(isDomestic);
        return listToResponse(accommodations);
    }

    @Transactional  //디폴트(한옥, 국내) 페이지 조회
    public List<AccommodationFindResponse> getAccommodationsByCategoryAndDomestic(String categoryStr, boolean isDomestic) {
        AccommodationCategory category = AccommodationCategory.valueOf(categoryStr.toUpperCase());
        List<Accommodation> accommodations = accommodationRepository.findByCategoryAndIsDomestic(category, isDomestic);
        return listToResponse(accommodations);
    }

    //숙소 별 이미지 리스트(String) 조회 모듈
    private List<String> getImagesForAccommodation(Long accommodationId) {
        List<AccommodationImages> images = accommodationImageRepository.findByAccommodation_AccommodationId(accommodationId);
        List<String> imageList = new ArrayList<>();
        for (AccommodationImages image : images) {
            imageList.add(image.getImage());
        }
        return imageList;
    }


    private List<AccommodationFindResponse> listToResponse(List<Accommodation> accommodationList){
        List<AccommodationFindResponse> responseList = new ArrayList<>();
        for(Accommodation found : accommodationList){
            List<String> imageList = getImagesForAccommodation(found.getAccommodationId());
            List<String> serviceList = Arrays.asList(found.getServiceInfo().split(",")); // 콤마로 구분된 문자열을 리스트로 변환


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
                    .serviceInfoList(serviceList)
                    .accommodationImageList(imageList)
                    .build();

            responseList.add(accommodatonListFindResponse);
        }
        return responseList;
    }



}
