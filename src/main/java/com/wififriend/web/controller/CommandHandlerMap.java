package com.wififriend.web.controller;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommandHandlerMap {
    private final Map<String, CommandHandler> map = new HashMap<>();
    private final HttpServlet ownerServlet;

    public CommandHandlerMap(HttpServlet ownerServlet) {
        this.ownerServlet = ownerServlet;
    }

    public void add(CommandHandler handler) {
        map.put(handler.getName(), handler);
    }

    public Optional<CommandHandler> get(String pathKey) {
        return Optional.ofNullable(map.get(pathKey));
    }
}
