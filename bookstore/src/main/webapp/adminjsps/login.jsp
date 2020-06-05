<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>管理員登入頁面</title>
    
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
			if(!$("#adminname").val()) {
				alert("管理員名稱不能為空！");
				return false;
			}
			if(!$("#adminpwd").val()) {
				alert("管理員密碼不能為空！");
				return false;
			}
			return true;
		}
	</script>
  </head>
  
  <body>
<h1>管理員登入頁面</h1>
<hr/>
  <p style="font-weight: 900; color: red">${msg }</p>
<form action="<c:url value='/AdminServlet'/>" method="post" onsubmit="return checkForm()" target="_top">
	<input type="hidden" name="method" value="login"/>
	管理員帳戶：<input type="text" name="adminname" value="" id="adminname"/><br/>
	密　　　碼：<input type="password" name="adminpwd" id="adminpwd"/><br/>
	<input type="submit" value="進入後台"/>
</form>
  </body>
</html>
