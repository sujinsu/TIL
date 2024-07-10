package com.example.structural_pattern._9_decorator.after;

/**
 * concrete component
 */
public class DefaultCommentService implements CommentService {
    @Override
    public void addComment(String comment) {
        System.out.println(comment);
    }
}
