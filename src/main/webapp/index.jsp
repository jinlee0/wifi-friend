<%@ page import="java.util.List" %>
<%@ page import="com.wififriend.web.entity.Wifi" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>WIFI-Friend</title>
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
<h1>와이파이 정보 구하기</h1>
<br/>
<a href="/">홈</a> |
<a href="/history">위치 히스토리 목록</a> |
<a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
<a href="/bookmark-list">즐겨 찾기 보기</a> |
<a href="/bookmark-group">즐겨 찾기 그룹 관리</a>
<br/>
<label>LAT: </label><input id="user-lat" onchange="">
<label>LNT: </label><input id="user-lnt" onchange="">
<button id="btn-location-refresh">내 위치 가져오기</button>
<button id="btn-wifi">근처 WIFI 정보 보기</button>
<% List<Wifi> wifi = (List<Wifi>) request.getAttribute("wifi"); %>
<table>
    <tr class="table-header">
        <td>거리(Km)</td>
        <td>관리번호</td>
        <td>자치구</td>
        <td>와이파이명</td>
        <td>도로명주소</td>
        <td>상세주소</td>
        <td>설치위치(층)</td>
        <td>설치유형</td>
        <td>설치기관</td>
        <td>서비스구분</td>
        <td>망종류</td>
        <td>설치년도</td>
        <td>실내외구분</td>
        <td>WIFI접속환경</td>
        <td>X좌표</td>
        <td>Y좌표</td>
        <td>작업일자</td>
    </tr>
    <% if(wifi == null) { %>
        <tr><td colspan=100 style="padding: 10px; text-align: center">위치 정보를 입력한 후에 조회해 주세요.</td></tr>
    <% } else { %>
        <%
            double lnt = Double.parseDouble((String) request.getAttribute(("lnt")));
            double lat = Double.parseDouble((String) request.getAttribute(("lat")));
            for (Wifi w : wifi) {
        %>
        <tr class="row-main">
            <td><%=w.distanceFrom(lnt, lat)%></td>
            <td><%=w.getManagementNumber()%></td>
            <td><%=w.getDistrict()%></td>
            <td><a href=<%="/detail?id=" + w.getId() + "&lat=" + lat + "&lnt=" + lnt%>><%=w.getName()%></a></td>
            <td><%=w.getStreet()%></td>
            <td><%=w.getDetailAddress()%></td>
            <td><%=w.getDeployedFloor()%></td>
            <td><%=w.getDeployedType()%></td>
            <td><%=w.getDeploymentOrg()%></td>
            <td><%=w.getServiceType()%></td>
            <td><%=w.getNetType()%></td>
            <td><%=w.getDeployedYear()%></td>
            <td><%=w.getInOrOut()%></td>
            <td><%=w.getAccessEnvironment()%></td>
            <td><%=w.getLatitude()%></td>
            <td><%=w.getLongitude()%></td>
            <td><%=w.getWorkedDate().replace('T', ' ')%></td>
        </tr>
        <% } %>
    <% } %>
</table>

<script>
    let lat = 0
    let lnt = 0
    let latInput = document.getElementById("user-lat");
    latInput.addEventListener("change", e => lat = e.target.value)
    let lntInput = document.getElementById("user-lnt");
    lntInput.addEventListener("change", e => lnt = e.target.value)

    const setCurrLocation = () => {
        navigator.geolocation.getCurrentPosition(p => {
            latInput.value = lat = p.coords.latitude
            lntInput.value = lnt = p.coords.longitude
            fetch('/history/add?lat=' + lat + '&lnt=' + lnt)
                .then(res => console.log(res))
                .catch(err => console.log('history add failed'))
        })
    }
    document.getElementById("btn-location-refresh").addEventListener("click", setCurrLocation)

    document.getElementById("btn-wifi").addEventListener("click", ev => {
        let lat = document.getElementById("user-lat").value;
        let lnt = document.getElementById("user-lnt").value;
        window.location.replace(`/home?lat=` + lat + `&lnt=` + lnt)
    })

    // load data
</script>
</body>
</html>
