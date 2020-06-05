<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/4
  Time: 下午 05:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1 style="text-align: center;">線上書城</h1>
<div style="font-size: 10pt;">

    <c:choose>
        <c:when test="${empty sessionScope.session_user}">
            <a href="<c:url value='/jsps/user/regist.jsp'/>" target="_parent">註冊</a>
            <a href="<c:url value='/jsps/user/login.jsp'/>" target="_parent">登入</a>
        </c:when>
        <c:otherwise>
            你好: ${sessionScope.session_user.username}
            <a href="<c:url value='/jsps/cart/list.jsp'/>" target="body">我的購物車</a>
            <a href="<c:url value='/OrderServlet?method=myOrders'/>" target="body">我的訂單</a>
            <a href="<c:url value='/UserServlet?method=quit'/>" target="_parent">退出</a>
        </c:otherwise>
    </c:choose>
</div>

</body>
</html>
