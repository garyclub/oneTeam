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
<form action="empUpdate" method="post">
		<table>
			<caption>사원 정보 수정</caption>
			<tr>
				<th>번호</th>
				<td><input type="hidden" name="empno" value="${empVO.empno}"/>${empVO.empno}</td>
			</tr>
			<tr>
				<th>이름</th>
				<td><input type="text" name="ename" required="required" value="${empVO.ename}"/></td>
			</tr>
			<tr>
				<th>급여</th>
				<td><input type="text" name="sal" required="required" value="${empVO.sal}"/></td>
			</tr>
			<tr>
				<th>부서</th>
				<td><select name="deptVO.deptno">
						<c:forEach items="${deptList}" var="de">
							<option value="${de.deptno}"
							<c:if test="${de.deptno==empVO.deptVO.deptno}"> selected</c:if>
							>${de.dname}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="수정완료" />
	</form>
</body>
</html>