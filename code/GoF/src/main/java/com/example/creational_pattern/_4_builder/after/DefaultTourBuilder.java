package com.example.creational_pattern._4_builder.after;

import com.example.creational_pattern._4_builder.before.DetailPlan;
import com.example.creational_pattern._4_builder.before.TourPlan;

import java.time.LocalDate;
import java.util.ArrayList;

public class DefaultTourBuilder implements TourPlanBuilder{

    private TourPlan tourPlan;
    public TourPlanBuilder newInstance(){
        this.tourPlan = new TourPlan();
        return this;
    }

//    private String title;
//    private int nights;
//    private int days;
//    private LocalDate startDate;
//    private String whereToStay;
//    private List<DetailPlan> plans;

    @Override
    public TourPlanBuilder nightsAndDays(int nights, int days) {
        this.tourPlan.setNights(nights);
        this.tourPlan.setDays(days);
        return this;
    }

    @Override
    public TourPlanBuilder title(String title) {
        this.tourPlan.setTitle(title);
//        this.title = title;
        return this;
    }

    @Override
    public TourPlanBuilder startDate(LocalDate startDate) {
        this.tourPlan.setStartDate(startDate);
//        this.startDate = startDate;
        return this;
    }

    @Override
    public TourPlanBuilder whereToStay(String whereToStay) {
        this.tourPlan.setWhereToStay(whereToStay);
//        this.whereToStay = whereToStay;
        return this;
    }

    @Override
    public TourPlanBuilder addPlan(int day, String plan) {
        if (this.tourPlan.getPlans() == null) {
            this.tourPlan.setPlans( new ArrayList<>());
        }

        this.tourPlan.getPlans().add(new DetailPlan(day, plan));
        return this;
    }

    @Override
    public TourPlan getPlan() {
        return new TourPlan(this.tourPlan.getTitle(), this.tourPlan.getNights(),this.tourPlan.getDays(),this.tourPlan.getStartDate(),this.tourPlan.getWhereToStay(), this.tourPlan.getPlans());
    }
}
