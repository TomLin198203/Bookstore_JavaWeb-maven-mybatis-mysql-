<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>book_desc.jsp</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/desc.css'/>">
<link rel="stylesheet" type="text/css" href="<c:url value='/jquery/jquery.datepick.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick.js'/>"></script>
<script type="text/javascript" src="<c:url value='/jquery/jquery.datepick-zh-CN.js'/>"></script>

<script type="text/javascript" src="<c:url value='/adminjsps/admin/js/book/desc.js'/>"></script>

<script type="text/javascript">
/* tmlin 刪除
$(function() {
	$("#box").attr("checked", false);
	$("#formDiv").css("display", "none");
	$("#show").css("display", "");	
	
	// 操作和显示切换
	$("#box").click(function() {
		if($(this).attr("checked")) {
			$("#show").css("display", "none");
			$("#formDiv").css("display", "");
		} else {
			$("#formDiv").css("display", "none");
			$("#show").css("display", "");		
		}
	});
});
*/
window.onload=function () {
	var del =document.getElementById("del");
	var edit=document.getElementById("edit");
    var hid=document.getElementById("hid");
    del.onclick=function () {
		hid.setAttribute("value","del");
	};

    edit.onclick=function () {
		hid.setAttribute("value","edit");
	}


};

function setMethod(method) {
	document.getElementById("hid").setAttribute("valye",method);
}

function loadChildren() {
	/*
	1. 获取pid
	2. 发出异步请求，功能之：
	  3. 得到一个数组
	  4. 获取cid元素(<select>)，把内部的<option>全部删除
	  5. 添加一个头（<option>请选择2级分类</option>）
	  6. 循环数组，把数组中每个对象转换成一个<option>添加到cid中
	*/
	// 1. 获取pid
	var pid = $("#pid").val();
	// 2. 发送异步请求
	$.ajax({
		async:true,
		cache:false,
		url:"/goods/admin/AdminBookServlet",
		data:{method:"ajaxFindChildren", pid:pid},
		type:"POST",
		dataType:"json",
		success:function(arr) {
			// 3. 得到cid，删除它的内容
			$("#cid").empty();//删除元素的子元素
			$("#cid").append($("<option>====请选择2级分类====</option>"));//4.添加头
			// 5. 循环遍历数组，把每个对象转换成<option>添加到cid中
			for(var i = 0; i < arr.length; i++) {
				var option = $("<option>").val(arr[i].cid).text(arr[i].cname);
				$("#cid").append(option);
			}
		}
	});
}

/*
 * 点击编辑按钮时执行本函数
 */
function editForm() {
	$("#method").val("edit");
	$("#form").submit();
}
/*
 * 点击删除按钮时执行本函数
 */
 function deleteForm() {
	$("#method").val("delete");
	$("#form").submit();	
}

</script>
  </head>
  
  <body>

  <!-- tmlin -->
  <div>
	  <img src="<c:url value='/${book.image}'/>" border="0"/>
  </div>
  <form id="form" action="<c:url value='/admin/AdminBookServlet'/>" method="post">
	  <input id="hid" type="hidden" name="method"/>
	  <input type="hidden" name="bid" value="${book.bid}"/>
	  <input tpye="hidden" name="image" value="${book.image}"/>
	  圖書名稱<input type="text" name="bname" value="${book.bname}"/><br/>
	  圖書單價<input type="text" name="price" value="${book.price}"/><br/>
	  圖書作者<input type="text" name="author" value="${book.author}"/><br/>
	  圖書分類<select id="cid" style="width: 150px; height: 100px;" name="cid">
	  		  <c:forEach items="${categoryList}" var="category">
				  <c:choose>
					  <c:when test="${category.cid eq book.cid}">
						  <option value="${category.cid}" selected="selected">${category.cname}</option>
					  </c:when>
					  <c:otherwise>
						  <option value="${category.cid}">${category.cname}</option>
					  </c:otherwise>
				  </c:choose>
			  </c:forEach>
              </select>

		<br/>
	  <input id="del" type="submit" value="刪除"/>
	  <input id="edit" type="submit" value="修改"/>
  </form>
  </body>
</html>
