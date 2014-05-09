<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>우편번호검색</title>
<link rel="stylesheet" href='<c:url value="/resources/css/board.css" />' type="text/css" />
<%-- <script type="text/javascript" src='<c:url value="/js/regist.js" />'></script> --%>
<script type="text/javascript" src='<c:url value="/resources/js/jquery-2.0.2.js" />'></script>
<script type="text/javascript">
$(function(){
	$('.zip').click(function(){
		var zipcode = $(this).find('.zipcode').html();
		var address = $(this).find('.address').html();
		$('#user_zipcode', opener.document).val(zipcode);
		$('#user_addr1', opener.document).val(address);
		$('#addrPut', opener.document).focus();
		self.close();
	});
});
</script>
</head>
<body>
<form action="zipcode" method="post">
	<table>
		<caption>우편번호검색</caption>
		<tr>
			<th colspan="2" width="100%">
				동 : <input type="text" name="area" 
						value="${area}" autofocus="autofocus" required="required"/>
				<input type="submit" value="검색" />
			</th> 
		</tr>
		
		<c:if test="${area != null}">
			<tr>
				<th width="60">우편번호</th>
				<th>주소</th>
			</tr>
			<c:if test="${empty list}">
				<td colspan="2">${area}동은 존재하지 않습니다.</td>
			</c:if>
			<c:forEach items="${list}" var="vo">
<!-- 			<tr> -->
<!-- 				<td><a href="javascript:;"  -->
<%-- 					onclick="closeZip('${vo.zipcode1}','${vo.zipcode2}','${vo.addr1}');">${vo.zipcode1}-${vo.zipcode2}</a></td> --%>
<!-- 				<td><a href="javascript:;" -->
<%-- 				    onclick="closeZip('${vo.zipcode1}','${vo.zipcode2}','${vo.addr1}');"><c:out value="${vo.addr1}" /></a></td> --%>
<!-- 			</tr> -->

				<tr>
					<td colspan="2">
					<div class="zip">
						<span class="zipcode">${vo.zipcode1}-${vo.zipcode2}</span>
						<span class="address">${vo.addr1}</span>
					</div>
					</td>
				</tr>
			</c:forEach>
		</c:if>
	</table>
</form>
</body>
</html>