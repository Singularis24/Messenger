package com.singularis.messenger.controller;

import com.singularis.messenger.domain.Dialog;
import com.singularis.messenger.domain.Message;
import com.singularis.messenger.domain.User;
import com.singularis.messenger.service.DialogService;
import com.singularis.messenger.service.MessageService;
import com.singularis.messenger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;

@Controller
public class DialogController {

    @Autowired
    private UserService userService;

    @Autowired
    private DialogService dialogService;

    @Autowired
    private MessageService messageService;

    @MessageMapping("/dialog/{id}/sendMessage")
    @SendTo("/topic/dialog/{id}")
    public Message sendMessage(@Payload Message message) {
        Dialog dialog = dialogService.findById(message.getId_dialog());
        dialogService.updateDialog(dialog, message);
       // messageService.addMessage(message); //???
        return message;
    }


    @RequestMapping(value = "/dialog/{id}", method = RequestMethod.GET)
    public ModelAndView dialogGet(@PathVariable("id") int id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user1 = userService.findByLogin(auth.getName());
        User user2 = userService.findById(id);
        Dialog dialog = new Dialog();

        boolean flag = true;
        int i = 0;
        if (user1.getId() == id) {
            while (i < user1.getDialoges().size() && flag==true) {
                if (user1.getDialoges().get(i).getMembers().equals(user1.getId() + "/" + user1.getId())) {
                    flag = false;
                    dialog = user1.getDialoges().get(i);
                }
                i++;
            }
        } else {
            i = 0;
            int j = 0;
            while (i < user1.getDialoges().size() && flag==true) {
                while (j < user2.getDialoges().size() && flag==true) {
                    if (user1.getDialoges().get(i).getId() == user2.getDialoges().get(j).getId()) {
                        flag = false;
                        dialog = user2.getDialoges().get(j);
                    }
                    j++;
                }
                i++;
            }
            if(flag){
                i = 0;
                j = 0;
                while (i < user2.getDialoges().size() && flag==true) {
                    while (j < user1.getDialoges().size() && flag==true) {
                        if (user2.getDialoges().get(i).getId() == user1.getDialoges().get(j).getId()) {
                            flag = false;
                            dialog = user1.getDialoges().get(j);
                        }
                        j++;
                    }
                    i++;
                }
            }
        }

        if (flag) {
            dialog.setCreateDate(LocalDate.now());
            dialog.getUsers().add(user1);
            dialog.getUsers().add(user2);
            dialog.setMembers(user1.getId() + "/" + user2.getId());
            user1.getDialoges().add(dialog);
            user2.getDialoges().add(dialog);
            dialogService.addDialog(dialog);
            userService.updateUser(user1);
            userService.updateUser(user2);
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("dialog", dialog);
        modelAndView.addObject("user", user1);
        modelAndView.addObject("messeges", dialog.getMessages());
        modelAndView.addObject("newLine", "\n");
        modelAndView.addObject("user2", user2);
        modelAndView.setViewName("dialog");
        return modelAndView;
    }

    @RequestMapping(value = "/dialoges", method = RequestMethod.GET)
    public ModelAndView dialoges(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByLogin(auth.getName());
        ModelAndView modelAndView = new ModelAndView("dialoges");
        modelAndView.addObject("user", user);
        modelAndView.addObject("dialoges", user.getDialoges());
        return modelAndView;
    }

}
