package com.example.mediaScocial.services;

import com.example.mediaScocial.Enity.Comment;

public interface commentService {

    public Comment createComment(Comment comment,
            Integer userId,
            Integer PostId) throws Exception;

    public Comment likeComment(Integer commentId,
            Integer UserID) throws Exception;

    public Comment findCommentById(Integer commentId) throws Exception;
}
