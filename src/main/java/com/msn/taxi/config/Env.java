package com.msn.taxi.config;

public class Env {
    public static final String DB_PASSWORD;

    static {
        DB_PASSWORD = System.getenv().getOrDefault("dbpassword", "postgres");
    }
}
