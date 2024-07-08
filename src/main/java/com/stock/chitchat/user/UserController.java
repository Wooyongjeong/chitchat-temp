package com.stock.chitchat.user;

import com.stock.chitchat.common.CurrentUser;
import com.stock.chitchat.user.dto.JoinDTO;
import com.stock.chitchat.user.dto.UserDetailResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
@RestController
public class UserController {
    private final UserService userService;

    @PostMapping
    public ResponseEntity<UUID> join(@RequestBody JoinDTO joinDTO) {
        UUID userId = userService.join(joinDTO);
        return ResponseEntity.ok(userId);
    }

    @GetMapping("/me")
    public ResponseEntity<UserDetailResponseDTO> getUserDetail(
            @CurrentUser User user
    ) {
        UserDetailResponseDTO data = userService.findUserDetailOf(user);
        return ResponseEntity.ok(data);
    }
}
