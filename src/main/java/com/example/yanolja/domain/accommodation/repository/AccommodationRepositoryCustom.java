package com.example.yanolja.domain.accommodation.repository;


import com.example.yanolja.domain.accommodation.entity.AccommodationCategory;
import java.time.LocalDate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface AccommodationRepositoryCustom {

    Page<Long> findAccommodationIds(AccommodationCategory category, boolean isDomestic,
        Pageable pageable, LocalDate startDate, LocalDate endDate,int numberOfPeople);
}
