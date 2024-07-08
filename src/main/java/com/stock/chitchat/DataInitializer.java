package com.stock.chitchat;

import com.stock.chitchat.comment.Comment;
import com.stock.chitchat.comment.CommentRepository;
import com.stock.chitchat.post.Post;
import com.stock.chitchat.post.PostRepository;
import com.stock.chitchat.stock.Sector;
import com.stock.chitchat.stock.Stock;
import com.stock.chitchat.stock.StockRepository;
import com.stock.chitchat.user.RoleType;
import com.stock.chitchat.user.User;
import com.stock.chitchat.user.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataInitializer {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @PostConstruct
    public void init() {
        User user = User.builder()
                .name("overflow")
                .email("admin@admin.com")
                .password(passwordEncoder.encode("admin"))
                .roleType(RoleType.ADMIN)
                .build();
        userRepository.save(user);

        Stock stock = Stock.builder()
                .name("NVIDIA")
                .ticker("NVDA")
                .sector(Sector.INFORMATION_TECHNOLOGY)
                .build();
        stockRepository.save(stock);

        Post post = Post.builder()
                .title("150 가보자가보자")
                .body("황형은 신이야")
                .user(user)
                .stock(stock)
                .build();
        postRepository.save(post);

        Comment comment = Comment.builder()
                .body("\uD83D\uDCC8\uD83D\uDCC8")
                .user(user)
                .post(post)
                .build();
        commentRepository.save(comment);
    }
}
