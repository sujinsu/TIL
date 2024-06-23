package com.example.creational_pattern.builder.after;

import com.example.creational_pattern.builder.before.TourPlan;

import java.time.LocalDate;

/**
 * TourPlanBuilder 을 return type -> title 메서드 호출 후 응답 받은 빌더로 또 다른 메서드 호출 가능
 */
public interface TourPlanBuilder {

    TourPlanBuilder newInstance();
    TourPlanBuilder title(String title);

    TourPlanBuilder nightsAndDays(int nights, int days);

    TourPlanBuilder startDate(LocalDate localDate);

    TourPlanBuilder whereToStay(String whereToStay);

    TourPlanBuilder addPlan(int day, String plan);

    /**
     * 데이터 검증 가능 (ex. nights는 세팅하고 days는 세팅 안됨, 장거리인데 stay 값이 없진 않은지)
     * @return
     */
    TourPlan getPlan();
}
