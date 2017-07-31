<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/confirmId.js"></script>

<div class="page-main-style">
   <h1>회원가입</h1>
   <form:form commandName="command" action="write.do" enctype="multipart/form-data" id="register_form"> <!-- id는 ID중복체크를 Ajax방식으로 하기위해서 넣어둠. -->
      <form:errors element="div" cssClass="error-color"/>
      <ul>
         <li>
            <label for="mem_id">아이디</label>
            <form:input path="mem_id"/>
            <input type="button" id="confirmId" value="ID중복체크">
            <span id="message_id"></span>
            <img src="${pageContext.request.contextPath}/resources/images/ajax-loader.gif" width="16" height="16" style="display:none;">
            <form:errors path="mem_id" cssClass="error-color"/>
         </li>
         <li>
            <label for="mem_pw">비밀번호</label>
            <form:password path="mem_pw"/>
            <form:errors path="mem_pw" cssClass="error-color"/>
         </li>
         <li>
            <label for="mem_name">이름</label>
            <form:input path="mem_name"/>
            <form:errors path="mem_name" cssClass="error-color"/>
         </li>
         <li>
         	<label for="mem_gender">성별</label>
         	<form:select path="mem_gender">
         		<form:option value="남">남자</form:option>
         		<form:option value="여">여자</form:option>
         	</form:select>
         	<form:errors path="mem_gender" cssClass="error-color"/>
         </li>
         <li>
            <label for="mem_phone">전화번호</label>
            <form:input path="mem_phone"/>
            <form:errors path="mem_phone" cssClass="error-color"/>
         </li>
         <li>
            <label for="mem_email">이메일</label>
            <form:input path="mem_email"/>
            <form:errors path="mem_email" cssClass="error-color"/>
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
   </form:form>
</div>
