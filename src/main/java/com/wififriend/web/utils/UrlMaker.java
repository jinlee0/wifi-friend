package com.wififriend.web.utils;

import java.util.List;
import java.util.StringJoiner;

public class UrlMaker {
    public static String makeUrl(String baseUrl, List<String> pathVariables, List<String> queryStrings) {
        StringJoiner pathVariableJoiner = new StringJoiner("/");
        pathVariableJoiner.add(baseUrl);
        for (String pathVariable : pathVariables) {
            pathVariableJoiner.add(pathVariable);
        }

        if(queryStrings.isEmpty())
            return pathVariableJoiner.toString();

        StringJoiner queryStringJoiner = new StringJoiner("&");
        for (String queryString : queryStrings) {
            queryStringJoiner.add(queryString);
        }
        return pathVariableJoiner + "?" + queryStringJoiner;
    }
}
