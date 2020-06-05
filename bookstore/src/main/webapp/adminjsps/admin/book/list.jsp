<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>圖書分類</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<meta http-equiv="content-type" content="text/html;charset=utf-8">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css" href="<c:url value='/adminjsps/admin/css/book/list.css'/>">
<script type="text/javascript" src="<c:url value='/jquery/jquery-1.5.1.js'/>"></script>
<link rel="stylesheet" type="text/css" href="<c:url value='/jsps/pager/pager.css'/>" />
<script type="text/javascript" src="<c:url value='/jsps/pager/pager.js'/>"></script>

<script type="text/javascript" src="<c:url value='/adminjsps/admin/js/book/list.js'/>"></script>
  </head>
  

  <c:forEach items="${requestScope.pb.beanList}" var="book">
  <div class="divBook">
      <a href="<c:url value='/admin/AdminBookServlet?method=load&bid=${book.bid}'/>"><img src="<c:url value='/${book.image}'/>" border="0" width="200" height="300"/></a><br/>
      <a href="<c:url value='/admin/AdminBookServlet?method=load&bid=${book.bid}'/>">${book.bname}</a>
  </div>
  </c:forEach>

  <div>
    <br/>
  <a href="${requestScope.pb.url}&pc=1">首頁</a>
    <c:choose>
      <c:when test="${pb.pc eq 1}">上一頁</c:when>
      <c:otherwise>
        <a href="${requestScope.pb.url}&pc=${requestScope.pb.pc-1}">上一頁</a>
      </c:otherwise>
    </c:choose>
      <c:choose>
        <c:when test="${pb.tp <= 5}">
          <c:set var="begin" value="1"/>
          <c:set var="end" value="${pb.tp}"/>
        </c:when>
        <c:otherwise>
          <c:set var="begin" value="${pb.pc-2}"/>
          <c:set var="end" value="${pb.pc+2}"/>
          <c:if test="${begin < 1}">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="5"/>
          </c:if>
          <c:if test="${end > pb.tp}">
            <c:set var="begin" value="${pb.tp-4}"/>
            <c:set var="end" value="${pb.tp}"/>
          </c:if>
        </c:otherwise>
      </c:choose>
    <c:forEach var="i" begin="${begin}" end="${end}">
      <c:choose>
        <c:when test="${i eq pb.pc}">${i}</c:when>
        <c:otherwise>
          <a href="${requestScope.pb.url}&pc=${i}">[${i}]</a>
        </c:otherwise>
      </c:choose>

    </c:forEach>
    <c:choose>
      <c:when test="${pb.pc eq pb.tp}">下一頁</c:when>
      <c:otherwise>
        <a href="${requestScope.pb.url}&pc=${pb.pc+1}">下一頁</a>
      </c:otherwise>
    </c:choose>

  <a href="${requestScope.pb.url}&pc=${requestScope.pb.tp}">尾頁</a>
  </div>

  </body>
 
</html>

