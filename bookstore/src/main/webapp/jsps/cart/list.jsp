<%--
  Created by IntelliJ IDEA.
  User: user
  Date: 2020/5/5
  Time: 下午 03:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <title>購物車列表</title>
</head>
<body>
<h1>購物車</h1>
<c:choose>
    <c:when test="${empty cart or fn:length(sessionScope.cart.cartItems) eq 0}">
        <h1> 空車 </h1>>
    </c:when>
    <c:otherwise>
        <table border="1" width="100%" cellspacing="0" bgcolor="aqua">
            <tr>
                <td colspan="7" align="right" style="font-size: 15pt; font-weight: 900">
                    <a href="<c:url value='/CartServlet?method=clear'/>">清空購物車</a>
                </td>
            </tr>
            <tr>
                <th>圖片</th>
                <th>書名</th>
                <th>作者</th>
                <th>單價</th>
                <th>數量</th>
                <th>小計</th>
                <th>操作</th>
            </tr>

            <c:forEach items="${sessionScope.cart.cartItems}" var="cartItem">
                <tr>
                    <td><div><img src="<c:url value='/${cartItem.book.image}'/>" width="400" height="600"/></div></td>
                    <td>${cartItem.book.bname}</td>
                    <td>${cartItem.book.author}</td>
                    <td>${cartItem.book.price}元</td>
                    <td>${cartItem.count}</td>
                    <td>${cartItem.subtotal}元</td>
                    <td><a href="<c:url value='/CartServlet?method=delete&bid=${cartItem.book.bid}'/>">刪除</a></td>
                </tr>
            </c:forEach>
            <tr>
                <td colspan="7" align="right" style="font-size: 15pt;font-weight: 900">
                    合計:${sessionScope.cart.total} 元
                </td>
            </tr>
            <tr>
                <td colspan="7" align="right" style="font-size: 15pt;font-weight: 900">
                    <a id="buy" href="<c:url value='/OrderServlet?method=add'/>">一鍵購買</a>>
                </td>
            </tr>
        </table>
    </c:otherwise>
</c:choose>



</body>
</html>
