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
    <title>와이파이 세부 정보</title>
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
        tr:nth-child(even) {
            background-color: white;
        }
        tr:nth-child(odd) {
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
    </style>
</head>
<body>
<h1>와이파이 세부 정보</h1>
<br/>
<a href="/">홈</a> |
<a href="/history">위치 히스토리 목록</a> |
<a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
<a href="/bookmark-list">즐겨 찾기 보기</a> |
<a href="/bookmark-group">즐겨 찾기 그룹 관리</a>
<br/>
<select id="select-bookmark">
    <option value="label">북마크 그룹 이름 선택</option>
    <%
        List<BookmarkGroup> bgs = (List<BookmarkGroup>) request.getAttribute("bookmarkGroups");
        for (BookmarkGroup bg : bgs) {
    %>
    <option value="<%=bg.getId()%>"><%=bg.getName()%></option>
    <% } %>
</select>
<button onclick="onBookmarkAddButtonClicked()">즐겨찾기 추가하기</button>
<br/>
<table>
    <%
        Wifi w = (Wifi) request.getAttribute("wifi");
        double lnt = Double.parseDouble((String) request.getAttribute(("lnt")));
        double lat = Double.parseDouble((String) request.getAttribute(("lat")));
    %>
    <tr><td>거리(Km)</td><td><%=w.distanceFrom(lnt, lat)%></td></tr>
    <tr><td>관리번호</td><td><%=w.getManagementNumber()%></td></tr>
    <tr><td>자치구</td><td><%=w.getDistrict()%></td></tr>
    <tr><td>와이파이명</td><td><%=w.getName()%></td></tr>
    <tr><td>도로명주소</td><td><%=w.getStreet()%></td></tr>
    <tr><td>상세주소</td><td><%=w.getDetailAddress()%></td></tr>
    <tr><td>설치위치(층)</td><td><%=w.getDeployedFloor()%></td></tr>
    <tr><td>설치유형</td><td><%=w.getDeployedType()%></td></tr>
    <tr><td>설치기관</td><td><%=w.getDeploymentOrg()%></td></tr>
    <tr><td>서비스구분</td><td><%=w.getServiceType()%></td></tr>
    <tr><td>망종류</td><td><%=w.getNetType()%></td></tr>
    <tr><td>설치년도</td><td><%=w.getDeployedYear()%></td></tr>
    <tr><td>실내외구분</td><td><%=w.getInOrOut()%></td></tr>
    <tr><td>WIFI접속환경</td><td><%=w.getAccessEnvironment()%></td></tr>
    <tr><td>X좌표</td><td><%=w.getLatitude()%></td></tr>
    <tr><td>Y좌표</td><td><%=w.getLongitude()%></td></tr>
    <tr><td>작업일자</td><td><%=w.getWorkedDate()%></td></tr>
</table>
<br/>
<script>
    const onBookmarkAddButtonClicked = () => {
        let selectedId = document.getElementById("select-bookmark").value;
        if (selectedId === 'label') {
            alert("즐겨찾기 그룸을 선택해주세요. 그룹이 없다면 '즐겨찾기 그룹 관리'에서 추가해주세요.")
            return
        }
        fetch('/bookmark-add?wifiId=<%=w.getId()%>&bookmarkGroupId=' + selectedId)
            .then(res => {
                location.replace('/bookmark-list')
            });
    }
</script>
</body>
</html>
