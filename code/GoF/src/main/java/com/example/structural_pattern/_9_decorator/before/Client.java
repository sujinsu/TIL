package com.example.structural_pattern._9_decorator.before;

/**
 * decorator pattern의 장점 : 기존 코드를 변경하지 않고 부가적인 기능 추가 가능
 *    ㄴ 스태틱하게 컴파일 시점에 추가 x, 다이나믹하게 런타임에 추가 o
 */
public class Client {

    private CommentService commentService; // 데코레이터 패턴을 위해선 인터페이스로 만듦

    // 서비스안에서 하는 일은 concrete component로 옮겨갈 것
    // 그 다음 스팸필터 코멘트 서비스와 트리밍 코멘트 서비스 추상화 시킨 데코레이터를 새로 만듦
    public Client(CommentService commentService) {
        this.commentService = commentService;
    }

    private void writeComment(String comment) {
        commentService.addComment(comment);
    }

    public static void main(String[] args) {
//        Client client = new Client(new TrimmingCommentService()); // 기존 코드 변경하지 않고 추가한거긴 함 다만 쉽지 않음

        // 상속은 단일 상속 만 가능 그래서 스팸, 트림 둘 다 할 수 없음 -> TrimAndSpamFilteringService 만들어야함
        // 그리고 이제 1. trim 만, 2. Spam만, 3. 둘 다 셋의 경우 고려 .
        // 기능 하나 추가될 때마다 더 복잡해짐
        Client client = new Client(new SpamFilteringCommentService());
        client.writeComment("오징어게임");
        client.writeComment("보는게 하는거 보다 재밌을 수가 없지...");
        client.writeComment("http://whiteship.me");
    }

}
