package com.example.yanolja.domain.accommodation.service;

import com.example.yanolja.domain.accommodation.dto.IntegratedAccommodationDTO;
import com.example.yanolja.domain.accommodation.entity.Accommodation;
import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import com.example.yanolja.domain.accommodation.entity.AccommodationImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRoomImages;
import com.example.yanolja.domain.accommodation.entity.AccommodationRooms;
import com.example.yanolja.domain.accommodation.repository.AccommodationImageRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomImagesRepository;
import com.example.yanolja.domain.accommodation.repository.AccommodationRoomRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
@RequiredArgsConstructor
public class AccommodationApiService {

  private final AccommodationRepository accommodationRepository;
  private final AccommodationRoomRepository accommodationRoomRepository;
  private final AccommodationImageRepository accommodationImageRepository;
  private final AccommodationRoomImagesRepository accommodationRoomImagesRepository;
  private final RestTemplate restTemplate = new RestTemplate();

  private final String apiUrl = "https://apis.data.go.kr/B551011/KorService1/searchStay1?numOfRows=100&pageNo=1&MobileOS=ETC&MobileApp=TestApp&_type=json&arrange=D&serviceKey=xdzR0MEh2Mf8PxUEnYyM9djcwiBAuT22jXTmsI8Aj1Wf4iEkZPLk1K4EI4bRnFZiige6WMBTLmWgzf6onKh59Q==";
  private final String apiKey = "xdzR0MEh2Mf8PxUEnYyM9djcwiBAuT22jXTmsI8Aj1Wf4iEkZPLk1K4EI4bRnFZiige6WMBTLmWgzf6onKh59Q==";


  @Transactional  //DetailInfo1 API에 대한 처리 요청 처리 모듈
  public void processDetailInfo1(String contentId, Accommodation accommodation) {
    String url = "https://apis.data.go.kr/B551011/KorService1/detailInfo1?MobileOS=ETC&MobileApp=testApp&_type=json&contentId="
        + contentId
        + "&contentTypeId=32&numOfRows=100&pageNo=1&serviceKey=" + apiKey;
    ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);

