package com.wififriend.web.controller;

import com.wififriend.web.config.DbConfig;
import com.wififriend.web.entity.History;
import com.wififriend.web.repository.WifiRepository;
import com.wififriend.web.service.HistoryService;
import lombok.Getter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

@Getter
public class HistoryHandler extends CommandHandlerImpl {
    private final HistoryService historyService = new HistoryService();

    protected HistoryHandler() {
        super("history");
    }
    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        System.out.println("HistoryHandler");
        if (st.hasMoreTokens()) {
            switch (st.nextToken()) {
                case "add":
                    add(request, response);
                case "remove":
                    remove(request, response);
                default:
                    System.out.println("Unexpected Token in HistoryHandler.handle");
            }
        }
        updateAttribute(request);
        return "/WEB-INF/history.jsp";
    }

    private void remove(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("REMOVE");
        String historyId = request.getParameter("historyId");
        historyService.remove(historyId);
    }

    private void add(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(this.getClass().getSimpleName() + ".add");
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");
        historyService.add(lat, lnt);
    }

    private void updateAttribute(HttpServletRequest request) {
        List<History> histories = historyService.getAll();
        request.setAttribute("histories", histories);
    }
}
