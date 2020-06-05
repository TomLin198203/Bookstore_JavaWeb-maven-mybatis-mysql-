<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/5
  Time: 下午 07:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的訂單</title>
</head>
<body>
<h1>我的訂單</h1>
<table border="1" width="100%" cellspacing="0" bgcolor="aqua">
    <c:forEach items="${requestScope.orderList}" var="order">

    <tr bgcolor="gray" bordercolor="gray">
        <td colspan="6">
            訂單編號:${order.oid} 成交時間:<fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${order.ordertime}"/> 金額:${order.total}元
            <c:choose>
                <c:when test="${order.state eq 1}">
                    <a href="<c:url value='/OrderServlet?method=load&oid=${order.oid}'/>">付款</a>
                </c:when>
                <c:when test="${order.state eq 2}">等待發貨</c:when>
                <c:when test="${order.state eq 3}">
                    <a href="<c:url value='/OrderServlet?method=confirm&oid=${order.oid}'/>">確認收貨</a>
                </c:when>
                <c:when test="${order.state eq 4}">交易成功</c:when>
            </c:choose>
        </td>>
    </tr>
        <c:forEach items="${order.orderItemList}" var="orderItem">
    <tr bordercolor="gray" align="center">
        <td width="15%">
            <div><img src="<c:url value='/${orderItem.book.image}'/>" height="75"/></div>
        </td>
        <td>書名:${orderItem.book.bname}</td>
        <td>單價:${orderItem.book.price}元</td>
        <td>作者:${orderItem.book.author}</td>
        <td>數量:${orderItem.count}</td>
        <td>小計:${orderItem.subtotal}元</td>

    </tr>
        </c:forEach>
    </c:forEach>
</table>
</body>
</html>
