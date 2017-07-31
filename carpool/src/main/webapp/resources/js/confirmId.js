$(document).ready(function(){
	var checkIdDuplicated = 0;
	
	$('#confirmId').click(function(){
		if($('#mem_id').val() == ''){
			alert('아이디를 입력하세요!');
			$('#mem_id').focus();
			return;
		}
		
		$('#message_id').text(''); //메시지 초기화
		$('#loading').show();	   //로딩이미지 노출
		
		$.ajax({
			url:'confirmId.do',
			type:'post',
			data:{mem_id:$('#mem_id').val()},
			dataType:'json',
			cache:false,
			timeout:30000,
			success:function(data){
				$('#loading').hide();  //로딩 이미지 감추기.
				
				if(data.result == 'idNotFound'){
					$('#message_id').css('color','#000').text('등록가능ID');
					checkIdDuplicated = 1;
				}else if(data.result == 'idDuplicated'){
					$('#message_id').css('color','red').text('중복된 ID');
					$('#id').val('').focus();
					checkIdDuplicated = 0;
				}else{
					alert('ID중복체크 오류');
				}
			},
			error:function(){
				$('#loadding').hide(); //로딩 이미지 감추기.
				alert('네트워크 오류 발생');
			}
		});
	});
	
	//아이디 중복 안내 메시지 초기화 및 아이디 중복 값 초기화
	$('#register_form #mem_id').keyup(function(){
		checkIdDuplicated = 0;
		$('#message_id').text('');
	});
	
	//submit 이벤트 발생시 아이디 중복 체크 여부 확인
	$('#register_form').submit(function(){
		if(checkIdDuplicated == 0){
			alert('아이디 중복 체크 필수!!');
			
			if($('#mem_id').val()==''){
				$('#mem_id').focus();
			}else{
				$('confirmId').focus();
			}
			return false;
		}
	});
	
});