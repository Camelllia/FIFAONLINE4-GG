package com.FIFAONLINE4.GG.user;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserDivisionResponseDto {

    private int matchType;

    private int division;

    private String achievementDate;
}
