package com.example.yanolja.domain.accommodation.controller;

import com.example.yanolja.domain.accommodation.dto.AccommodationFindResponse;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.service.AccommodationService;
import com.example.yanolja.global.springsecurity.PrincipalDetails;
import com.example.yanolja.global.util.ResponseDTO;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder.Default;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/accommodation")
public class AccommodationController {

    private final AccommodationService accommodationService;
    public static final int DEFAULT_PAGE_SIZE = 16;

    //메인페이지 메인 API
    @GetMapping("")
    public ResponseEntity<ResponseDTO<List<AccommodationFindResponse>>> getAccommodationsInMainPage(
        @AuthenticationPrincipal PrincipalDetails principalDetails,
        @RequestParam AccommodationCategory category,
        @RequestParam boolean isDomestic,
        @RequestParam LocalDate startDate,
        @RequestParam LocalDate endDate,
        @RequestParam int numberOfPeople,
        @PageableDefault(size = DEFAULT_PAGE_SIZE) Pageable pageable) {

        List<AccommodationFindResponse> accommodations = accommodationService.getAccommodationsInMainPage(
            principalDetails, category, isDomestic, pageable,
            startDate, endDate, numberOfPeople
        );
        return ResponseEntity.ok(
            ResponseDTO.res(HttpStatus.OK, "카테고리와 국내/국외별 숙소 조회 성공", accommodations));
    }
}
