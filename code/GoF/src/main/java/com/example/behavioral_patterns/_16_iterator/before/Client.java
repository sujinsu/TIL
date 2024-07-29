package com.example.behavioral_patterns._16_iterator.before;

import java.util.Collections;
import java.util.List;

/**
 * Iterator pattern : 집합 객체의 내부 구조를 노출 시키지 않고 순회하는 방법 제공
 */
public class Client {

    /**
     * Post가 List 라는 것을 Client가 ㅇ라고 있음 >>  나중에 list가 아닌 set 등 인터페이스 변경시 클라이언트 코드에도 영향
     * @param args
     */
    public static void main(String[] args) {
        Board board = new Board();
        board.addPost("디자인 패턴 게임");
        board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
        board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

        // TODO 들어간 순서대로 순회하기
        List<Post> posts = board.getPosts();
        for (int i = 0 ; i < posts.size() ; i++) {
            Post post = posts.get(i);
            System.out.println(post.getTitle());
        }

        // TODO 가장 최신 글 먼저 순회하기
        Collections.sort(posts, (p1, p2) -> p2.getCreatedDateTime().compareTo(p1.getCreatedDateTime()));
        for (int i = 0 ; i < posts.size() ; i++) {
            Post post = posts.get(i);
            System.out.println(post.getTitle());
        }
    }

}
