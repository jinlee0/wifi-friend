<%@ page import="com.wififriend.web.entity.Wifi" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wififriend.web.entity.History" %><%--
  Created by IntelliJ IDEA.
  User: wls43
  Date: 2023-05-05
  Time: 오후 5:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>위치 히스토리 목록</title>
    <style>
        .table-header {
            text-align: center;
            background-color: #03A770;
            color: white;
        }
        tr.row-main > td {
            padding: 0 5px 0 5px;
        }
        tr.row-main:nth-child(even) {
            background-color: white;
        }
        tr.row-main:nth-child(odd) {
            background-color: #f3f2f3;
        }
        tr.row-main:hover {
            background-color: #deddde;
        }
    </style>
</head>
<body>
<h1>위치 히스토리 목록</h1>
<br/>
<a href="/">홈</a> |
<a href="/history">위치 히스토리 목록</a> |
<a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
<a href="/bookmark-list">즐겨 찾기 보기</a> |
<a href="/bookmark-group">즐겨 찾기 그룹 관리</a>
<br/>
<% List<History> histories = (List<History>) request.getAttribute("histories"); %>
<table style="width:100%; min-height: 50px; margin:5px; border: 1px solid lightgray">
    <tr class="table-header">
        <td>ID</td>
        <td>X좌표</td>
        <td>Y좌표</td>
        <td>조회일자</td>
        <td>비고</td>
    </tr>
    <% if(histories == null) { %>
    <tr><td colspan=100 style="padding: 10px; text-align: center">위치 정보를 입력한 후에 조회해 주세요.</td></tr>
    <% } else { %>

    <%
        for (History h : histories) {
    %>
    <tr class="row-main" id="<%=h.getId()%>">
        <td><%=h.getId()%></td>
        <td><%=h.getLatitude()%></td>
        <td><%=h.getLongitude()%></td>
        <td><%=h.getCreatedAt()%></td>
        <td style="text-align: center"><button onclick="onRemoveButtonClicked('<%=h.getId()%>')">삭제</button></td>
    </tr>
    <% } %>
    <% } %>
</table>
<script>
    const onRemoveButtonClicked = id => {
        console.log('id: ' + id)
        fetch('/history/remove?historyId=' + id)
            .then(res => {
                console.log(res)
                document.getElementById(id).remove();
            })
            .catch(err => console.log('history add failed'))
    }
</script>
</body>
</html>
