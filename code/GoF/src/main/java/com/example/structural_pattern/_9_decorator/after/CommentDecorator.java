package com.example.structural_pattern._9_decorator.after;

/**
 * 컴포넌트를 감싸고 호출해주는 역할
 * 코멘트 서비스를 가지고 있음 >> 가지고 있는 코멘트 서비스를 호출
 */
public class CommentDecorator implements CommentService {

    private CommentService commentService;

    public CommentDecorator(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    public void addComment(String comment) {
        commentService.addComment(comment);
    }
}
