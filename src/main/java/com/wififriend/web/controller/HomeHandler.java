package com.wififriend.web.controller;

import com.wififriend.web.entity.Wifi;
import com.wififriend.web.service.WifiRetrieveService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class HomeHandler extends CommandHandlerImpl {
    private final WifiRetrieveService wifiRetrieveService = new WifiRetrieveService();

    public HomeHandler() {
        super("home");
    }

    // /{lat}/{lnt}
    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        System.out.println("HOME");
        if (request.getQueryString() != null) {
            String lat = request.getParameter("lat");
            String lnt = request.getParameter("lnt");
            List<Wifi> wifi = wifiRetrieveService.retrieve(lat, lnt);
            request.setAttribute("wifi", wifi);
            request.setAttribute("lnt", lnt);
            request.setAttribute("lat", lat);
            System.out.println("set");
        } else {
            request.setAttribute("wifi", Collections.emptyList());
            request.setAttribute("lnt", 0);
            request.setAttribute("lat", 0);
        }
        return "/index.jsp";
    }
}
