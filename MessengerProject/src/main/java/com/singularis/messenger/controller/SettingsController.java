package com.singularis.messenger.controller;

import com.singularis.messenger.domain.User;
import com.singularis.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.util.UUID;

@Controller
public class SettingsController {

    @Autowired
    UserService userService;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @RequestMapping(value = "/user/settings/getOptionalInformation", method = RequestMethod.POST)
    public ModelAndView settingPostOptional(@RequestParam("city") String city, @RequestParam("phone") String phone,
                                            @RequestParam("dateBirth") String dateBirth) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        if (!city.isEmpty())
            user.setCity((city));
        if (!dateBirth.isEmpty())
            user.setDateBirth(dateBirth);
        if (!phone.isEmpty())
            user.setPhone(phone);
        userService.updateUser(user);
        modelAndView.setViewName("redirect:/user/settings");
        return modelAndView;
    }

    @RequestMapping(value = "/user/settings/getGeneralInformation", method = RequestMethod.POST)
    public ModelAndView settingPostGeneral(@RequestParam("firstName") String firstName,
                                           @RequestParam("lastName") String lastName,
                                           @RequestParam("login") String login,
                                           @RequestParam("password") String password) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        if (userService.findByLogin(login) != null) {
            modelAndView.addObject("error", "Такой логин уже существует");
            modelAndView.setViewName("user/settings");
        } else {
            modelAndView.setViewName("redirect:/user/settings");
            if (!firstName.isEmpty())
                user.setFirstName(firstName);
            if (!lastName.isEmpty())
                user.setLastName(lastName);
            if (!login.isEmpty()) {
                user.setLogin(login);
                modelAndView.setViewName("redirect:/");
            }
            if (!password.isEmpty()) {
                user.setPassword(bCryptPasswordEncoder.encode(password));
                modelAndView.setViewName("redirect:/");
            }
            userService.updateUser(user);
        }
        return modelAndView;
    }

    @RequestMapping(value = "/user/settings/uploadFile", method = RequestMethod.POST)
    public ModelAndView uploadFileHundler(@RequestParam("file") MultipartFile file) {
        String name = null;
        if (!file.isEmpty())
            name = file.getOriginalFilename();
        String error;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                UUID id = UUID.randomUUID();
                String link = "C://Users//Singularis//IdeaProjects//Messenger//src//main//resources//static//images//" + id + name;
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(link)));
                stream.write(bytes);
                stream.close();
                link = "/images/" + id + name;
                user.setAvatarLink(link);
                System.out.println(link);
                userService.updateUser(user);
            } catch (Exception e) {
                error = "Вам не удалось загрузить " + name + " => " + e.getMessage();
                modelAndView.addObject("error", error);
            }
        } else {
            error = "Вам не удалось загрузить " + name + " потому что файл пустой.";
            modelAndView.addObject("error", error);
        }
        modelAndView.setViewName("user/settings");
        return modelAndView;
    }

    @RequestMapping(value = "/user/settings", method = RequestMethod.GET)
    public ModelAndView settingsGet() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("userSettings", user);
        modelAndView.setViewName("user/settings");
        return modelAndView;
    }
}
