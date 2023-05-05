<%@ page import="java.util.List" %>
<%@ page import="com.wififriend.web.entity.Wifi" %>
<%@ page import="com.wififriend.web.entity.BookmarkGroup" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>즐겨찾기 그룹 관리</title>
    <style>
        table {
            width: 100%;
            min-height: 50px;
            margin: 5px;
            border: 1px solid lightgray;
        }
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
<h1>즐겨찾기 그룹 관리</h1>
<br/>
<a href="/">홈</a> |
<a href="/history">위치 히스토리 목록</a> |
<a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
<a href="/bookmark-list">즐겨 찾기 보기</a> |
<a href="/bookmark-group">즐겨 찾기 그룹 관리</a>
<br/>
<a href="bookmark-group-add"><button>북마크 그룹 이름 추가</button></a>
<table>
    <tr class="table-header">
        <td>ID</td>
        <td>북마크 이름</td>
        <td>순서</td>
        <td>등록일자</td>
        <td>수정일자</td>
        <td>비고</td>
    </tr>
    <%
        List<BookmarkGroup> bookmarkGroups = (List<BookmarkGroup>) request.getAttribute("bookmarkGroups");
    %>
    <%
        for (BookmarkGroup b : bookmarkGroups) {
    %>
    <tr class="row-main">
        <td><%=b.getId()%></td>
        <td><%=b.getName()%></td>
        <td><%=b.getOrdinal()%></td>
        <td><%=b.getCreatedAt()%></td>
        <td><%=b.getUpdatedAt()%></td>
        <td style="text-align: center">
            <a href="/bookmark-group-edit?id=<%=b.getId()%>">수정</a>
            &nbsp;
            <a href="/bookmark-group-delete?id=<%=b.getId()%>" onclick="onRemoveButtonClicked(id)">삭제</a>
        </td>
    </tr>
    <% } %>
    <script>
        const onRemoveButtonClicked = (id) => {
            fetch("/bookmark-group-delete?id=" + id)
                .then(res => {
                    window.location.replace('/bookmark-group')
                })
        }
    </script>
</body>
</html>
