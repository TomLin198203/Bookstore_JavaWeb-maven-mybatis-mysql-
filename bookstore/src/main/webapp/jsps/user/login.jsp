<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/4
  Time: 下午 12:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>登入</h1>
${requestScope.msg}
<form action="<c:url value='/UserServlet'/>" method="post" target="_top">
    <input type="hidden" name="method" value="login"/>
    username:<input type="text" name="username" value="${requestScope.form.username}"/><br/>
    password:<input type="text" name="password" value="${requestScope.form.password}"/><br/>
    <input type="submit" value="登入"/>
</form>

</body>
</html>
