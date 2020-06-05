<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/5
  Time: 上午 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>圖書詳細</title>
</head>
<body>
<div>
    <img src="<c:url value='/${requestScope.book.image}'/>" width="400" height="600"/>
</div>
<ul>
    <li>書名:${requestScope.book.bname}</li>
    <li>作者:${requestScope.book.author}</li>
    <li>單價:${requestScope.book.price}</li>
</ul>
<form id="form" action="<c:url value='/CartServlet'/>" method="post">
    <input type="hidden" name="method" value="add"/>
    <input type="hidden" name="bid" value="${requestScope.book.bid}"/>
    <input type="text" size="3" name="count" value="1"/><br/>
</form>
<a href="javascript:document.getElementById('form').submit();">購買</a>
</body>
</html>
