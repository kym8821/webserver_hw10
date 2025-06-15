package org.webserver.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.webserver.domain.User;
import org.webserver.dto.UserAuthDto;
import org.webserver.repository.UserRepository;

import static org.webserver.dto.UserAuthDto.*;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public UserAuthResponse signInUser(UserAuthRequest userAuthRequest) {
        String username = userAuthRequest.getUsername();
        String password = userAuthRequest.getPassword();
        if(username==null || password==null) {
            return null;
        }
        User user = userRepository.findByUsername(username);
        if (user == null || !user.getPassword().equals(password)) {
            return null;
        }
        return UserAuthResponse.fromUser(user);
    }

    @Transactional
    public UserAuthResponse signUpUser(UserAuthRequest userAuthRequest) {
        String username = userAuthRequest.getUsername();
        String password = userAuthRequest.getPassword();
        if(username==null || password==null) {
            return null;
        }
        User user = userRepository.findByUsername(username);
        if(user!=null) return null;
        User savedUser = userRepository.save(User.builder()
                .username(username)
                .password(password).build());
        return UserAuthResponse.fromUser(savedUser);
    }
}
