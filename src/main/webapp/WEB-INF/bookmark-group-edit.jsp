<%@ page import="com.wififriend.web.entity.History" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wififriend.web.entity.Wifi" %>
<%@ page import="com.wififriend.web.entity.BookmarkGroup" %><%--
  Created by IntelliJ IDEA.
  User: wls43
  Date: 2023-05-05
  Time: 오후 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>북마크 그룹 수정</title>
    <style>
        table {
            width: 100%;
            min-height: 50px;
            margin: 5px;
            border: 1px solid lightgray;
        }
        td {
            padding: 10px 5px 10px 5px;
        }
        tr:nth-child(odd) {
            background-color: white;
        }
        tr:nth-child(even) {
            background-color: #f3f2f3;
        }
        tr:hover {
            background-color: #deddde;
        }
        td:first-child {
            text-align: center;
            background-color: #03A770;
            color: white;
        }
        tr:nth-child(3) > td {
            background-color: white;
        }
    </style>
</head>
<body>
<h1>북마크 그룹 수정</h1>
<br/>
<a href="/">홈</a> |
<a href="/history">위치 히스토리 목록</a> |
<a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
<a href="/bookmark-list">즐겨 찾기 보기</a> |
<a href="/bookmark-group">즐겨 찾기 그룹 관리</a>
<br/>
<button>즐겨찾기 그룹 수정하기</button>
<br/>
<table>
    <%
        BookmarkGroup origin = (BookmarkGroup) request.getAttribute("bookmarkGroup");
    %>
    <tr><td>북마크 이름</td><td><input id="input-name" value="<%=origin.getName()%>"/></td></tr>
    <tr><td>순서</td><td><input id="input-ordinal" type="number" value="<%=origin.getOrdinal()%>"/></td></tr>
    <tr><td colspan=100><a href="/bookmark-group">돌아가기</a> | <button onclick="onEditButtonClicked('<%=origin.getId()%>')">수정</button></td></tr>
</table>
<br/>
<script>
    const onEditButtonClicked = (id) => {
        let name = document.getElementById('input-name').value;
        let ordinal = document.getElementById('input-ordinal').value;
        console.log('onEditButtonClicked')
        fetch('/bookmark-group-edit?name=' + name + '&ordinal=' + ordinal + "&id=" + id)
            .then(res => {
                window.location.replace('/bookmark-group')
            })
    }
</script>
</body>
</html>
