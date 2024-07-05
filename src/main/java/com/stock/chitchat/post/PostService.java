package com.stock.chitchat.post;

import com.stock.chitchat.post.dto.PostCreateUpdateRequestDTO;
import com.stock.chitchat.post.dto.PostDetailResponseDTO;
import com.stock.chitchat.post.dto.PostPageResponseDTO;
import com.stock.chitchat.stock.Stock;
import com.stock.chitchat.stock.StockService;
import com.stock.chitchat.user.User;
import com.stock.chitchat.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class PostService {
    private final PostRepository postRepository;
    private final StockService stockService;
    private final UserService userService;

    public Post findPostById(Long postId) {
        return postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
    }

    @Transactional
    public Long create(
            PostCreateUpdateRequestDTO requestDTO,
            Long stockId,
            UserDetails userDetails
    ) {
        if (null == userDetails) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        Stock stock = stockService.findStockById(stockId);
        User user = userService.findUserByEmail(userDetails.getUsername());
        Post post = requestDTO.toEntity(user, stock);
        Post savedPost = postRepository.save(post);
        return savedPost.getId();
    }

    public Page<PostPageResponseDTO> getPostsPage(
            Long stockId,
            Pageable pageable
    ) {
        Page<Post> posts = postRepository.findByStockIdOrderByCreatedDateDesc(stockId, pageable);
        List<PostPageResponseDTO> postsPage = posts.get()
                .map(PostPageResponseDTO::fromEntity)
                .toList();
        return new PageImpl<>(postsPage, posts.getPageable(), posts.getTotalPages());
    }

    public PostDetailResponseDTO getPostDetail(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 게시물입니다."));
        return PostDetailResponseDTO.fromEntity(post);
    }

    public void updatePost(Long postId, PostCreateUpdateRequestDTO requestDTO, UserDetails userDetails) {
        if (null == userDetails) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        User user = userService.findUserByEmail(userDetails.getUsername());
        Post post = findPostById(postId);
        if (!user.getId().equals(post.getUser().getId())) {
            throw new IllegalArgumentException("글쓴이만 수정할 수 있습니다.");
        }
        post.update(requestDTO.getTitle(), requestDTO.getBody());
        postRepository.save(post);
    }
}
