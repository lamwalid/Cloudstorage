package com.example.cloudstorage.cloudstorage.controller;

import com.example.cloudstorage.cloudstorage.model.User;
import com.example.cloudstorage.cloudstorage.services.UserService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String signupView(){
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute User user, Model model){

        String signupError = null;

        if(!userService.isUserNameAvailable(user.getUserName())){
            signupError = "The username is already used. Please choose a different one";
        }

        if(signupError == null){
            int rowAdded = userService.crateUser(user);
            if(rowAdded < 0)
                signupError = "There was an error signing you up. Please try again";
        }

        if(signupError == null){
            model.addAttribute("signupSuccess", true);
            return "redirect:/login";
        }else{
            model.addAttribute("signupError", signupError);
        }

        return "signup";
    }
}
