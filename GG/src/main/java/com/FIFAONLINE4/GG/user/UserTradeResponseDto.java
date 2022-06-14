package com.FIFAONLINE4.GG.user;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserTradeResponseDto {

    private String tradeDate;

    private String saleSn;

    private Integer spid;

    private Integer grade;

    private Long value;
}
