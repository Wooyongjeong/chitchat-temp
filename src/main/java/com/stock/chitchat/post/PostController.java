package com.stock.chitchat.post;

import com.stock.chitchat.post.dto.PostCreateUpdateRequestDTO;
import com.stock.chitchat.post.dto.PostDetailResponseDTO;
import com.stock.chitchat.post.dto.PostPageResponseDTO;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/v1/stocks/{stockId}/posts")
@RestController
public class PostController {
    private final PostService postService;

    @PostMapping
    public ResponseEntity<Long> createPost(
            @RequestBody PostCreateUpdateRequestDTO requestDTO,
            @PathVariable("stockId") Long stockId,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        Long postId = postService.create(requestDTO, stockId, userDetails);
        return ResponseEntity.ok(postId);
    }

    @GetMapping
    public ResponseEntity<Page<PostPageResponseDTO>> findPostsPage(
            @PathVariable("stockId") Long stockId,
            Pageable pageable
    ) {
        Page<PostPageResponseDTO> data = postService.getPostsPage(stockId, pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<PostDetailResponseDTO> findPostDetail(
            @PathVariable("postId") Long postId
    ) {
        PostDetailResponseDTO data = postService.getPostDetail(postId);
        return ResponseEntity.ok(data);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<String> updatePost(
            @PathVariable("postId") Long postId,
            @RequestBody PostCreateUpdateRequestDTO requestDTO,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        postService.updatePost(postId, requestDTO, userDetails);
        return ResponseEntity.ok("success");
    }
}
