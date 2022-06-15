package com.FIFAONLINE4.GG.user;

import com.FIFAONLINE4.GG.player.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    private final PlayerService playerService;

    private UserResponseDto userInfoDto;

    private UserDivisionResponseDto[] userDivisionDtoArr;

    private UserTradeResponseDto[] userTradeDtoArr;

    private String curAccessId;

    @GetMapping("/user")
    public String user(Model model) {

        if(userInfoDto != null) {
            model.addAttribute("nickName", userInfoDto.getNickname());
            model.addAttribute("level", userInfoDto.getLevel());

            if(userDivisionDtoArr != null) {
                model.addAttribute("maxDivision", userService.replaceDivision(userDivisionDtoArr[0].getDivision()));
                model.addAttribute("matchType", "순위 경기");
                model.addAttribute("regDate", userDivisionDtoArr[0].getAchievementDate());
            }

            model.addAttribute("curAccessId", curAccessId);

            if(userTradeDtoArr != null) {
                Map playerInfoMap = playerService.getMapFromPlayerJson();
                model.addAttribute("tradeList", playerService.replacePlayerName(playerInfoMap, userTradeDtoArr));
            }
        }
        return "user";
    }

    @GetMapping("api/v1/user/{nickname}")
    @ResponseBody
    public UserResponseDto getUserInfo(@PathVariable String nickname) {

        userInfoDto = userService.searchUserInfo(nickname);

        return userInfoDto;
    }

    @GetMapping("api/v1/divisioninfo/{accessid}")
    @ResponseBody
    public UserDivisionResponseDto[] getUserDivisionInfo(@PathVariable String accessid) {

        curAccessId = accessid;
        userDivisionDtoArr = userService.searchUserDivision(accessid);

        return userDivisionDtoArr;
    }

    @GetMapping("api/v1/trade/{accessid}/{tradeType}")
    @ResponseBody
    public UserTradeResponseDto[] getUserTradeInfo(@PathVariable("accessid") String accessid, @PathVariable String tradeType) {

        accessid = curAccessId;
        userTradeDtoArr = userService.searchUserTrade(accessid, tradeType);
        return userTradeDtoArr;
    }
}
