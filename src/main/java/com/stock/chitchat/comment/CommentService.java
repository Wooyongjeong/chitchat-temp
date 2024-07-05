package com.stock.chitchat.comment;

import com.stock.chitchat.comment.dto.CommentCreateUpdateRequestDTO;
import com.stock.chitchat.comment.dto.CommentPageResponseDTO;
import com.stock.chitchat.post.Post;
import com.stock.chitchat.post.PostService;
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
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostService postService;
    private final UserService userService;

    public Comment findCommentById(Long commentId) {
        return commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 댓글입니다."));
    }

    public Page<CommentPageResponseDTO> getCommentsPage(
            Long postId,
            Pageable pageable
    ) {
        Page<Comment> comments = commentRepository.findByPostId(postId, pageable);
        List<CommentPageResponseDTO> commentsPage = comments.get()
                .map(CommentPageResponseDTO::fromEntity)
                .toList();
        return new PageImpl<>(commentsPage, comments.getPageable(), comments.getTotalElements());
    }

    @Transactional
    public Long createComment(Long postId, CommentCreateUpdateRequestDTO requestDTO, UserDetails userDetails) {
        if (null == userDetails) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        User user = userService.findUserByEmail(userDetails.getUsername());
        Post post = postService.findPostById(postId);
        Comment comment = requestDTO.toEntity(user, post);
        commentRepository.save(comment);
        return comment.getId();
    }

    @Transactional
    public void updateComment(Long commentId, CommentCreateUpdateRequestDTO requestDTO, UserDetails userDetails) {
        if (null == userDetails) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }

        User user = userService.findUserByEmail(userDetails.getUsername());
        Comment comment = findCommentById(commentId);
        if (!user.getId().equals(comment.getUser().getId())) {
            throw new IllegalArgumentException("작성자만 댓글을 수정할 수 있습니다.");
        }
        comment.update(requestDTO.getBody());
        commentRepository.save(comment);
    }
}
