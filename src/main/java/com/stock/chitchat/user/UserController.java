package com.stock.chitchat.user;

import com.stock.chitchat.user.dto.JoinDTO;
import com.stock.chitchat.user.dto.LoginDTO;
import com.stock.chitchat.user.dto.UserDetailResponseDTO;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        UserDetailResponseDTO data = userService.findUserDetailBy(userDetails);
        return ResponseEntity.ok(data);
    }
}
