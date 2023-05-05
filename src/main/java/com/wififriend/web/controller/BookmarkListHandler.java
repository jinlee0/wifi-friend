package com.wififriend.web.controller;

import com.wififriend.web.service.BookmarkService;
import lombok.Builder;
import lombok.Getter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class BookmarkListHandler extends CommandHandlerImpl {
    private final BookmarkService bookmarkService = new BookmarkService();
    public BookmarkListHandler() {
        super("bookmark-list");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        List<BookmarkService.BookmarkListDto> dtos = bookmarkService.getAll();
        request.setAttribute("dtos", dtos);
        return "/WEB-INF/bookmark-list.jsp";
    }
}
