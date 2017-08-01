<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- JSTL에 있는 함수를 구현하는 것으로, 자바에 있는 String클래스에 있는 메소드를 태그로 구현. -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-main-style">
	<h2>마이 페이지</h2>
	<p class="align-right">
		<a href="${pageContext.request.contextPath}/member/driverWrite.do">운전자 등록</a>
		<a href="${pageContext.request.contextPath}/member/driverDetail.do">운전자 정보</a>
	</p>
	<ul>
		<c:if test="${fn:endsWith(member.mem_filename, '.jpg') || 
				  fn:endsWith(member.mem_filename, '.JPG') ||
				  fn:endsWith(member.mem_filename, '.gif') ||
				  fn:endsWith(member.mem_filename, '.GIF') ||
				  fn:endsWith(member.mem_filename, '.png') ||
				  fn:endsWith(member.mem_filename, '.PNG')}">
			<div class="align-left">
				<img src="imageView.do?mem_id=${member.mem_id}" style="max-width:150px">
			</div>
		</c:if>
		
		<li>아이디 : ${member.mem_id}</li>
		<li>이름 : ${member.mem_name}</li>
		<li>성별 : ${member.mem_gender}</li>
		<li>회원등급 : ${member.mem_grade}</li>
		<li>전화번호 : ${member.mem_phone}</li>
		<li>이메일 : ${member.mem_email}</li>
		<li>가입날짜 : ${member.mem_date}</li>
	</ul>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="수정" onclick="location.href='update.do'">
		<input type="button" value="탈퇴" onclick="location.href='delete.do'">
	</p>
</div>