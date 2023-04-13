package com.karacsonyiz.gameshop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class LoginController {

    @GetMapping("/")
    public RedirectView redirectWithUsingRedirectView() {
        return new RedirectView("/login.html");
    }
}
