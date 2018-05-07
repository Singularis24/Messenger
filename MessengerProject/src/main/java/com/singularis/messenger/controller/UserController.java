package com.singularis.messenger.controller;

import com.singularis.messenger.domain.User;
import com.singularis.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String user(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("path", "/user/add");
        return "user";
    }

    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        this.userService.addUser(user);
        return "redirect:/user";
    }
}