    if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
      try {
        JSONObject json = new JSONObject(response.getBody());
        JSONObject body = json.getJSONObject("response").getJSONObject("body");
        Object itemsObj = body.get("items");

        if (itemsObj instanceof JSONObject && ((JSONObject) itemsObj).has("item")) {
          JSONArray items = ((JSONObject) itemsObj).getJSONArray("item");
          for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            System.out.println("API Item: " + item.toString());  //API 응답 내용 확인

            IntegratedAccommodationDTO dto = parseJsonToDto(item);

            saveAccommodationRooms(dto, accommodation);
          }
        } else {
          System.out.println("DetailInfo1 API 응답에서 항목을 찾을 수 없거나 예상치 못한 형식입니다.");
        }
      } catch (JSONException e) {
        System.out.println("DetailInfo1 API 호출에서 JSON 파싱 오류: " + e.getMessage());
      }
    } else {
      System.out.println("DetailInfo1 API 호출 오류: " + response.getStatusCode());
    }

  }


  @Transactional  //Stay1 API에 대한 요청 처리 모듈
  public void processSearchStay1Api () {
    try {
      ResponseEntity<String> response = restTemplate.getForEntity(apiUrl, String.class);

      if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {

        JSONObject json = new JSONObject(response.getBody());
        JSONArray items = json.getJSONObject("response").getJSONObject("body")
            .getJSONObject("items").getJSONArray("item");

        for (int i = 0; i < items.length(); i++) {
          JSONObject item = items.getJSONObject(i);
          System.out.println("API Item: " + item.toString());  //API 응답 내용 확인
          IntegratedAccommodationDTO dto = parseJsonToDto(item);
          Accommodation accommodation = saveAccommodation(dto);
          processDetailInfo1(dto.getContentid(), accommodation);

          //todo 방금 저장된 accommodation 데이터의 serviceinfo 를 dto로 채우기 코드
        }
      } else {
        System.out.println("SearchStay1 API 호출 오류");
      }

    } catch (Exception e) {
      System.out.println("API 호출 중 예외 발생: " + e.getMessage());
    }
  }


  @Transactional //Json 에서 Dto 로 파싱 함수
  public IntegratedAccommodationDTO parseJsonToDto(JSONObject item) {
    List<String> serviceInfo = new ArrayList<>();

    if("Y".equals(item.optString("roombathfacility"))) serviceInfo.add("목욕시설");
    if("Y".equals(item.optString("roombath"))) serviceInfo.add("욕조");
    if("Y".equals(item.optString("roomhometheater"))) serviceInfo.add("홈시어터");
    if("Y".equals(item.optString("roomaircondition"))) serviceInfo.add("에어컨");
    if("Y".equals(item.optString("roomtv"))) serviceInfo.add("TV");
    if("Y".equals(item.optString("roompc"))) serviceInfo.add("컴퓨터");
    if("Y".equals(item.optString("roomcable"))) serviceInfo.add("케이블");
    if("Y".equals(item.optString("roominternet"))) serviceInfo.add("인터넷");
    if("Y".equals(item.optString("roomrefrigerator"))) serviceInfo.add("냉장고");

    List<String> roomImageUrls = new ArrayList<>();
    for (int i = 1; i <= 5; i++) {
      String imageUrl = item.optString("roomimg" + i);
      if (imageUrl != null && !imageUrl.isEmpty()) {
        roomImageUrls.add(imageUrl);
      }
    }

    return IntegratedAccommodationDTO.builder()
        .title(item.optString("title"))
        .addr1(item.optString("addr1"))
        .roomTitle(item.optString("roomtitle"))
        .roomoffseasonminfee1((item.optString("roomoffseasoninfee1","0")))
        .roomIntro(item.optString("roomtintro"))
        .roombasecount((item.optString("roombasecount")))
        .roommaxcount((item.optString("roommaxcount")))
        .firstimage(item.optString("firstimage"))
        .firstimage2(item.optString("firstimage2"))
        .roomImages(roomImageUrls)
        .serviceInfo(serviceInfo)
        .infotext(item.optString("infotext"))
        .contentid(item.optString("contentid"))
        .createdtime(item.optString("createdtime"))
        .modifiedtime(item.optString("modifiedtime"))
        .build();

  }


  @Transactional // 공통 DTO로부터 Accommodation에 맞는 필드 매핑 후 엔티티 생성
  public Accommodation saveAccommodation(IntegratedAccommodationDTO dto) {
    // DTO 필드에서 null 체크 및 기본값 설정
    String combinedServiceInfo = String.join(", ", dto.getServiceInfo());
    String explanation = dto.getSubdetailoverview() != null ? dto.getSubdetailoverview() : "기본 설명";
    String cancelInfo = "기본 취소 정보";
    String useGuide = "기본 사용 안내";
    String reservationNotice = "기본 예약 안내";

    Accommodation accommodation = Accommodation.builder()
        .accommodationName(dto.getTitle())
        .location(dto.getAddr1())
        .isDomestic(true)
        .category(AccommodationCategory.HOTEL)
        .explanation(explanation)
        .cancelInfo(cancelInfo)
        .useGuide(useGuide)
        .reservationNotice((reservationNotice))
        .serviceInfo(combinedServiceInfo)
        .build();

    accommodationRepository.save(accommodation);


    if (!dto.getFirstimage().isEmpty()) {
      SaveAccommodationImage(accommodation, dto.getFirstimage());
    }
    if (!dto.getFirstimage2().isEmpty()) {
      SaveAccommodationImage(accommodation, dto.getFirstimage2());
    }
    return accommodation;
  }


  @Transactional// 공통 DTO로부터 AccommodationRooms에 맞는 필드 매핑 후 엔티티 생성
  public void saveAccommodationRooms(IntegratedAccommodationDTO dto, Accommodation accommodation) {
    // DTO 필드에서 null 체크 및 기본값 설정
    String combinedServiceInfo = String.join(", ", dto.getServiceInfo());
    String roomTitle = dto.getRoomTitle() != null ? dto.getRoomTitle() : "기본 방 제목";
    String subdetailoverview = dto.getSubdetailoverview() != null ? dto.getSubdetailoverview() : "기본 방 설명";
    int minCapacity = (dto.getRoombasecount() != null && !dto.getRoombasecount().isEmpty() && dto.getRoombasecount().matches("\\d+")) ? Integer.parseInt(dto.getRoombasecount()) : 1;
    int maxCapacity = (dto.getRoommaxcount() != null && !dto.getRoommaxcount().isEmpty() && dto.getRoommaxcount().matches("\\d+")) ? Integer.parseInt(dto.getRoommaxcount()) : 2;
    int price = (dto.getRoomoffseasonminfee1() != null && !dto.getRoomoffseasonminfee1().isEmpty() && dto.getRoomoffseasonminfee1().matches("\\d+")) ? Integer.parseInt(dto.getRoomoffseasonminfee1()) : 0;

    AccommodationRooms accommodationRoom = AccommodationRooms.builder()
        .accommodation(accommodation)
        .name(roomTitle)
        .price(price)
        .minCapacity(minCapacity)
        .maxCapacity(maxCapacity)
        .explanation(subdetailoverview)
        .checkinExplanation(dto.getInfotext())
        .serviceInfo(combinedServiceInfo)
        .build();

    accommodationRoomRepository.save(accommodationRoom);

    SaveRoomImages(accommodationRoom, dto);
    //todo price 입력값 0
  }


  @Transactional
  public void SaveAccommodationImage(Accommodation accommodation, String imageUrl) {
    AccommodationImages accommodationImage = AccommodationImages.builder()
        .accommodation(accommodation)
        .image(imageUrl)
        .build();

    accommodationImageRepository.save(accommodationImage);
  }


  @Transactional
  public void SaveRoomImages(AccommodationRooms accommodationRoom, IntegratedAccommodationDTO dto) {
    List<String> roomImageUrls = Arrays.asList(dto.getRoomimg1(), dto.getRoomimg2(), dto.getRoomimg3(), dto.getRoomimg4(), dto.getRoomimg5());
    for (String imageUrl : roomImageUrls) {
      if (imageUrl != null && !imageUrl.isEmpty()) {
        AccommodationRoomImages accommodationRoomImage = AccommodationRoomImages.builder()
            .accommodationRooms(accommodationRoom)
            .image(imageUrl)
            .build();

        accommodationRoomImagesRepository.save(accommodationRoomImage);
      }
    }
  }

}