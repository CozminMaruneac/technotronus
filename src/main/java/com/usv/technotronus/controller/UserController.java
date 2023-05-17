package com.usv.technotronus.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.usv.technotronus.utilities.CurrentUserLogged.getLoggedUserEmail;

@RestController


@RequestMapping(path = "")
public class UserController {

    @GetMapping
    public String welcome(){
        return "Welcome!";
    }

    @GetMapping("/user")
    public String user( ){
        return getLoggedUserEmail();
    }
}
