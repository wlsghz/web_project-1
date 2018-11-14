<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nonage Shop</title>
<link href="<%=request.getContextPath() %>/resources/css/shopping.css" rel="stylesheet">
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/product.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/member.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/resources/js/mypage.js"></script>
<decorator:head />
</head>
<body>
	<div id="wrap">
		<!--헤더파일 들어가는 곳 시작 -->
		<header>
			<!--로고 들어가는 곳 시작--->
			<div id="logo">
				<a href="<%=request.getContextPath() %>/index.do"> <img src="<%=request.getContextPath() %>/resources/images/logo.gif" width="180"
					height="100" alt="nonageshop">
				</a>
			</div>
			<!--로고 들어가는 곳 끝-->
			<nav id="catagory_menu">
				<ul>
					<c:choose>
						<c:when test="${empty sessionScope.loginUser}">
							<li><a href="<%=request.getContextPath() %>/loginForm.do" style="width: 110px;">LOGIN&nbsp;(CUSTOMER</a>
								<a href="<%=request.getContextPath() %>/admin/loginForm.do">| ADMIN)</a></li>
							<li>/</li>
							<li><a href="<%=request.getContextPath() %>/contract.do">JOIN</a></li>
						</c:when>
						<c:otherwise>
							<li style="color: orange">
								${sessionScope.loginUser.name}(${sessionScope.loginUser.id})</li>
							<li><a href="<%=request.getContextPath() %>/logout.do">LOGOUT</a></li>
						</c:otherwise>
					</c:choose>
					<li>/</li>
					<li><a href="<%=request.getContextPath() %>/cartList.do">CART</a></li>
					<li>/</li>
					<li><a href="<%=request.getContextPath() %>/mypage.do">MY PAGE</a></li>
					<li>/</li>
					<li><a href="<%=request.getContextPath() %>/qnaList.do">Q&amp;A(1:1)</a></li>
				</ul>
			</nav>

			<nav id="top_menu">
				<ul>
					<li><a href="<%=request.getContextPath() %>/category.do?kind=1&tpage=1">Heels</a></li>
					<li><a href="<%=request.getContextPath() %>/category.do?kind=2&tpage=1">Boots</a></li>
					<li><a href="<%=request.getContextPath() %>/category.do?kind=3&tpage=1">Sandals</a></li>
					<li><a href="<%=request.getContextPath() %>/category.do?kind=4&tpage=1">Sneakers</a></li>
					<li><a href="<%=request.getContextPath() %>/category.do?kind=5&tpage=1">On Sale</a></li>
				</ul>
			</nav>
			<div class="clear"></div>
			<hr>
		</header>
		<!--헤더파일 들어가는 곳 끝 -->