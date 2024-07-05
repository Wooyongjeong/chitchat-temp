package com.stock.chitchat.comment.dto;

import com.stock.chitchat.comment.Comment;
import com.stock.chitchat.post.Post;
import com.stock.chitchat.user.User;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentCreateUpdateRequestDTO {
    private String body;

    public Comment toEntity(User user, Post post) {
        return Comment.builder()
                .body(body)
                .user(user)
                .post(post)
                .build();
    }
}
