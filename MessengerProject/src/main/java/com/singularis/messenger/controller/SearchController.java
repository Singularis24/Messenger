package com.singularis.messenger.controller;

import com.singularis.messenger.domain.User;
import com.singularis.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public ModelAndView searchPost(@RequestParam(value = "stringSearch") String str){
        String[] keyWords = str.split(" ");
        List<User> usersByFirstName = new ArrayList<>();
        List<User> usersByLastName = new ArrayList<>();
        List<User> usersByLogin = new ArrayList<>();
        List<User> usersSearch = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        for (String key: keyWords){
            List<User> users = userService.findByFirstName(key);
            if(users!=null)
            usersByFirstName.addAll(users);
        }
        for (String key: keyWords){
            List<User> users = userService.findByLastName(key);
            if(users!=null)
            usersByLastName.addAll(users);
        }
        for (String key: keyWords){
            User user = userService.findByLogin(key);
            if(user!=null)
            usersByLogin.add(user);
        }
        if (!usersByLogin.isEmpty()){
            usersSearch.addAll(usersByLogin);
            modelAndView.addObject("users", usersSearch);
            return modelAndView;
        }
        if(!usersByFirstName.isEmpty() && !usersByLastName.isEmpty()){
            for (User user: usersByFirstName){
                if(usersByLastName.contains(user)){
                    usersSearch.add(user);
                }
            }
            modelAndView.addObject("users", usersSearch);
            return modelAndView;
        }
        if(!usersByFirstName.isEmpty()){
            usersSearch.addAll(usersByFirstName);
            modelAndView.addObject("users", usersSearch);
            return modelAndView;
        }
        if(!usersByLastName.isEmpty()){
           usersSearch.addAll(usersByLastName);
            modelAndView.addObject("users", usersSearch);
            return modelAndView;
        }
        modelAndView.addObject("notFind", "По данному запросу ничего не найдено");
        return modelAndView;
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ModelAndView searchGet(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("search");
        return modelAndView;
    }
}
