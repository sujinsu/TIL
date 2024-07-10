package com.example.structural_pattern._9_decorator.after;

/**
 * Decorator Pattern
 * (+) : (단일 책임 원칙) - 조합해서 쓸 수 있음. 각자 해야할 일만 하고 있음.
 * (+) : (Open Closed :확장에는 열려있고, 변경에는 닫힘) - 런타임에 동적으로 기능 변경 가능 (상속이면 정적으로 해당 클래스를 만들어놔야 함 ex.trimAndSpam.. )
 * (+) : (DI : 의존성 역전) - client에 interface 사용
 * (-) : 데코레이터 조합 코드가 복잡해질 수 있음 (기능이 추가될 수록 단점 아닐지도?)
 */
public class App {

    private static boolean enabledSpamFilter = true;

    private static boolean enabledTrimming = true;

    public static void main(String[] args) {
        CommentService commentService = new DefaultCommentService(); // 기본적으로 사용

        // 구분값을 통해 런타임에 적용
        // 상속을 사용했을 때와 달리 위임은 추가적으로 데코레이터를 감싸서 처리 ㅇ
        if (enabledSpamFilter) {
            commentService = new SpamFilteringCommentDecorator(commentService);
        }

        if (enabledTrimming) {
            commentService = new TrimmingCommentDecorator(commentService);
        }

        Client client = new Client(commentService);
        client.writeComment("오징어게임");
        client.writeComment("보는게 하는거 보다 재밌을 수가 없지...");
        client.writeComment("http://whiteship.me");
    }
}
