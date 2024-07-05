package com.stock.chitchat.post.dto;

import com.stock.chitchat.post.Post;
import com.stock.chitchat.user.User;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostPageResponseDTO {
    private Long postId;
    private String title;
    private UUID userId;
    private String userName;
    private Integer commentCount;
    private LocalDateTime createdDate;

    public static PostPageResponseDTO fromEntity(Post post) {
        return PostPageResponseDTO.builder()
                .postId(post.getId())
                .title(post.getTitle())
                .userId(post.getUser().getId())
                .userName(post.getUser().getName())
                .commentCount(post.getComments().size())
                .createdDate(post.getCreatedDate())
                .build();
    }
}
