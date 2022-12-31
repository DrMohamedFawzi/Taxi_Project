package com.msn.taxi.contollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {


    @GetMapping("/login")
    public String login() {
        return "login/login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String phone, @RequestParam String password) {
        System.out.println(phone);
        System.out.println(password);
        return "login/login";
    }


}
