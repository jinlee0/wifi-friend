package com.wififriend.web.controller;

import com.wififriend.web.config.DbConfig;
import com.wififriend.web.repository.WifiRepository;
import lombok.Getter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.StringTokenizer;

@Getter
public class LoadWifiHandler extends CommandHandlerImpl {
    protected LoadWifiHandler() {
        super("load-wifi");
    }

    @Override
    public String handle(HttpServletRequest request, HttpServletResponse response, StringTokenizer st) throws ServletException, IOException {
        System.out.println("LoadWifiHandler");
        if (st.hasMoreTokens()) {
            DbConfig.updateWifiInfo();
            int count = WifiRepository.getInstance().findAll().size(); // caching and count
            request.setAttribute("count", count);
        }
        return "/WEB-INF/load-wifi.jsp";
    }
}
