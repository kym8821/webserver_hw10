package org.webserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webserver.domain.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    Long userId;
    String username;

    public static UserResponse fromUser(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername()).build();
    }
}
