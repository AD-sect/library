package ru.dmitruk.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @GetMapping("/login")
    public String getLoginPage(){
        return "login";
    }

//    @PostMapping("/login")
//    public String login(){
//        return "redirect:/succes";
//    }

    @GetMapping("/success")
    public String getSuccessPage(){
        return "succes";
    }
}