package com.example.creational_pattern._5_prototype.after;

/**
 * prototype :: 기존 인스턴스를 복제하여 새 인스턴스를 만드는 방법
 * (+) :  DB를 다녀오거나 네트워크를 다녀오느라 리소스 소모가 심한 작업의 경우 복제 사용 이득
 *
 *
 * (+) : 복잡한 객체 만드는 과정을 숨길 수 있다. 비용(시간 또는 메모리) 적인 면에서 효율적일 수도 있다. 추상적인 타입을 리턴할 수 있다
 * (-) : 복잡한 객체를 만드는 과정 자체가 복잡할 수 있다 (특히, 순환 참조가 있는 경우)
 *
 */
public class App {

    public static void main(String[] args) throws CloneNotSupportedException {
        GithubRepository repository = new GithubRepository();
        repository.setUser("whiteship");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

        String url = githubIssue.getUrl();
        System.out.println(url);

        GithubIssue clone = (GithubIssue) githubIssue.clone(); // clone 메서드를 만들었다고 가정
        System.out.println(clone.getUrl());

        repository.setName("sujin");

        System.out.println(clone != githubIssue);
        System.out.println(clone.equals(githubIssue));
        System.out.println(clone.getClass() == githubIssue.getClass());

        // 얕은 복사 관련 ->  clone 메서드 수정하여 deep copy
        // >  원래의 객체의 정보가 바뀌어도 clone 객체는 복사될 때의 정보 유지
        System.out.println(clone.getRepository() == githubIssue.getRepository());

        System.out.println(clone.getUrl());

        // TODO clone != githubIssue
        // TODO clone.equals(githubIssue) => true
    }

}
