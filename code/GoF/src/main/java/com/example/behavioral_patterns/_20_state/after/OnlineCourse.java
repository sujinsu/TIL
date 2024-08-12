package com.example.behavioral_patterns._20_state.after;

import java.util.ArrayList;
import java.util.List;

/**
 * context
 */
public class OnlineCourse {

    private State state = new Draft(this); // 의존하고 있는 타입이 인터페이스 >> 새로운 상태 추가가 쉬움!

    private List<Student> students = new ArrayList<>();

    private List<String> reviews = new ArrayList<>();

    public void addStudent(Student student) {
        this.state.addStudent(student);
    }

    public void addReview(String review, Student student) {
        this.state.addReview(review, student);
    }

    public State getState() {
        return state;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<String> getReviews() {
        return reviews;
    }

    public void changeState(State state) {
        this.state = state;
    }
}
