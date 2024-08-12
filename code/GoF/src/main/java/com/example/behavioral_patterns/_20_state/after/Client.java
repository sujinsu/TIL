package com.example.behavioral_patterns._20_state.after;

/**
 * State pattern
 *
 * (+) : 상태에 따른 동작을 개별 클래스로 옮겨서 관리 가능 >> Single Responsibility principle
 * (+) : 기존 특정 상태에 따른 동작 변경하지 않고, 새로운 상태 다른 동작 추가 가능
 * (+) : 코드 복잡도 줄임
 *
 * (-) : 복잡도 증가 (상태 종류가 많지 않을 땐 굳이 일수도)
 */
public class Client {

    public static void main(String[] args) {
        OnlineCourse onlineCourse = new OnlineCourse();
        Student student = new Student("whiteship");
        Student keesun = new Student("keesun");
        keesun.addPrivate(onlineCourse);

        onlineCourse.addStudent(student);

        onlineCourse.changeState(new Private(onlineCourse));

        onlineCourse.addReview("hello", student);

        onlineCourse.addStudent(keesun);

        System.out.println(onlineCourse.getState());
        System.out.println(onlineCourse.getReviews());
        System.out.println(onlineCourse.getStudents());
    }
}
