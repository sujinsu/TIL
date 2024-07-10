package com.example.structural_pattern._9_decorator.after;

public class TrimmingCommentDecorator extends CommentDecorator {

    public TrimmingCommentDecorator(CommentService commentService) {
        super(commentService);
    }

    @Override
    public void addComment(String comment) {
        super.addComment(trim(comment));
    }

    /**
     * 부가적인 작업 추가
     * @param comment
     * @return
     */
    private String trim(String comment) {
        return comment.replace("...", "");
    }
}
