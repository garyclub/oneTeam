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
<form action="empInsert" method="post">
		<table>
			<caption>사원 추가</caption>
			<tr>
				<th>이름</th>
				<td><input type="text" name="ename" /></td>
			</tr>
			<tr>
				<th>급여</th>
				<td><input type="text" name="sal" /></td>
			</tr>
			<tr>
				<th>부서</th>
				<td><select name="deptVO.deptno">
						<c:forEach items="${deptList}" var="de">
							<option value="${de.deptno}">${de.dname}</option>
						</c:forEach>
				</select></td>
			</tr>
		</table>
		<input type="submit" value="등록" />
	</form>
</body>
</html>