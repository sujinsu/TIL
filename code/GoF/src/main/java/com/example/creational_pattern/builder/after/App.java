package com.example.creational_pattern.builder.after;

import com.example.creational_pattern.builder.before.TourPlan;

import java.time.LocalDate;

/**
 * Builder
 * (+) : 복잡한 개체를 순차적으로 만들 수 있는 방법 제공 (생성자 보다) / 구체적인 과정을 숨길 수 있음 / 불완전한 객체를 만드는 것을 막음(getPlan 호출 전까지 인스턴스 생성 불가)
 * (-) : 디렉터 or 빌더까지 만들어야 함 / 구조가 복잡
 */
public class App {

    public static void main(String[] args) {
//        TourPlanBuilder builder = new DefaultTourBuilder();
//        TourPlan plan = builder.title("칸쿤 여행")
//                .nightsAndDays(2,3)
//                .startDate(LocalDate.of(2020,12,9))
//                .whereToStay("리조트")
//                .addPlan(0, "체크인하고 짐 풀기")
//                .addPlan(0, "저녁 식사")
//                .getPlan();

        TourDirector director = new TourDirector(new DefaultTourBuilder());
        TourPlan tourPlan = director.cancunTrip();
        TourPlan tourPlan1 = director.longBeachTrip();

    }
}
