package com.msn.taxi.contollers;

import com.msn.taxi.UsersService;
import com.msn.taxi.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/user")
public class UserApi {
    private final UsersService usersService;

    public UserApi(UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public User getUser(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Cookie cookie = usersService.login(params.get("phone"), params.get("password"));
        if (cookie.getMaxAge() < 0) {
            response.setStatus(HttpServletResponse.SC_OK);
            return usersService.getUserByToken(cookie.getValue());
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        return null;

    }

    @GetMapping("/token")
    public String getToken(@RequestParam Map<String, String> params, HttpServletResponse response) {
        Cookie cookie = usersService.login(params.get("phone"), params.get("password"));
        if (cookie.getMaxAge() < 0) {
            response.setStatus(HttpServletResponse.SC_OK);
            return cookie.getValue();
        }
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        return null;

    }

    @PostMapping()
    public String createUser(@RequestParam Map<String, String> params, HttpServletResponse response) {
        String message = usersService.createNewUser(
                params.get("fname"),
                params.get("lname"),
                params.get("phone"),
                params.get("password")
        );
        response.setStatus(HttpServletResponse.SC_OK);
        return message;
    }

    @PostMapping("/request_car")
    public String request(@RequestParam Map<String, String> params, HttpServletResponse response) {

        response.setStatus(HttpServletResponse.SC_OK);
        return "UNDER CONSTRUCTION";
    }

}
