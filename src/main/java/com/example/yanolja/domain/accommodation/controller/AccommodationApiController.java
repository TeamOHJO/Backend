package com.example.yanolja.domain.accommodation.controller;

import com.example.yanolja.domain.accommodation.service.AccommodationApiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AccommodationApiController {

  private final AccommodationApiService accommodationApiService;


  @GetMapping("/fetch")
  public ResponseEntity<String> fetchAccommodations() {
    accommodationApiService.processSearchStay1Api();
    return ResponseEntity.ok("성공적으로 저장됐습니다!");
  }


}
