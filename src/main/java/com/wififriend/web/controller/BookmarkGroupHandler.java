package com.wififriend.web.controller;

import com.wififriend.web.entity.BookmarkGroup;
import com.wififriend.web.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class BookmarkGroupHandler extends CommandHandlerImpl {
    private BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();

    public BookmarkGroupHandler() {
        super("bookmark-group");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        List<BookmarkGroup> bookmarkGroups = bookmarkGroupService.getAll();
        request.setAttribute("bookmarkGroups", bookmarkGroups);
        return "/WEB-INF/bookmark-group.jsp";
    }

}
