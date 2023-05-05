package com.wififriend.web.controller;

import com.wififriend.web.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class BookmarkGroupDeleteHandler extends CommandHandlerImpl{
    private final BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();

    public BookmarkGroupDeleteHandler() {
        super("bookmark-group-delete");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        bookmarkGroupService.remove(request.getParameter("id"));
        return "";
    }

}
