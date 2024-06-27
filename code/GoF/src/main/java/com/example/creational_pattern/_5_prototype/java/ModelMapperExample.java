package com.example.creational_pattern._5_prototype.java;


import com.example.creational_pattern._5_prototype.after.GithubIssue;
import com.example.creational_pattern._5_prototype.after.GithubRepository;
import org.modelmapper.ModelMapper;

public class ModelMapperExample {

    public static void main(String[] args) {
        GithubRepository repository = new GithubRepository();
        repository.setUser("whiteship");
        repository.setName("live-study");

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(1);
        githubIssue.setTitle("1주차 과제: JVM은 무엇이며 자바 코드는 어떻게 실행하는 것인가.");

        // modelmapper의 원리 : reflection > GithubIssueData 과 같은 타켓 클래스의 정보를 리플렉션으로 알아내서 데이터를 알아서 넣어주는 것
        ModelMapper modelMapper = new ModelMapper();
        GithubIssueData githubIssueData = modelMapper.map(githubIssue, GithubIssueData.class);
        System.out.println(githubIssueData);
    }
}
