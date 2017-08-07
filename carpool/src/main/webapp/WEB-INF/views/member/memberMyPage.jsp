<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- JSTL에 있는 함수를 구현하는 것으로, 자바에 있는 String클래스에 있는 메소드를 태그로 구현. -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-main-style">
	<h2>마이 페이지</h2>
	<p class="align-right">
		<c:if test="${empty driverMember}">
			<a href="${pageContext.request.contextPath}/member/driverWrite.do">운전자 등록</a>
		</c:if>
		<c:if test="${!empty driverMember}">
			<a href="${pageContext.request.contextPath}/member/driverDetail.do">운전자 정보</a>
		</c:if>
	</p>
	<table border="1" height="300" class="table table-striped table-bordered table-hover" style="text-align:center;">
		<tr>
			<td rowspan="6" width="250">
				<c:if test="${fn:endsWith(member.mem_filename, '.jpg') || 
				  fn:endsWith(member.mem_filename, '.JPG') ||
				  fn:endsWith(member.mem_filename, '.gif') ||
				  fn:endsWith(member.mem_filename, '.GIF') ||
				  fn:endsWith(member.mem_filename, '.png') ||
				  fn:endsWith(member.mem_filename, '.PNG')}">
					<div class="align-left">
						<img src="imageView.do?mem_id=${member.mem_id}" style="width:250px;height:280px;">    
					</div>
				</c:if>
			</td>    
			<td>아이디</td>  
			<td colspan="3">${member.mem_id}</td>
		</tr>
		<tr>
			<td>이름</td>
			<td colspan="3">${member.mem_name}</td>
		</tr>	
		<tr>
			<td width="150">성별 </td>
			<td width="150">${member.mem_gender}</td>
			<td width="150">등급 </td>
			<td width="150">${member.mem_grade}</td>  
		</tr>
		<tr>
			<td>전화번호 </td>
			<td colspan="3">${member.mem_phone}</td>
		</tr>  
		<tr>
			<td>이메일</td>
			<td colspan="3"> ${member.mem_email}</td>
		</tr>
		<tr>
			<td>가입날짜</td>
			<td colspan="3"> ${member.mem_date}</td>
		</tr>   
	</table>
	
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" id="updateForm" value="수정" onclick="location.href='update.do?mem_id=${member.mem_id}'">
		<input type="button" value="탈퇴" onclick="location.href='delete.do'">
	</p>
</div>