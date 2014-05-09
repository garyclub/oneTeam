<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href='<c:url value="/resources/css/board.css" />' type="text/css" />
<script type="text/javascript" src='<c:url value="/resources/js/jquery-2.0.2.js" />'></script>
<script type="text/javascript">
$(document).ready(function(){
	$('#goLogin').click(function(){
		location.href='login';
	});
		
});
</script>
</head>
<body>
<div cssClass="msg_ok">${userVO.user_id}(${userVO.user_name}) 님축하합니다. 가입되셨습니다.</div>
<table>
	<caption>회원 가입 확인</caption>
	<tr>
		<th>아이디</th>
		<td><input type="text" name="user_id" autofocus="autofocus" value="${userVO.user_id}" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="user_name" value="${userVO.user_name}" readonly="readonly"/></td>
	</tr>
	<tr>
		<th>성별</th>
		<td>
			<c:if test="${userVO.user_gender == 1}">남</c:if>
			<c:if test="${userVO.user_gender == 2}">여</c:if>
		</td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="text" name="user_email" value="${userVO.user_email}" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td><input type="text" name="user_phone" value="${userVO.user_phone}" readonly="readonly"/></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td><input type="text" name="user_zipcode" class="textZipcode" value="${userVO.user_zipcode}" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input type="text" name="user_addr1" 
		class="input_text_300" id="user_addr1" value="${userVO.user_addr1}" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<th>상세주소</th>
		<td><input type="text" id="addrPut" name="user_addr2" 
		class="input_text_300" value="${userVO.user_addr2}" readonly="readonly"/>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="button" value="가입완료" id="goLogin"/>
		</td>
	</tr>
</table>
</body>
</html>