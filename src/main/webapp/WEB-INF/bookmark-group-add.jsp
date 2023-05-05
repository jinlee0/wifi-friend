<%@ page import="com.wififriend.web.entity.History" %>
<%@ page import="java.util.List" %>
<%@ page import="com.wififriend.web.entity.Wifi" %><%--
  Created by IntelliJ IDEA.
  User: wls43
  Date: 2023-05-05
  Time: 오후 8:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>즐겨찾기 그룹 추가</title>
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
<h1>즐겨찾기 그룹 추가</h1>
<br/>
<a href="/">홈</a> |
<a href="/history">위치 히스토리 목록</a> |
<a href="/load-wifi">Open API 와이파이 정보 가져오기</a> |
<a href="/bookmark-list">즐겨 찾기 보기</a> |
<a href="/bookmark-group">즐겨 찾기 그룹 관리</a>
<br/>
<button>즐겨찾기 추가하기</button>
<br/>
<table>
    <tr><td>북마크 이름</td><td><input id="input-name" /></td></tr>
    <tr><td>순서</td><td><input id="input-ordinal" type="number"/></td></tr>
    <tr><td colspan=100><button onclick="onAddButtonClicked()">추가</button></td></tr>
</table>
<br/>
<script>
    const onAddButtonClicked = () => {
        let name = document.getElementById('input-name').value;
        let ordinal = document.getElementById('input-ordinal').value;
        fetch('/bookmark-group-add?name=' + name + '&ordinal=' + ordinal)
            .then(res => {
                console.log('BG edit success')
                window.location.replace('/bookmark-group')
            }).catch(err => console.error(err))
    }
</script>
</body>
</html>
