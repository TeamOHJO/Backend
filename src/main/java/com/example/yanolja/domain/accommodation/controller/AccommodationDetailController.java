package com.example.yanolja.domain.accommodation.controller;

import com.example.yanolja.domain.accommodation.dto.AccommodationDetailResponse;
import com.example.yanolja.domain.accommodation.service.AccommodationService;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/accommodation")
@RequiredArgsConstructor
public class AccommodationDetailController {
    private final AccommodationService accommodationService;

    @GetMapping("/{accommodationId}")
    public ResponseEntity<AccommodationDetailResponse> getAccommodationDetail(
        @PathVariable Long accommodationId,
        @AuthenticationPrincipal PrincipalDetails principalDetails) {

        Long userId = principalDetails.getUser().getUserId();
        AccommodationDetailResponse response = accommodationService.getAccommodationDetail(accommodationId, userId);
        return ResponseEntity.ok(response);
    }

}
