package com.msn.taxi.security;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TokenGen {


    public static final String SPLITTER = "::";
    public static final String SECRET = "msn_it";
    public static final String LOG_TOKEN = "log_token";

    public static String genNewToken(String userId, String userPass, Timestamp date) {
        String userIdEncSimple = Enc.encrypt(userId, SECRET);

        String userIdEnc = Enc.encrypt(userId, userPass);
        String dateEnc = Enc.encrypt(date.toString(), userPass);
        return userIdEncSimple + SPLITTER + userIdEnc + SPLITTER + dateEnc;
    }

    public static String getIDFromToken(String token) {
        String userIdEncSimple = token.split(SPLITTER)[0];
        return Enc.decrypt(userIdEncSimple, SECRET);
    }

    public static Date decToken(String userId, String userPass, String token) {
        if (!token.contains(SPLITTER)) return null;

        String userIdDecSimple = getIDFromToken(token);
        String userIdEnc = token.split(SPLITTER)[1];
        String dateEnc = token.split(SPLITTER)[2];

        String userIdDec = Enc.decrypt(userIdEnc, userPass);
        String dateDec = Enc.decrypt(dateEnc, userPass);

        if (userIdDecSimple.equals(userIdDec) && userIdDec.equals(userId)) {
            if (dateDec != null)
                return Date.valueOf(dateDec);
        }
        return Date.valueOf("0");
    }

    public static Timestamp getCurrentDateAnd(int addDays) {
        return Timestamp.valueOf(LocalDateTime.now(ZoneId.of("Asia/Gaza")).plusDays(addDays));
    }
}
