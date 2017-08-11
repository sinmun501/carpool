<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#searchPw_form').submit(function(e){
			e.preventDefault();
			
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
			
			/* alert("메일로 전송되었습니다.\n메일을 확인하세요."); */
			
			$.ajax({
				url:'searchPwAlert.do',
				type:'post',
				data:{
					mem_id:$("#mem_id").val(),
					mem_email:$("#mem_email").val()
				},
				dataType:'json',
				success:function(data){
					var mem_auth = data.mem_auth;
					
					if(mem_auth == 1 || mem_auth == 2){
						alert("메일로 전송되었습니다.\n메일을 확인하세요.");
						location.href='login.do';
					}else if(mem_auth == 0){
						alert("입력하신 계정이 존재 하지 않습니다.");
						location.href='searchPw.do';
					}else if(mem_auth == 3){
						alert("이메일이 일치하지 않습니다.");
					}else{
						alert("올바르지 않는 계정입니다");
						location.href='searchPw.do';
					}
				},
				error:function(){
					alert('네트워크 오류');
				}
			});
		});
	});
</script>

<div class="page-main-style">
   <h1>비밀번호 찾기</h1>
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
