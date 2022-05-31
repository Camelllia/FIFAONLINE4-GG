package com.FIFAONLINE4.GG.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserApiClient userApiClient;

    public UserResponseDto searchUserInfo(String nickname) {
        return userApiClient.requestUserInfo(nickname);
    }
}
