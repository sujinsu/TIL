package com.example.structural_pattern._9_decorator.before;

/**
 * 출력하기 전에 trim 하는 서비스
 * ㄴ 이것도 기존 코드 변경하지 않고 추가한거긴 함
 */
public class TrimmingCommentService extends CommentService {

    @Override
    public void addComment(String comment) {
        super.addComment(trim(comment));
    }

    private String trim(String comment) {
        return comment.replace("...", "");
    }

}
