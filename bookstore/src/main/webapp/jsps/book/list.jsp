<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/4
  Time: 下午 10:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/list.css'/>">
</head>
<body>
<c:forEach items="${requestScope.booklist}" var="book">
<div class="divBook">
    <a href="<c:url value='/BookServlet?method=load&bid=${book.bid}'/>"><img src="<c:url value='/${book.image}'/>" border="0" width="100" height="150"/></a>
    <br/>
    <a href="<c:url value='/BookServlet?method=load&bid=${book.bid}'/>">${book.bname}</a>
</div>
</c:forEach>
</body>
</html>
