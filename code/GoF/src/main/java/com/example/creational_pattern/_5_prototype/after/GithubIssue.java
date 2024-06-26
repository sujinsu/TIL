package com.example.creational_pattern._5_prototype.after;

import java.util.Objects;

/**
 * 클론 지원하고자 하면 반드시 구현
 */
public class GithubIssue implements Cloneable{

    private int id;

    private String title;

    private GithubRepository repository;

    public GithubIssue(GithubRepository repository) {
        this.repository = repository;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public GithubRepository getRepository() {
        return repository;
    }

    public String getUrl() {
        return String.format("https://github.com/%s/%s/issues/%d",
                repository.getUser(),
                repository.getName(),
                this.getId());
    }


    @Override
    protected Object clone() throws CloneNotSupportedException {
//        return super.clone(); // 기존 얕은 복사

        // deep copy ->  원래의 객체의 정보가 바뀌어도 clone 객체는 복사될 때의 정보 유지
        GithubRepository repository = new GithubRepository();
        repository.setUser(this.repository.getUser());
        repository.setName(this.repository.getName());

        GithubIssue githubIssue = new GithubIssue(repository);
        githubIssue.setId(this.id);
        githubIssue.setTitle(this.title);

        return githubIssue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GithubIssue that = (GithubIssue) o;
        return id == that.id && Objects.equals(title, that.title) && Objects.equals(repository, that.repository);
    }


    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
