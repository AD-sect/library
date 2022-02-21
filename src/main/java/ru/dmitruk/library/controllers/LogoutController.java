package ru.dmitruk.library.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LogoutController {
    @GetMapping("/success_logout")
    public String getLogoutPage(){
        return "logout";
    }
}
