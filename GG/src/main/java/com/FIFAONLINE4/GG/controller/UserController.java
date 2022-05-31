package com.FIFAONLINE4.GG.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/user")
    public String user(Model model) {

        model.addAttribute("name", "이름테스트");
        return "user";
    }
}
