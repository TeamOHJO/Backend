package com.example.yanolja.domain.accommodation.controller;

import com.example.yanolja.domain.accommodation.dto.AccommodationFindResponse;
import com.example.yanolja.domain.accommodation.service.AccommodationService;
import com.example.yanolja.global.util.ResponseDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;

    @GetMapping
    public ResponseEntity<ResponseDTO<List<AccommodationFindResponse>>> getAllAccommodation(){
        List<AccommodationFindResponse> accomodations = accommodationService.getAllAccommodation();

        ResponseDTO<List<AccommodationFindResponse>> response = ResponseDTO.<List<AccommodationFindResponse>>builder()
            .code(HttpStatus.OK.value())
            .message("숙박 전체 조회 성공")
            .data(accomodations)
            .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/default")
    public ResponseEntity<ResponseDTO<List<AccommodationFindResponse>>> getDefaultAccommodations() {
        List<AccommodationFindResponse> accommodations = accommodationService.getAccommodationsByCategoryAndDomestic("HOTEL", true);

        ResponseDTO<List<AccommodationFindResponse>> response = ResponseDTO.<List<AccommodationFindResponse>>builder()
            .code(HttpStatus.OK.value())
            .message("디폴트 숙박 조회 성공")
            .data(accommodations)
            .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{accommodation_Id}")
    public ResponseEntity<ResponseDTO<AccommodationFindResponse>> getAccommodationById(
            @PathVariable(name = "accommodation_Id") Long accommodationId
    ){
        AccommodationFindResponse accommodation = accommodationService.getAccommodationById(accommodationId);

        ResponseDTO<AccommodationFindResponse> response = ResponseDTO.<AccommodationFindResponse>builder()
            .code(HttpStatus.OK.value())
            .message("ID로 숙박 조회 성공")
            .data(accommodation)
            .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/category/{category}")
    public ResponseEntity<ResponseDTO<List<AccommodationFindResponse>>> getAccommodationsByCategory(
        @PathVariable("category") String category) {
        List<AccommodationFindResponse> accommodations = accommodationService.getAccommodationsByCategory(category);


        ResponseDTO<List<AccommodationFindResponse>> response = ResponseDTO.<List<AccommodationFindResponse>>builder()
            .code(HttpStatus.OK.value())
            .message("카테고리별로 숙박 조회 성공")
            .data(accommodations)
            .build();
        return ResponseEntity.ok(response);
    }


    @GetMapping("/domestic")
    public ResponseEntity<ResponseDTO<List<AccommodationFindResponse>>> getAccommodationsByDomestic(
        @RequestParam("isDomestic") boolean isDomestic) {
        List<AccommodationFindResponse> accommodations = accommodationService.getAccommodationsByDomestic(isDomestic);

        ResponseDTO<List<AccommodationFindResponse>> response = ResponseDTO.<List<AccommodationFindResponse>>builder()
            .code(HttpStatus.OK.value())
            .message("국내/국외별로 숙박 조회 성공")
            .data(accommodations)
            .build();
        return ResponseEntity.ok(response);
    }




}
