<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<ul class="header-member">
	<c:if test="${empty user_id}">
		<li><a href="${pageContext.request.contextPath}/member/write.do">회원가입</a></li>
		<li><a href="${pageContext.request.contextPath}/member/login.do">로그인</a></li>
	</c:if>
	<c:if test="${!empty user_id}">
		<li><a href="${pageContext.request.contextPath}/member/mypage.do">마이페이지</a></li>
		<li>${user_id}님 로그인중</li>
		<li><a href="${pageContext.request.contextPath}/member/logout.do">로그아웃</a></li>
	</c:if>
</ul>
<div class="align-center">
	<h1>CarPool</h1>
</div>
<div class="align-right">
	<a href="${pageContext.request.contextPath}/main/main.do">홈으로</a>
</div>