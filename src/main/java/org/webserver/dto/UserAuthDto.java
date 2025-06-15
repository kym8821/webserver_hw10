package org.webserver.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.webserver.domain.User;

public class UserAuthDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserAuthRequest {
        String username;
        String password;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserAuthResponse {
        Long userId;
        String username;

        public static UserAuthResponse fromUser(User user) {
            return UserAuthResponse.builder()
                    .userId(user.getUserId())
                    .username(user.getUsername()).build();
        }
    }

}
