package com.stock.chitchat.post.dto;

import com.stock.chitchat.post.Post;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostDetailResponseDTO {
    private String title;
    private String body;
    private UUID userId;
    private String userName;
    private LocalDateTime createdDate;

    public static PostDetailResponseDTO fromEntity(Post post) {
        return PostDetailResponseDTO.builder()
                .title(post.getTitle())
                .body(post.getBody())
                .userId(post.getUser().getId())
                .userName(post.getUser().getName())
                .createdDate(post.getCreatedDate())
                .build();
    }
}
