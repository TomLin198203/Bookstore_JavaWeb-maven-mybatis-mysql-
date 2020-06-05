<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/5
  Time: 下午 07:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>當前訂單</title>
</head>
<body>
<h1>當前訂單</h1>
<table border="1" width="100%" cellspacing="0" bgcolor="#00bfff">
    <tr bgcolor="gray" bordercolor="gray">
        <td colspan="6">
            訂單編號:${requestScope.order.oid},成交時間:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss"  value="${requestScope.order.ordertime}"/>, 金額:${requestScope.order.total}元
        </td>>
    </tr>
    <c:forEach items="${requestScope.order.orderItemList}" var="orderItem">
    <tr>
        <td><div><img src="<c:url value='/${orderItem.book.image}'/>" width="100" height="150"/></div></td>
        <td>書名:${orderItem.book.bname}</td>
        <td>單價:${orderItem.book.price}元</td>
        <td>作者:${orderItem.book.author}</td>
        <td>數量:${orderItem.count}</td>
        <td>小計:${orderItem.subtotal}元</td>
    </tr>
    </c:forEach>
</table>
<form method="post" action="<c:url value='/OrderServlet'/>" id="form" >
    <input type="hidden" name="method" value="zhiFu"/>
    <input type="hidden" name="oid" value="${requestScope.order.oid}"/>
收貨地址:<input type="text" name="address"/><br/>

</form>

<%--
<a id="pay" href="javascript:document.getElementById('form').submit();">直接結算</a> --%>
<a id="pay" href="javascript:alert('此功能沒有')">直接結算</a>
</body>
</html>
