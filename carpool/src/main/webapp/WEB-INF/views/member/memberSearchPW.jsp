<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#searchPw_form').submit(function(){
			if($('#mem_id').val()==''){
				alert('아이디를 입력하세요!');
				$('#mem_id').focus();
				return false;
			}
			if($('#mem_email').val()==''){
				alert('이메일을 입력하세요!');
				$('#mem_email').focus();
				return false;
			}
			
			alert("메일로 전송되었습니다.\n메일을 확인하세요.");
		});
	});
</script>

<div class="page-main-style">
   <h1>비밀번호 찾기</h1>
   <%-- <p class="align-right">
   		<a href="${pageContext.request.contextPath}/member/search.do">ID 찾기</a>
   </p> --%>
   <form action="searchPw.do" id="searchPw_form" method="post">
      <ul>
      	 <li>
      	 	<label for="mem_id">아이디</label>
      	 	<input type="text" name="mem_id" id="mem_id" >
      	 </li>
         <li>
            <label for="mem_email">이메일</label>
            <input type="email" name="mem_email" id="mem_email" placeholder="sample@gmail.com" size="56">
         </li>
      </ul>
      
      <div class="align-center">
         <input type="submit" value="비밀번호 찾기">
         <input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
      </div>
   </form>
</div>
