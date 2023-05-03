package com.wififriend.web.controller;

import lombok.Getter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

@Getter
public class SomeHandler implements CommandHandler {
    private final String name = "some";
    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        System.out.println("SomeHandler");
        request.setAttribute("some", "somesome");
        return "/WEB-INF/some.jsp";
    }
}
