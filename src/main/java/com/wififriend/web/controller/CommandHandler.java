package com.wififriend.web.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public interface CommandHandler {
    String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException;

    String getName();
}
