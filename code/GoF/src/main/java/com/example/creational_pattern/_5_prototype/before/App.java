package com.example.creational_pattern._5_prototype.before;

/**
 * prototype :: 기존 인스턴스를 복제하여 새 인스턴스를 만드는 방법
 */
public class App {

    public static void main(String[] args) {
        GithubRepository repository = new GithubRepository();
        repository.setUser("whiteship");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

        String url = githubIssue.getUrl();
        System.out.println(url);

//        GithubIssue clone  = githubIssue.clone(); // clone 메서드를 만들었다고 가정
        // TODO clone != githubIssue
        // TODO clone.equals(githubIssue) => true
    }

}
