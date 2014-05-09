<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="ko_KR" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시판 리스트</title>
<link rel="stylesheet" href='<c:url value="/resources/css/board.css" />' type="text/css" />
<script type="text/javascript" src='<c:url value="/resources/js/jquery-2.0.2.js" />'></script>
<script type="text/javascript" src='<c:url value="/resources/js/regist.js" />'></script>
</head>
<body>
접속 유저 : ${sessionScope.user_id}(${sessionScope.user_name}님)<br/>
전체 	게시물 수 : ${result.count}<br/>
<a href='<c:url value="/user/logout" />'>logout</a><br/>
<table width="800" border="1" cellspacing="0">
	<caption>${boardInfo.bname} 리스트</caption>
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
	<c:forEach items="${list}" var="vo" varStatus="st">
	<tr>
		<td>${vo.no}</td>
		<td><a href="${vo.no}/">${vo.title}</a></td>
		<td>${vo.userVO.user_name}(${vo.userVO.user_id})</td>
		<td>${vo.regdate}</td>
		<td align="right">${vo.count}</td>
	</tr>		
	</c:forEach>
</table><br/>
<a href="insert">글쓰기</a>
</body>
</html>