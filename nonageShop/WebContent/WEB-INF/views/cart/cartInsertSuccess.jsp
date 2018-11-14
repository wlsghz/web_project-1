<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>

<script>
	var answer=confirm("장바구니에 담았습니다.\n장바구니 페이지로 이동하시겠습니까?");
	if(answer){
		location.href="cartList.do";
	}else{
		history.go(-1);
	}
</script>