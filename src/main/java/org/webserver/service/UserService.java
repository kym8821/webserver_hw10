package org.webserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webserver.domain.User;
import org.webserver.dto.UserAuthDto;
import org.webserver.dto.UserResponse;
import org.webserver.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserResponse signInUser(UserAuthDto userAuthDto) {
        String username = userAuthDto.getUsername();
        String password = userAuthDto.getPassword();
        if(username==null || password==null) {
            return null;
        }
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return UserResponse.fromUser(user);
    }

    @Transactional
    public UserResponse signUpUser(UserAuthDto userAuthDto) {
        String username = userAuthDto.getUsername();
        String password = userAuthDto.getPassword();
        if(username==null || password==null) {
            return null;
        }
        User user = userRepository.findByUsername(username);
        if(user!=null) return null;
        User savedUser = userRepository.save(User.builder()
                .username(username)
                .password(password).build());
        return UserResponse.fromUser(savedUser);
    }
}
