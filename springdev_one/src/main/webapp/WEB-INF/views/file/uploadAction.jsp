<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
업로드 파일 :<a href="download?fileName=${originalName}"> ${originalName}</a>(${fileSize})<br/>
</hr>
<c:if test="${isImage == true}">
<img src="download?fileName=${originalName}" width="100" /><br/>
<img src="download?fileName=sm_${originalName}" />
</c:if>

</body>
</html>