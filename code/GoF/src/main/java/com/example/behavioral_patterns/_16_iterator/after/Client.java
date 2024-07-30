package com.example.behavioral_patterns._16_iterator.after;


import com.example.behavioral_patterns._16_iterator.before.Post;

import java.util.Iterator;
import java.util.List;

/**
 * Iterator interface : 클라이언트가 사용할 인터페이스 (어떻게 순회하는지에 대한 방법을 가진 인터페이스)
 * ㄴ getNext() : 다음 element로 return
 * ㄴ hasNext() : 다음 element가 있는지 확인
 *
 * Aggregate inteface (있을 수도 있고, 없을 수도 있음) 여기선 Board
 *
 * (+) : 내부 구조를 몰라도 됨. 순회 로직만 알면 됨. 책임 분리 O (SRP)
 * (+) : 기존 코드 변경이 없진 않지만, client 입장에선 OCP 달성이라 볼 수 있음
*          BUT concrete aggregate는 변경 생길 수도 있음 (새로운 interator 생성 필요시) >> iterator 를 주입받아 사용하는 방식이면 변경 최소화 가능
 */
public class Client {

    public static void main(String[] args) {
        Board board = new Board();
        board.addPost("디자인 패턴 게임");
        board.addPost("선생님, 저랑 디자인 패턴 하나 학습하시겠습니까?");
        board.addPost("지금 이 자리에 계신 여러분들은 모두 디자인 패턴을 학습하고 계신 분들입니다.");

        // TODO 들어간 순서대로 순회하기
        List<Post> posts = board.getPosts(); // 여기 List는 aggregate interface
        Iterator<Post> iterator = posts.iterator(); // Iterator는 iterater interface >> 종류로 arraylistiterator 같은게 concrete iterator
        System.out.println(iterator.getClass());

        for (int i = 0 ; i < posts.size() ; i++) {
            Post post = posts.get(i);
            System.out.println(post.getTitle());
        }

        // TODO 가장 최신 글 먼저 순회하기
        Iterator<Post> recentPostIterator = board.getRecentPostIterator();
        while(recentPostIterator.hasNext()) {
            System.out.println(recentPostIterator.next().getTitle());
        }
    }

}
