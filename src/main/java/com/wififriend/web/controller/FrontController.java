package com.wififriend.web.controller;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class FrontController extends HttpServlet {
    private final CommandHandlerMap handlerMap = new CommandHandlerMap(this);
    private String message;

    public void init() {
        System.out.println("FrontController");
        handlerMap.add(new SomeHandler());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        System.out.println(path);
        StringTokenizer st = new StringTokenizer(path, "/");
        String pathKey = st.nextToken();
        System.out.println("pathKey => " + pathKey);
        handlerMap.get(pathKey)
                .ifPresent(handler -> {
                    System.out.println("yes");
                    try {
                        String page = handler.handle(req, resp, st);
                        req.getRequestDispatcher(page).forward(req, resp);
                    } catch (ServletException | IOException e) {
                        throw new RuntimeException(e);
                    }
                });
    }

    public void destroy() {
    }


}
