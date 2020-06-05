<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/4
  Time: 下午 05:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table class="table" align="center" width="90%">
    <tr style="background: aqua;height: 120px;">
        <td colspan="2" align="center">
            <iframe frameborder="0" src="<c:url value='/jsps/top.jsp'/>" name="top">
            </iframe>
        </td>
    </tr>
    <tr>
        <td width="120" style="padding: 5px;" align="center" valign="top">
            <%-- <iframe frameborder="0" width="120" src="<c:url value='/jsps/left.jsp'/>" name="top"> --%>
            <iframe frameborder="0" width="120" src="<c:url value='/CategoryServlet?method=findAll'/>" name="left"></iframe>
        </td>
        <td>
            <iframe frameborder="0" width="800" height="600" src="<c:url value='/jsps/body.jsp'/>" name="body"></iframe>
        </td>
    </tr>
</table>
</body>
</html>
