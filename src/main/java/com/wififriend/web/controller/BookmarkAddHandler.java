package com.wififriend.web.controller;

import com.wififriend.web.service.BookmarkService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class BookmarkAddHandler extends CommandHandlerImpl {
    private final BookmarkService bookmarkService = new BookmarkService();
    public BookmarkAddHandler() {
        super("bookmark-add");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        String wifiId = request.getParameter("wifiId");
        String bookmarkGroupId = request.getParameter("bookmarkGroupId");
        bookmarkService.add(wifiId, bookmarkGroupId);
        return "";
    }
}
