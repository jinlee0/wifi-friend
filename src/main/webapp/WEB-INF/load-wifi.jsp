<%--
  Created by IntelliJ IDEA.
  User: wls43
  Date: 2023-05-05
  Time: 오후 4:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>와이파이 정보 업데이트</title>
    <style>
      body {
        align-content: center;
        text-align: center;
      }
    </style>
</head>
<body>
<h1 id="main-text">Loading... 오래 걸릴 수 있습니다.</h1>
<br/>
<a href="/">홈으로 가기</a>
<script>
    console.log('${count}')
    if ('${count}') {
        document.getElementById("main-text").innerHTML = '${count}개의 WIFI 정보를 성공적으로 저장하였습니다.'
    } else {
        location.replace("/load-wifi/load");
    }
</script>
</body>
</html>
