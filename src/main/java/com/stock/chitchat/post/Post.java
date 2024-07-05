package com.stock.chitchat.post;

import com.stock.chitchat.comment.Comment;
import com.stock.chitchat.common.BaseEntity;
import com.stock.chitchat.stock.Stock;
import com.stock.chitchat.user.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "posts")
@Entity
public class Post extends BaseEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String body;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stock_id")
    private Stock stock;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    private Post(String title, String body, User user, Stock stock) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.stock = stock;
    }

    public void update(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
