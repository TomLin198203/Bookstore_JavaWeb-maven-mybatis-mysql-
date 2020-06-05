<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/4
  Time: 下午 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1> ${msg}</h1>
<ul>
    <!-- <li><a href="<c:url value='/index.jsp'/>">主頁</a></li> -->
    <li><a href="<c:url value='/jsps/main.jsp'/>">主頁</a></li>
    <li><a href="<c:url value='/jsps/user/login.jsp'/>">登入</a></li>
    <li><a href="<c:url value='/jsps/user/regist.jsp'/>">註冊</a></li>
</ul>

</body>
</html>
