package com.example.structural_pattern._9_decorator.after;

public class SpamFilteringCommentDecorator extends CommentDecorator {

    public SpamFilteringCommentDecorator(CommentService commentService) {
        super(commentService);
    }

    @Override
    public void addComment(String comment) {
        if (isNotSpam(comment)) {
            super.addComment(comment);
        }
    }

    /**
     * 부가적인 기능 추가
     * @param comment
     * @return
     */
    private boolean isNotSpam(String comment) {
        return !comment.contains("http");
    }
}
