<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <%@ include file="../header.jsp" %> --%>

<%@ include file="sub_menu.jsp"%>

<head>
<style>
	div#pageNum{
		position:relative;
	}
	div#pageNum_body{
		width:200px;
		height:20px;
		line-height:20px;
		text-align:center;
		font-weight : bold;
		
		position:absolute;
		left:0;
		right:0;
		top:0;
		bottom:0;
		margin:auto;
		
	}
	a{
		color:black;
	}
	div#article-header{
		position:relative
	}
	div#article-header h2{
		
	}
	div#search_form{
		overflow:hidden;
	}
	div#search_form form{
		text-align:right;
	}
	div#article-header div#search_form{
		position:absolute;
		right:0;
		bottom:0;
		
	}
</style>	
</head>
<body>
	<article>
		<div id="article-header">
			<h2>Item</h2>		
			<div id="search_form">	
				<form name="search" action="category.do" method="get">
					상품명 : <input type="text" title="상품명 검색" 
					placeholder="검색할 상품명을 입력하세요." name="name" />
					<button class="submit" type="submit" style="width:70px;">검색</button>
					<input type="hidden" name="tpage" value="${param.tpage }"/>
					<input type="hidden" name="kind" value="${param.kind }" />
				</form>
			</div>
		</div>
		<c:forEach items="${productKindList }" var="productVO">
			<div id="item">
				<a href="productDetail.do?pseq=${productVO.pseq}"> <img
					src="product_images/${productVO.image}" />
					<h3>${productVO.name}</h3>
					<p>${productVO.price2}</p>
				</a>
			</div>
		</c:forEach>
		<div class="clear"></div>
		<div id="pageNum">
			<div id="pageNum_body">${paging }</div>
		</div>
	</article>
</body>

<%-- <%@ include file="../footer.jsp" %>     --%>