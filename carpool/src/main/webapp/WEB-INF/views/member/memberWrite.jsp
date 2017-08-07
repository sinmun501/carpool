<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/confirmId.js"></script>

<div class="page-main-style">
   <h1>회원가입</h1>
     <form action="write.do" method="post" id="register_form" enctype="multipart/form-data">
      <ul>
         <li>
            <label for="mem_id">아이디</label>
            <input type="text" name="mem_id" id="mem_id" maxlength="10" size="5">
            <input type="button" id="confirmId" value="ID중복체크">
            <span id="message_id"></span>
            <img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;">
         </li>
         <li>
            <label for="mem_pw">비밀번호</label>
            <input type="password" name="mem_pw" id="mem_pw" maxlength="12">
         </li>
         <li>
            <label for="mem_name">이름</label>
            <input type="text" name="mem_name" id="mem_name" maxlength="10" placeholder="ex)홍길동">
         </li>
         <li>
         	<label for="mem_gender">성별</label>
         	<select id="mem_gender" name="mem_gender">
				<option value="남">남자</option>
				<option value="여">여자</option>
			</select>
         </li>
         <li>
            <label for="mem_phone">전화번호</label>
            <input type="number" id="mem_phone1" maxlength="6" size="2">-
			<input type="number" id="mem_phone2" maxlength="6" size="3">-
			<input type="number" id="mem_phone3" maxlength="6" size="3">
			<input type="hidden" id="mem_phone" name="mem_phone">
         </li>
         <li>
            <label for="mem_email">이메일</label>
            <input type="text" name="mem_email1" id="mem_email1" maxlength="30" size="10">@
            <select id="emailSelect">
				<option>naver.com</option>
				<option>hanmail.net</option>
				<option>nate.com</option>
				<option>gmail.com</option>
			</select>
			<input type="hidden" id="mem_email" name="mem_email"> 
         </li>
          <li>
            <label for="upload">프로필 이미지</label>
			<input type="file" name="upload" id="upload">
         </li>
      </ul>
      
      <div class="align-center">
         <input type="submit" value="회원가입">
         <input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
      </div>
   </form>
</div>
