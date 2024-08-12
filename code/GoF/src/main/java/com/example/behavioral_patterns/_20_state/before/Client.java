package com.example.behavioral_patterns._20_state.before;

/**
 * State pattern : 상태에 따라 행동이 달라지는 객체를 위한 패턴
 *
 * ex) 티비가 꺼져있을 때는 리모컨 어떤 버튼을 눌러도 소용 없음.
 */
public class Client {

    public static void main(String[] args) {
        Student student = new Student("whiteship");
        OnlineCourse onlineCourse = new OnlineCourse();

        Student keesun = new Student("keesun");
        keesun.addPrivateCourse(onlineCourse);

        onlineCourse.addStudent(student);
        onlineCourse.changeState(OnlineCourse.State.PRIVATE);

        onlineCourse.addStudent(keesun);

        onlineCourse.addReview("hello", student);

        System.out.println(onlineCourse.getState());
        System.out.println(onlineCourse.getStudents());
        System.out.println(onlineCourse.getReviews());
    }
}
