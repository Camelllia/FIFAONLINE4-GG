package com.FIFAONLINE4.GG.user;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class UserTradeResponseDto {

    private String tradeDate;

    private String saleSn;

    private Long spid;

    private Integer grade;

    private Long value;
}
