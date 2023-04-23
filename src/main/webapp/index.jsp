<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<%
    String a = "asdf";
    for (int i = 0; i < 2; i++) {
        System.out.println(i);
    }
%>
<p><%= a%><p/>
</body>
</html>
