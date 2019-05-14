package com.karacsonyiz.gameshop.controller;

import com.karacsonyiz.gameshop.model.User;
import com.karacsonyiz.gameshop.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping("/api/user")
    public User getUser(Authentication authentication){
        if(authentication == null) {
            return null;
        } else {
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String name = userDetails.getUsername();
            String role = new ArrayList<GrantedAuthority>(userDetails.getAuthorities()).get(0).getAuthority();
            User foundUser = userService.findUserByUserName(name).get();
            return new User(foundUser.getId(), foundUser.getName(), foundUser.getPassword(), foundUser.getEnabled(), foundUser.getRole());
        }
    }

    private User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByUserName(authentication.getName()).get();
        return user;
    }
}
