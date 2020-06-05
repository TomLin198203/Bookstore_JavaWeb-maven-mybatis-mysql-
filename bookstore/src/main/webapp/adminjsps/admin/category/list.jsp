<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>分類列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/category/list.css'/>">
	<link rel="stylesheet" type="text/css" href="<c:url value='/css/css.css'/>">
  </head>
  
  <body>
    <h2 style="text-align: center;">分類列表</h2><br/>
	${msg}
    <table align="center" border="1" cellpadding="0" cellspacing="0">
    	<caption class="captionAddOneLevel">
    	  <a href="<c:url value='/adminjsps/admin/category/add.jsp'/>">添加分類</a>
    	</caption>
    	<tr class="trTitle">
    		<th>分類名稱</th>
			<th>操作1</th>
			<th>操作2</th>
    	</tr>

		<!-- tmlin add -->
		<c:forEach items="${requestScope.categoryList}" var="category">
			<tr>
				<td>${category.cname}</td>
				<td><a href="<c:url value='/admin/AdminCategoryServlet?method=editParentPre&cid=${category.cid}'/>">修改</a></td>
				<td><a onclick="return confirm('你是否要刪除該分類？')" href="<c:url value='/admin/AdminCategoryServlet?method=delete&cid=${category.cid}'/>">刪除</a></td>
			</tr>
		</c:forEach>

    </table>
  </body>
</html>
