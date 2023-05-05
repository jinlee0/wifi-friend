package com.wififriend.web.controller;

import com.wififriend.web.entity.BookmarkGroup;
import com.wififriend.web.entity.Wifi;
import com.wififriend.web.service.BookmarkGroupService;
import com.wififriend.web.service.WifiRetrieveService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

public class DetailHandler extends CommandHandlerImpl {
    private final WifiRetrieveService wifiRetrieveService = new WifiRetrieveService();
    private final BookmarkGroupService bookmarkGroupService = new BookmarkGroupService();
    public DetailHandler() {
        super("detail");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        String id = request.getParameter("id");
        Wifi wifi = wifiRetrieveService.getById(id);
        List<BookmarkGroup> bookmarkGroups = bookmarkGroupService.getAll();
        request.setAttribute("bookmarkGroups", bookmarkGroups);
        request.setAttribute("wifi", wifi);
        request.setAttribute("lat", request.getParameter("lat"));
        request.setAttribute("lnt", request.getParameter("lnt"));
        return "/WEB-INF/detail.jsp";
    }

}
