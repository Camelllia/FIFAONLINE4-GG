package com.FIFAONLINE4.GG.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


@RequiredArgsConstructor
@Controller
public class UserController {

    private final UserService userService;

    private UserResponseDto userInfoDto;

    @GetMapping("/user")
    public String user(Model model) {

        if(userInfoDto != null) {
            model.addAttribute("nickName", userInfoDto.getNickname());
            model.addAttribute("level", userInfoDto.getLevel());
        }

        return "user";
    }

    @GetMapping("api/v1/user/{nickname}")
    @ResponseBody
    public UserResponseDto getUserInfo(@PathVariable String nickname, Model model) {

        userInfoDto = userService.searchUserInfo(nickname);

        return userInfoDto;
    }
}
