package com.stock.chitchat.user;

import com.stock.chitchat.user.dto.JoinDTO;
import com.stock.chitchat.user.dto.UserDetailResponseDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("사용자를 찾을 수 없습니다."));
    }

    @Transactional
    public UUID join(JoinDTO joinDTO) {
        String encodedPassword = passwordEncoder.encode(joinDTO.getPassword());
        User user = joinDTO.toEntity(encodedPassword);
        User savedUser = userRepository.save(user);
        return savedUser.getId();
    }

    public UserDetailResponseDTO findUserDetailBy(UserDetails userDetails) {
        if (null == userDetails) {
            throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
        }
        String email = userDetails.getUsername();
        User user = findUserByEmail(email);
        return UserDetailResponseDTO.fromEntity(user);
    }
}
