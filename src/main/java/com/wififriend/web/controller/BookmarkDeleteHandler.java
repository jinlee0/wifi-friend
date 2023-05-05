package com.wififriend.web.controller;

import com.wififriend.web.service.BookmarkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class BookmarkDeleteHandler extends CommandHandlerImpl {
    private final BookmarkService bookmarkService = new BookmarkService();
    public BookmarkDeleteHandler() {
        super("bookmark-delete");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        bookmarkService.remove(request.getParameter("id"));
        return "";
    }
}
