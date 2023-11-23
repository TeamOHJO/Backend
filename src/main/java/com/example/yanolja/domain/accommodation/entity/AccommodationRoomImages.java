package com.example.yanolja.domain.accommodation.entity;

import com.example.yanolja.global.entity.BaseTimeEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class AccommodationRoomImages extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "imageId")
  private String imageId;

  @Column(name = "image", nullable = false)
  private String image;

  @ManyToOne
  @JoinColumn(name = "roomId", nullable = false)
  private AccommodationRooms accommodationRooms;

}
