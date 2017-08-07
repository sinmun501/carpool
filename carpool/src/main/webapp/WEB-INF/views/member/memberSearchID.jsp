<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#searchId_form').submit(function(){
			if($('#mem_name').val()==''){
				alert('이름를 입력하세요!');
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
   <h1>ID 찾기</h1>
   <p class="align-right">
   		<a href="${pageContext.request.contextPath}/member/searchPw.do">비밀번호 찾기</a>
   </p>
   <form action="search.do" id="searchId_form" method="post">
      <ul>
      	 <li>
      	 	<label for="mem_name">이름</label>
      	 	<input type="text" name="mem_name" id="mem_name" >
      	 </li>
         <li>
            <label for="mem_email">이메일</label>
            <input type="email" name="mem_email" id="mem_email" placeholder="sample@gmail.com" size="56">
         </li>
      </ul>
      
      <div class="align-center">
         <input type="submit" value="아이디 찾기">
         <input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
      </div>
   </form> 
</div>

