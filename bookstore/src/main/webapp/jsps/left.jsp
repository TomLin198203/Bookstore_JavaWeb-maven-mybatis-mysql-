<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/4
  Time: 下午 05:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <base target="body"/>
</head>
<body>
<div>
    <a href="<c:url value='/BookServlet?method=findAll'/>">全部分類</a>
</div>
<c:forEach items="${requestScope.categoryList}" var="category">
    <div>
        <a href="<c:url value='/BookServlet?method=findByCategory&cid=${category.cid}'/>">${category.cname}</a>
    </div>
</c:forEach>
</body>
</html>
