package com.example.yanolja.domain.accommodation.controller;

import com.example.yanolja.domain.accommodation.dto.AccommodationDetailResponse;
import com.example.yanolja.domain.accommodation.dto.RoomDetail;
import com.example.yanolja.domain.accommodation.service.AccommodationDetailsService;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation/detail")
@RequiredArgsConstructor
public class AccommodationDetailController {

    private final AccommodationDetailsService accommodationDetailService;

    @GetMapping("/{accommodationId}/{maxCapacity}")
    public ResponseEntity<ResponseDTO<AccommodationDetailResponse>> getAccommodationDetail(
        @PathVariable Long accommodationId,
        @PathVariable int maxCapacity,
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate,
        @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = (principalDetails != null) ? principalDetails.getUser().getUserId() : null;
        AccommodationDetailResponse accommodationDetail = accommodationDetailService.getAccommodationDetail(
            accommodationId, userId, maxCapacity, startDate, endDate);
        ResponseDTO<AccommodationDetailResponse> response = ResponseDTO.<AccommodationDetailResponse>builder()
            .code(HttpStatus.OK.value())
            .message("숙박 상세 정보 조회 성공")
            .data(accommodationDetail)
            .build();

        return ResponseEntity.ok(response);
    }


    @GetMapping("/room/{roomId}")
    public ResponseEntity<ResponseDTO<RoomDetail>> getRoomDetail(
        @PathVariable Long roomId,
        @AuthenticationPrincipal PrincipalDetails principalDetails) {
        Long userId = (principalDetails != null) ? principalDetails.getUser().getUserId() : null;
        RoomDetail roomDetail = accommodationDetailService.getRoomDetail(roomId);

        ResponseDTO<RoomDetail> response = ResponseDTO.<RoomDetail>builder()
            .code(HttpStatus.OK.value())
            .message("방 상세 정보 조회 성공")
            .data(roomDetail)
            .build();

        return ResponseEntity.ok(response);
    }


}
