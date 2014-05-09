<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="<c:url value="/resources/css/board.css" />" type="text/css" />
</head>
<body>
<table>
	<caption>사원 리스트</caption>

<tr>
	<th>사원번호</th>
	<th>이름</th>
	<th>사무번호</th>
	<th>사무명</th>
	<th>급여</th>
	<th>액션</th>
</tr>
<c:forEach items="${empList}" var="vo">
	<tr>
		<td>${vo.empno}</td>
		<td>${vo.ename}</td>
		<td>${vo.deptVO.deptno}</td>
		<td>${vo.deptVO.dname}</td>
		<td>${vo.sal}</td>
		<td>
		<input type="button" value="수정" onclick="location.href='${vo.empno}';"/>|
		<input type="button" value="삭제" onclick="location.href='${vo.empno}/delete';"/>
		</td>
	</tr>		
	</c:forEach>
<tr>
<tr>
	<th colspan="4">총 급여</th>
	<td>${totalSal}</td>
	<th></th>
</tr>
</table>
<a href="empInsert">사원 추가</a>
</body>
</html>