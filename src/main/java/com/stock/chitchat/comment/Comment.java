package com.stock.chitchat.comment;

import com.stock.chitchat.common.BaseEntity;
import com.stock.chitchat.post.Post;
import com.stock.chitchat.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, columnDefinition = "text")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @Builder
    private Comment(String body, User user, Post post) {
        this.body = body;
        this.user = user;
        this.post = post;
    }

    public void update(String body) {
        this.body = body;
    }
}
