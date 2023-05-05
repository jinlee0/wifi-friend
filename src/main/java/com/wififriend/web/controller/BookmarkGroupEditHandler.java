package com.wififriend.web.controller;

import com.wififriend.web.entity.BookmarkGroup;
import com.wififriend.web.service.BookmarkGroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

public class BookmarkGroupEditHandler extends CommandHandlerImpl{
    private final BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();

    public BookmarkGroupEditHandler() {
        super("bookmark-group-edit");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        String id = request.getParameter("id");
        BookmarkGroup bg = bookmarkGroupService.getById(id);
        request.setAttribute("bookmarkGroup", bg);

        String newName = request.getParameter("name");
        String newOrdinal = request.getParameter("ordinal");
        if(newName == null)
            return "/WEB-INF/bookmark-group-edit.jsp";

        bookmarkGroupService.modify(id, newName, newOrdinal);
        return "/WEB-INF/bookmark-group.jsp";
    }

}
