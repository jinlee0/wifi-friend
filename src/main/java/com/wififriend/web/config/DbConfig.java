package com.wififriend.web.config;

public class DbConfig {
    public static final String JDBC_DRIVER = "org.sqlite.JDBC";
    public static final String DB_URL_PREFIX = "jdbc:sqlite:";
    public static final String DB_NAME = "wifi-friend-db";
    public static final String DB_URL = DB_URL_PREFIX + DB_NAME;

}
