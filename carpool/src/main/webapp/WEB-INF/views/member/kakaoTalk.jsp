<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.2.1.min.js"></script>
<script src="https://developers.kakao.com/sdk/js/kakao.min.js"></script>

<%
		
%>

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

	
   