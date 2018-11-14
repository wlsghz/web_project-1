<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- <%@ include file="../header.jsp" %> --%>

<%@ include file="sub_menu.jsp"%>


<article>
	<h2>My Page(진행중인 주문내역)</h2>
	<form name="formm" method="post">
		<table id="cartList">
			<tr>
				<th>주문일자<br/>(주문번호)</th>							
				<th>상품명</th>
				<th>수량</th>
				<th>금액</th>
				<th>주문 상세</th>
			</tr>
			<c:forEach var="oseq" items="${oseqList }" >								
				<c:forEach var="order" items="${orderGroup.get(oseq) }" varStatus="status">
				<tr>
					<c:if test="${status.count eq 1 }">
						<td rowspan="${orderGroup.get(oseq).size() }">
						<fmt:formatDate value="${order.indate }"
										pattern="yyyy-MM-dd"/>																		
						<br/>(${order.oseq})</td>	
					</c:if>
								
						
					<td>${order.pname }</td>		
					<td>${order.quantity }</td>			
					<td>${order.price2*order.quantity }</td>
					<td>주문자명: ${order.mname }<br/>
						배송지 : ${order.address }</td>
				</tr>
				</c:forEach>
			</c:forEach>
		</table>

		<div class="clear"></div>
		<div id="buttons" style="float: right">
			<input type="button" value="쇼핑 계속하기" class="cancel"
				onclick="location.href='index.do'">
		</div>
	</form>
</article>
<%-- <%@ include file="../footer.jsp"%> --%>
