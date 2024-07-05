package com.stock.chitchat.post.dto;

import com.stock.chitchat.post.Post;
import com.stock.chitchat.stock.Stock;
import com.stock.chitchat.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostCreateUpdateRequestDTO {
    private String title;
    private String body;

    public Post toEntity(User user, Stock stock) {
        return Post.builder()
                .title(title)
                .body(body)
                .user(user)
                .stock(stock)
                .build();
    }
}
