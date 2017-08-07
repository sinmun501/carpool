<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>  
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/confirmId.js"></script>

<div class="page-main-style">
   <h1>회원 정보 수정</h1>
     <form action="update.do" method="post" id="update_form" enctype="multipart/form-data">
      <input type="hidden" id="mem_id" name="mem_id" value="${command.mem_id}">
      <ul>
         <li>
            <label for="mem_pw">비밀번호</label>
            <input type="password" name="mem_pw" id="mem_pw" maxlength="12">
         </li>
         <li>
            <label for="mem_name">이름</label>
            <input type="text" name="mem_name" id="mem_name" value="${command.mem_name}" maxlength="10" placeholder="ex)홍길동">
         </li>
         <li>
         	<label for="mem_gender">성별</label>
         	<select id="mem_gender" name="mem_gender">
         		<c:if test="${command.mem_gender eq '남'}">
         			<option value="남" selected>남자</option>
					<option value="여">여자</option>
         		</c:if>
         		<c:if test="${command.mem_gender eq '여'}">
         			<option value="남">남자</option>
					<option value="여" selected>여자</option>
         		</c:if>
				
			</select>
         </li>
         <li>
            <label for="mem_phone">전화번호</label>
            <input type="number" id="mem_phone1" maxlength="6" size="2" value="${phone[0]}">-
			<input type="number" id="mem_phone2" maxlength="6" size="3" value="${phone[1]}">-
			<input type="number" id="mem_phone3" maxlength="6" size="3" value="${phone[2]}">
			<input type="hidden" id="mem_phone" name="mem_phone">
         </li>
         <li>
            <label for="mem_email">이메일</label>
            <input type="text" name="mem_email1" id="mem_email1" value="${email[0]}" maxlength="30" size="10">@
            <select id="emailSelect">
            	<c:if test="${email[1] eq 'naver.com'}">
            		<option selected>naver.com</option>
            		<option>hanmail.net</option>
            		<option>nate.com</option>
            		<option>google.co.kr</option>
            	</c:if>
				<c:if test="${email[1] eq 'hanmail.net'}">
            		<option>naver.com</option>
            		<option selected>hanmail.net</option>
            		<option>nate.com</option>
            		<option>google.co.kr</option>
            	</c:if>
            	<c:if test="${email[1] eq 'nate.com'}">
            		<option>naver.com</option>
            		<option>hanmail.net</option>
            		<option selected>nate.com</option>
            		<option>google.co.kr</option>
            	</c:if>
            	<c:if test="${email[1] eq 'gmail.com'}">
            		<option>naver.com</option>
            		<option>hanmail.net</option>
            		<option>nate.com</option>
            		<option selected>gmail.com</option>
            	</c:if>
			</select>
			<input type="hidden" id="mem_email" name="mem_email"> 
         </li>
         <li>
            <label for="upload">프로필 이미지</label>
			<input type="file" name="upload" id="upload" value="${command.mem_filename}">
			<c:if test="${!empty command.mem_filename}">
 				<br>
 				<span>(${command.mem_filename})파일이 등록되어 있습니다. 다시 업로드 하면 기존 파일은 삭제됩니다.</span>
 			</c:if>
         </li>
      </ul>
      
      <div class="align-center">
         <input type="submit" value="수정">
         <input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
      </div>
   </form>
</div>
