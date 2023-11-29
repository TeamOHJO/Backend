package com.example.yanolja.domain.accommodation.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class IntegratedAccommodationDTO {

  private String title;
  private String addr1;
  private String sigungucode;
  private String areaCode;
  private String subdetailoverview;
  private String roomoffseasonminfee1;
  private String roompeakseasonminfee1;
  private String roombasecount;
  private String roommaxcount;
  private String firstimage;
  private String firstimage2;
  private String roomimg1;
  private String roomimg2;
  private String roomimg3;
  private String roomimg4;
  private String roomimg5;
  private String contentid;
  private String cat1;
  private String cat2;
  private String cat3;
  private String contenttypeid;
  private String createdtime;
  private String modifiedtime;
  private String mapx;
  private String mapy;
  private String tel;
  private String roomTitle;
  private String roomIntro;
  private String infotext;
  private String roombathfacility;
  private String roombath;
  private String roomhometheater;
  private String roomaircondition;
  private String roomtv;
  private String roompc;
  private String roomcable;
  private String roominternet;
  private String roomrefrigerator;
  private List<String> roomImages;
  private List<String> serviceInfo;


}
