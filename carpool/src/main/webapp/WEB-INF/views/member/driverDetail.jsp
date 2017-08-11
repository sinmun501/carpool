<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- JSTL에 있는 함수를 구현하는 것으로, 자바에 있는 String클래스에 있는 메소드를 태그로 구현. -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<div class="page-main-style">
	<h2>운전자 세부정보</h2>
	<table border="1" height="300" class="table table-striped table-bordered table-hover" style="text-align:center;">
		<tr>
			<td rowspan="8" width="250">
				<c:if test="${fn:endsWith(driverMember.car_filename, '.jpg') || 
				  fn:endsWith(driverMember.car_filename, '.JPG') ||
				  fn:endsWith(driverMember.car_filename, '.gif') ||
				  fn:endsWith(driverMember.car_filename, '.GIF') ||
				  fn:endsWith(driverMember.car_filename, '.png') ||
				  fn:endsWith(driverMember.car_filename, '.PNG')}">
					<div class="align-left">
						<img src="carimageView.do?mem_id=${driverMember.mem_id}" style="width:250px;height:290px;">
					</div>
				</c:if>
			</td>    
			<td>아이디</td>  
			<td colspan="3">${driverMember.mem_id}</td>
		</tr>
		<tr>
			<td>면허증번호</td>
			<td>${driverMember.driver_license_num}</td>
		</tr>
		<tr>
			<td>은행</td>
			<td>${driverMember.driver_bank}</td>
		</tr>
		<tr>
			<td>계좌번호</td>
			<td>${driverMember.driver_account}</td>
		</tr>
		<tr>
			<td>차종</td>
			<td>${driverMember.car_model}</td>
		</tr>
		<tr>
			<td>차량번호</td>
			<td>${driverMember.car_registration_num}</td>
		</tr>
		<tr>
			<td>보유돈</td>
			<td>${driverMember.driver_money}</td>
		</tr>
		<tr>
			<td>운전자 등록날짜</td>
			<td>${driverMember.driver_date}</td>
		</tr>
		<tr>
			<td>애완동물 탑승 여부</td>
			<td colspan="2">${driverMember.driver_can_animal}</td>
		</tr>
		<tr>
			<td>흡연 가능 여부</td>
			<td colspan="2">${driverMember.driver_can_smoking}</td>
		</tr>
		<tr>
			<td>충전기 유/무</td>
			<td colspan="2">${driverMember.driver_can_charge}</td>
		</tr>
	</table>
	<hr size="1" width="100%">
	<p class="align-right">
		<input type="button" value="수정" onclick="location.href='driverUpdate.do?mem_id=${driverMember.mem_id},car_seq=${driverMember.car_seq}'">
	</p>
</div>