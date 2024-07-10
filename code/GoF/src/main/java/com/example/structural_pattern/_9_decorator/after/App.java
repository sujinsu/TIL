package com.example.structural_pattern._9_decorator.after;

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
