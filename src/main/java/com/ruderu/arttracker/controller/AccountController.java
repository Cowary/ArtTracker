package com.ruderu.arttracker.controller;

import com.ruderu.arttracker.dbCase.UserService;
import com.ruderu.arttracker.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("user/account")
    public String get(
            Model model
    ) {
        User user = userService.getCurrentUser();
        model.addAttribute("username", user.getUsername());

        return "account";
    }

    @PostMapping("user/changePassword")
    public String post(
            String username,
            String password,
            Model model
    ) {
        userService.savePassword(username, password);

        return "redirect:../";
    }

    @PostMapping("user/changeName")
    public String post(
            String username
    ) {

        return "redirect:../view/media";
    }
}
