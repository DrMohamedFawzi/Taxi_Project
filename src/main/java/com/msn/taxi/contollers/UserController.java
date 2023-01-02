package com.msn.taxi.contollers;

import com.msn.taxi.UsersService;
import com.msn.taxi.entity.User;
import com.msn.taxi.security.TokenGen;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
public class UserController {


    public static final String REDIRECT_USER = "redirect:/user";
    public static final String REDIRECT_LOGIN = "redirect:/login";
    private final UsersService usersService;

    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @ModelAttribute("user")
    public User getUser(@CookieValue(name = TokenGen.LOG_TOKEN, defaultValue = "end") String token) {
        if (token.length() > 16) return usersService.getUserByToken(token);
        else return null;
    }

    @GetMapping("/login")
    public String login(@ModelAttribute User user) {
        if (user != null) {
            return REDIRECT_USER;
        }
        return "login/login";
    }

    @PostMapping("/login")
    public String loginPost(@RequestParam String phone, Model model, @RequestParam String password, HttpServletResponse response) {
        System.out.println(phone);
        System.out.println(password);
        Cookie cookie = usersService.login(phone, password);
        response.addCookie(cookie);

        if (cookie.getMaxAge() > 0) {
            return REDIRECT_USER;
        }
        model.addAttribute("info", "error");
        return "login/login";
    }


    @GetMapping("/user")
    public String user(@ModelAttribute User user, Model model) {
        if (user == null) {
            return REDIRECT_LOGIN;
        }
        model.addAttribute("name", user.getFname());
        return "user/user";
    }

    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie(TokenGen.LOG_TOKEN, "end");
        cookie.setMaxAge(-1);
        response.addCookie(cookie);
        return REDIRECT_LOGIN;
    }

    @GetMapping("/sign_up")
    public String signUp(@ModelAttribute User user, Model model) {
        if (user != null) {
            return REDIRECT_USER;
        }
        model.addAttribute("info", "");
        return "login/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUpPost(@ModelAttribute User user, @RequestParam String fname, @RequestParam String lname, @RequestParam String phone, @RequestParam String password, Model model) {
        if (user != null) {
            return REDIRECT_USER;
        }

        String result = usersService.createNewUser(fname, lname, phone, password);
        if (result.equals("success")) result = "تم إنشاء حسابك بنجاح يرجى تسجيل الدخول";
        model.addAttribute("info", result);
        return "login/sign_up";
    }

}
