<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 쓰기</title>
<link rel="stylesheet" href='<c:url value="/resources/css/board.css" />' type="text/css" />
</head>
<body>
<form:form action="insert" method="post" commandName="memoVO">
<table>
<caption>글 쓰기</caption>
<tr>
	<th>제목</th>
	<td>
	<input type="text" name="title" value="${memoVO.title}" /><br/>
	<form:errors path="title" />
	</td>
</tr>
<tr>
	<th>이름</th>
	<td><input type="text" name="name" value="${memoVO.name}" /><br/>
	<form:errors path="name" />
	</td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="pwd" value="${memoVO.pwd}" /><br/>
	<form:errors path="pwd" />
	</td>
</tr>
<tr>
	<th>내용</th>
	<td><textarea cols="40" rows="5" name="content" >${memoVO.content}</textarea><br/>
	<form:errors path="content" />
	</td>
</tr>
<tr>
	<td colspan="2" align="center">
		<input type="submit" value="완료" />
	</td>
</tr>
</table>
</form:form>
</body>
</html>