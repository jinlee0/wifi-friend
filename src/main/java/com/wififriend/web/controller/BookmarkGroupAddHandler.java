package com.wififriend.web.controller;

import com.wififriend.web.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class BookmarkGroupAddHandler extends CommandHandlerImpl{
    private final BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();

    public BookmarkGroupAddHandler() {
        super("bookmark-group-add");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        if(request.getQueryString() != null) {
            return wifhQueryString(request, response);
        }
        return "/WEB-INF/bookmark-group-add.jsp";
    }

    private String wifhQueryString(HttpServletRequest request, HttpServletResponse response) {
        String name = request.getParameter("name");
        String ordinal = request.getParameter("ordinal");
        bookmarkGroupService.add(name, ordinal);
        request.setAttribute("bookmarkGroups", bookmarkGroupService.getAll());
        return "/WEB-INF/bookmark-group.jsp";
    }
}
