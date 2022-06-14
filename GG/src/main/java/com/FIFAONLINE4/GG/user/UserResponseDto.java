package com.FIFAONLINE4.GG.user;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserResponseDto {

    private String accessId;

    private String nickname;

    private int level;
}
