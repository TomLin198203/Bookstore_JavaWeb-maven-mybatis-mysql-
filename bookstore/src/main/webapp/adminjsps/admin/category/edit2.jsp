<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改分類</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
	<script type="text/javascript">
		function checkForm() {
			if(!$("#cname").val()) {
				alert("分類名稱不能為空！");
				return false;
			}
			if(!$("#pid").val()) {
				alert("一級分類不能為空！");
				return false;
			}
			if(!$("#desc").val()) {
				alert("分類描述不能為空！");
				return false;
			}
			return true;
		}
	</script>
<style type="text/css">
	body {background: rgb(254,238,189);}
</style>
  </head>
  
  <body>
    <h3>修改2級分類</h3>
    <h1></h1>
    <p style="font-weight: 900; color: red">${msg }</p>
    <form action="<c:url value='/admin/AdminCategoryServlet'/>" method="post" onsubmit="return checkForm()">
    	<input type="hidden" name="method" value="editChild"/>
    	<input type="hidden" name="cid" value="${child.cid }"/>
    	分類名稱：<input type="text" name="cname" value="${child.cname }" id="cname"/><br/>
    	一級分類：<select name="pid" id="pid">
    		<option value="">===選擇1級分類===</option>
<c:forEach items="${parents }" var="parent">
    		<option value="${parent.cid }" <c:if test="${parent.cid eq child.parent.cid }">selected="selected"</c:if> >${parent.cname }</option>
</c:forEach>
    	</select><br/>
    	分類描述：<textarea rows="5" cols="50" name="desc" id="desc">${child.desc }</textarea><br/>
    	<input type="submit" value="修改二級分類"/>
    	<input type="button" value="返回" onclick="history.go(-1)"/>
    </form>
  </body>
</html>
