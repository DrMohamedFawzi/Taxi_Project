package com.msn.taxi.config;

import java.util.Map;

public class Env {
    public static Map<String, String> data;

    static {
        data = System.getenv();
    }
}
