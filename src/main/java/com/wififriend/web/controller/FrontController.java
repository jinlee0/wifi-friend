package com.wififriend.web.controller;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class FrontController extends HttpServlet {
    private final CommandHandlerMap handlerMap = new CommandHandlerMap(this);

    public void init() {
        System.out.println("FrontController");
        handlerMap.add(new LoadWifiHandler());
        handlerMap.add(new HomeHandler());
        handlerMap.add(new HistoryHandler());
        handlerMap.add(new DetailHandler());
        handlerMap.add(new BookmarkGroupHandler());
        handlerMap.add(new BookmarkGroupAddHandler());
        handlerMap.add(new BookmarkGroupEditHandler());
        handlerMap.add(new BookmarkGroupDeleteHandler());
        handlerMap.add(new BookmarkListHandler());
        handlerMap.add(new BookmarkAddHandler());
        handlerMap.add(new BookmarkDeleteHandler());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        System.out.println("path: " + path);
        StringTokenizer st = new StringTokenizer(path, "/");
        if (st.hasMoreTokens()) {
            String pathKey = st.nextToken();
            System.out.println("pathKey => " + pathKey);
            handlerMap.get(pathKey)
                    .ifPresent(handler -> {
                        try {
                            String page = handler.handle(req, resp, st);
                            req.getRequestDispatcher(page).forward(req, resp);
                        } catch (ServletException | IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        }
    }

    public void destroy() {
    }


}
