package com.FIFAONLINE4.GG.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    private UserResponseDto userInfoDto;

    private UserDivisionResponseDto[] userDivisionDtoArr;

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

        userDivisionDtoArr = userService.searchUserDivision(accessid);

        return userDivisionDtoArr;
    }
}
