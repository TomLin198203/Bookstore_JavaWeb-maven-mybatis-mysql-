<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>訂單詳細</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/order/desc.css'/>">
  </head>
  
<body>
	<div class="divOrder">
		<span>訂單號：${order.oid }
<c:choose>
	<c:when test="${order.state eq 1 }">(等待付款)</c:when>
	<c:when test="${order.state eq 2 }">(準備發貨)</c:when>
	<c:when test="${order.state eq 3 }">(等待確認)</c:when>
	<c:when test="${order.state eq 4 }">(交易成功)</c:when>
	<c:when test="${order.state eq 5 }">(已取消)</c:when>
</c:choose>
		　　　下單時間：${order.ordertime }
		</span>
	</div>
	<div class="divRow">
		<div class="divContent">
			<dl>
				<dt>收貨人信息</dt>
				<dd>姓名: ${order.user.username}</dd>
				<dd>地址: ${order.address }</dd>

			</dl>
		</div>
		<div class="divContent">
			<dl>
				<dt>商品清單</dt>
				<dd>
					<table cellpadding="0" cellspacing="0">
						<tr>
							<th class="tt">商品名稱</th>
							<th class="tt" align="left">單價</th>
							<th class="tt" align="left">數量</th>
							<th class="tt" align="left">小計</th>
						</tr>




<c:forEach items="${order.orderItemList }" var="orderItem">
						<tr style="padding-top: 20px; padding-bottom: 20px;">
							<td class="td" width="400px">
								<div class="bookname">
								  <img align="middle" width="70" src="<c:url value='/${orderItem.book.image }'/>"/>
								  ${orderItem.book.bname }
								</div>
							</td>
							<td class="td" >
								<span>&yen;${orderItem.book.price }</span>
							</td>
							<td class="td">
								<span>${orderItem.count }</span>
							</td>
							<td class="td">
								<span>&yen;${orderItem.subtotal }</span>
							</td>			
						</tr>
</c:forEach>

							
							
							
					</table>
				</dd>
			</dl>
		</div>
		<div class="divBtn">
			<span class="spanTotal">合　　計：</span>
			<span class="price_t">&yen;${order.total }</span><br/>

<c:if test="${order.state eq 2 and btn eq 'deliver' }">
	<a id="deliver" href="<c:url value='/admin/AdminOrderServlet?method=deliver&oid=${order.oid }'/>">發　　貨</a>
</c:if>
<c:if test="${order.state eq 1 and btn eq 'cancel' }">
	<a id="cancel" href="<c:url value='/admin/AdminOrderServlet?method=cancel&oid=${order.oid }'/>">取　　消</a>
</c:if>
		</div>
	</div>
</body>
</html>

