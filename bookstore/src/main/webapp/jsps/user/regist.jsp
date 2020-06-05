<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/4
  Time: 下午 12:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>註冊</h1>
<form action="<c:url value='/UserServlet'/>" method="post">
    <input type="hidden" name="method" value="regist"/>
    username:<input type="text" name="username" value="${requestScope.form.username}"/>${requestScope.errors.username}<br/>
    password:<input type="password" name="password" value="${requestScope.form.password}"/>${requestScope.errors.password}<br/>
    email:<input type="email" name="email" value="${requestScope.form.email}"/>${requestScope.errors.email}<br/>
    <input type="submit" value="註冊"/>
</form>

</body>
</html>
