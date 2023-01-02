package com.msn.taxi;

import com.msn.taxi.entity.User;
import com.msn.taxi.repo.ClientRepo;
import com.msn.taxi.repo.CookiesRepo;
import com.msn.taxi.security.TokenGen;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;

@Service
public class UsersService {

    private static final int COOKIE_DAYS = 5;
    private final ClientRepo clientRepo;
    private final CookiesRepo cookiesRepo;

    public UsersService(ClientRepo clientRepo, CookiesRepo cookiesRepo) {
        this.clientRepo = clientRepo;
        this.cookiesRepo = cookiesRepo;
    }

    public Cookie login(String phone, String password) {
        User user = clientRepo.findById(phone).orElse(null);
        Cookie cookie = new Cookie(TokenGen.LOG_TOKEN, "end");
        cookie.setMaxAge(-1);
        cookie.setDomain("/");
        cookie.setSecure(true);
        if (user != null) {
            if (user.getPassword().equals(password)) {
                cookie.setMaxAge(COOKIE_DAYS * 86400);
                cookie.setValue(
                        TokenGen.genNewToken(
                                phone, password, TokenGen.getCurrentDateAnd(COOKIE_DAYS)));
                return cookie;
            }
        }
        return cookie;
    }

    public User getUserByToken(String token) {
        String userId = TokenGen.getIDFromToken(token);
        return clientRepo.findById(userId).orElse(null);

    }

    public String createNewUser(String fname, String lname, String phone, String password) {
        if (fname == null || fname.length() < 4) {
            return "الإسم أقل من 4 أحرف";
        }
        if (lname == null || lname.length() < 4) {
            return "الإسم أقل من 4 أحرف";

        }
        if (phone == null || !phone.matches("^05[6|9]\\d{7}$")) {
            return "الرقم غير صحيح";

        }
        if (password == null || password.length() < 8) {
            return "كلمةالسر أقل من 8 خانات";
        }
        User user = clientRepo.findById(phone).orElse(null);
        if (user == null)
            user = new User(fname, lname, phone, password);
        else {
            return "رقم الهاتف مستخدم من قبل";
        }
        clientRepo.save(user);
        return "success";
    }

}
