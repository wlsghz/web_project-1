<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- <%@ include file="../header.jsp" %> --%>   
<%@ include file="sub_menu.jsp" %>   
  <article>
      <h2> 1:1 고객 게시판 </h2>
      <h3> 고객님의 질문에 대해서 운영자가 1:1 답변을 드립니다.</h3>    
    <form name="formm" method="post">
    <table id="notice">
      <tr>
              <th>제목</th>
              <td>${qnaVO.subject}</td> 
      </tr>
      <tr>
        <th>등록일</th>
        <td> <fmt:formatDate value="${qnaVO.indate}" type="date"/></td>
      </tr>
      <tr>
        <th>질문내용</th>
        <td>${qnaVO.content} 
      </tr>
      <tr>
        <th>답변 내용</th>
        <td>
        	<c:if test="${!empty qnaVO.reply}">
        		${qnaVO.reply}
        	</c:if>
        	<c:if test="${empty qnaVO.reply}">
        		답변 내용이 없습니다.
        	</c:if>
        </td>  
      </tr>
    </table>
    <div class="clear"></div>
     <div id="buttons" style="float:right">
      <input type="button"  value="목록보기"     class="submit"  onclick="location.href='qnaList.do'"> 
      <input type="button"  value="쇼핑 계속하기"  class="cancel"  onclick="location.href='index.do'">  
      </div>
    </form>
  </article>
<%-- <%@ include file="../footer.jsp" %> --%>