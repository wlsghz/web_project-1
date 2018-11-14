<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>


<script>
	var answer=confirm("주문이 완료되었습니다.\n 주문리스트 페이지로 이동하시겠습니까?");
	if(answer){
		location.href="mypage.do";
	}else{
		location.href="cartList.do";
	}
</script>