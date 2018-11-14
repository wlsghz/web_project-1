<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@ taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator" %>

<%
	response.setHeader("Pragma", "No-cache");
	response.setHeader("Cache-Control", "no-cache");
	response.addHeader("Cache-Control", "no-store");
	response.setDateHeader("Expires", 1L);
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title><decorator:title default="Nonage Admin" /></title>
<link rel="stylesheet" href="<%=request.getContextPath() %>/resources/admin/css/admin.css">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/admin/js/product/product.js"></script>

<decorator:head />
</head>
<body onload="go_ab()">
	<div id="wrap">
		<header>			
			<div id="logo">
				<a href="<%=request.getContextPath() %>/admin/loginForm.do"> 
					<img style="width:800px" src="<%=request.getContextPath() %>/resources/admin/images/bar_01.gif">
					<img src="<%=request.getContextPath() %>/resources/admin/images/text.gif">
				</a>
			</div>	
			<input class="btn" type="button"  value="logout"  style="float: right;"
			   onClick="location.href='<%=request.getContextPath()%>/admin/logout.do'">			
		</header>
		<div class="clear"></div>
		
		
		