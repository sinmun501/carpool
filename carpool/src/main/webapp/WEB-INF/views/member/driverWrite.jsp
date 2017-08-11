<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 

<div class="page-main-style">
   <h1>운전자 등록</h1>
   <form:form commandName="driverCommand" action="driverWrite.do" enctype="multipart/form-data" id="driver_register_form">
   	  <form:hidden path="mem_id" />
      <%-- <form:errors element="div" cssClass="error-color"/> --%>
      <ul>
         <li>
            <label for="driver_license_num">면허증 번호</label>
            <form:input path="driver_license_num" />
            <form:errors path="driver_license_num" cssClass="error-color"/>
         </li>
         <li>
         	<label for="driver_bank">은행</label>
         	<form:select path="driver_bank">
         		<form:option value="국민은행" selected="true">국민은행</form:option>
         		<form:option value="신한은행">신한은행</form:option>
         		<form:option value="우리은행">우리은행</form:option>
         		<form:option value="하나은행">하나은행</form:option>
         		<form:option value="농협">농협</form:option>
         		<form:option value="IBK기업은행">IBK기업은행</form:option>
         		<form:option value="우체국">우체국</form:option>
         		<form:option value="새마을금고">새마을금고</form:option>
         	</form:select>
         </li>
         <li>
         	<label for="driver_account">계좌번호</label>
            <form:input path="driver_account" />
            <form:errors path="driver_account" cssClass="error-color"/>
         </li>
         <li>
			<label for="driver_can_animal">애완동물 탑승 여부</label>
			<input type="radio" name="driver_can_animal" value="Yes">가능
			<input type="radio" name="driver_can_animal" value="No" checked>불가능
		 </li>
		 <li>
			<label for="driver_can_smoking">흡연 여부</label>
			<input type="radio" name="driver_can_smoking" value="Yes">가능
			<input type="radio" name="driver_can_smoking" value="No" checked>불가능
		 </li>
		 <li>	
			<label for="driver_can_charge">충전기 유/무</label>
			<input type="radio" name="driver_can_charge" value="Yes">보유
			<input type="radio" name="driver_can_charge" value="No" checked>미보유
         </li>
         <li>
            <label for="car_upload">자동차 이미지</label>
			<input type="file" name="car_upload" id="car_upload">
         </li>
         <li>
            <label for="car_registration_num">차량번호</label>
         	<form:input path="car_registration_num"/>
            <form:errors path="car_registration_num" cssClass="error-color"/>
         </li>
         <li>
            <label for="car_model">차종</label>
         	<form:input path="car_model"/>
            <form:errors path="car_model" cssClass="error-color"/>
         </li>
      </ul>
      
      <div class="align-center">
         <input type="submit" value="운전자 등록">
         <input type="button" value="홈으로" onclick="location.href='${pageContext.request.contextPath}/main/main.do'">
      </div>
   </form:form>
</div>