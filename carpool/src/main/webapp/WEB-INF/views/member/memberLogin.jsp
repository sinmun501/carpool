<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>

<div class="page-main-style">
   <h1>회원 로그인</h1>
   <p class="align-right">
   		<span id="kakao-logged-group"></span><br>
		<!-- <div id="kakao-profile"></div> -->
   		<a href="${pageContext.request.contextPath}/member/searchPw.do">비밀번호 찾기</a>
   </p>
   <form:form commandName="command" action="login.do" id="login_form">
      <form:errors element="div" cssClass="error-color"/>
      <ul>
         <li>
            <label for="mem_id">아이디</label>
            <form:input path="mem_id"/>
            <form:errors path="mem_id" cssClass="error-color"/>
         </li>
         <li>
            <label for="mem_pw">비밀번호</label>
            <form:password path="mem_pw"/>
            <form:errors path="mem_pw" cssClass="error-color"/>
         </li>
      </ul>
      
      <div class="align-center">
         <input type="submit" value="로그인">
         <%-- <input type="button" value="카카오" onclick="location.href='${pageContext.request.contextPath}/member/kakaoTalk.do'"> --%>
         <input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'"><br><br>
      </div>
      <!-- <div class="align-center">
      		<a id="kakao-login-btn"></a>
			<script>
				// 사용할 앱의 JavaScript 키를 설정해 주세요.
				Kakao.init('83cc2bc07d63097f7b0f406867263b18');
				// 카카오 로그인 버튼을 생성합니다.
				Kakao.Auth.createLoginButton({
				  container: '#kakao-login-btn',
				  success: function(authObj) {
				    // 로그인 성공시, API를 호출합니다.
				    Kakao.API.request({
				      url: '/v1/user/me',
				      success: function(res) {
				    	var id = res.id;
				    	var nickname = res.properties.nickname; 
				    	alert(id + ', ' + nickname);
				        /* alert(JSON.stringify(res)); */
				      },
				      fail: function(error) {
				        alert(JSON.stringify(error));
				      }
				    });
				  },
				  fail: function(err) {
				    alert(JSON.stringify(err));
				  }
				});
			</script>	
      </div> -->
   </form:form>
</div>
