package com.stock.chitchat.comment.dto;

import com.stock.chitchat.comment.Comment;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentPageResponseDTO {
    private Long commentId;
    private String body;
    private UUID userId;
    private String userName;
    private LocalDateTime createdDate;

    public static CommentPageResponseDTO fromEntity(Comment comment) {
        return CommentPageResponseDTO.builder()
                .commentId(comment.getId())
                .body(comment.getBody())
                .userId(comment.getUser().getId())
                .userName(comment.getUser().getName())
                .createdDate(comment.getCreatedDate())
                .build();
    }
}
