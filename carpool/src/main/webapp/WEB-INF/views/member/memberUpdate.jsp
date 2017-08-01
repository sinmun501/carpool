<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    

<div class="page-main-style">
   <h1>회원 정보 수정</h1>
   <form:form commandName="command" action="update.do" enctype="multipart/form-data" id="update_form"> 
   	  <form:hidden path="mem_id" />
      <form:errors element="div" cssClass="error-color"/>
      <ul>
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
   </form:form>
</div>
