<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function() {
		$('#delete_form').submit(function(){
			alert($('#mem_id').val() + '님 계정이 탈퇴되었습니다.');
		});
	});
</script>

<div class="page-main-style">
   <h1>회원 탈퇴</h1>
   <form:form commandName="command" action="delete.do" id="delete_form"> 
   	  <form:hidden path="mem_id" />
      <form:errors element="div" cssClass="error-color"/>
      <ul>
         <li>
            <label for="mem_pw">비밀번호</label>
            <form:password path="mem_pw"/>
            <form:errors path="mem_pw" cssClass="error-color"/>
         </li>
      </ul>
      
      <div class="align-center">
         <input type="submit" value="전송">
         <input type="button" value="마이 페이지" onclick="location.href='mypage.do'">
      </div>
   </form:form>
</div>
