<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href='<c:url value="/resources/css/board.css" />' type="text/css" />
<script type="text/javascript" src='<c:url value="/resources/js/jquery-2.0.2.js" />'></script>
<script type="text/javascript">
var commentList;

$(document).ready(function(){
	$('#updateCommentOk').hide();
	
	$.ajax({
		url:'<c:url value="/comment/${vo.no}" />',
		type:'POST',
		success:function(data) {
			commentList = '<table>';
			$.each(data, setCommentList);
			commentList += '</table>';
			
			$('#commentDisplay').html(commentList);
		}
	});


	$('#deleteBoard').click(function(){
		confirm('정말로 삭제하시겠습니까?')?location.href='delete':location.href='/';
		
	});
	
	$('#updateBoard').click(function(){
		location.href='update';
		
	});
	
	$('#list').click(function(){
		location.href='..';
		
	});
	
	$('#btnCommentOk').click(function(){
		var user_id = '${sessionScope.user_id}';
		var bcomment = $('#taComment').val();
		var bno = ${bno};
		var no = ${vo.no};
		
		$.ajax({
			url:'<c:url value="/comment/insert" />',
			type:'POST',
			data:{
				'user_id':user_id,
				'bcomment':bcomment,
				'bno'	 :bno,
				'no'	 :no
			},
			success:function(data) {
				$('#taComment').val('');
				commentList = '<table>';
				$.each(data, setCommentList);
				commentList += '</table>';
				$('#commentDisplay').html(commentList);
			}
		});
		
	});
	
	$('#updateCommentOk').click(function(){
		var user_id = '${sessionScope.user_id}';
		var bcomment = $('#taComment').val();
		var no = ${vo.no};
		var cno = $('#cno').val();
		$.ajax({
			url:'<c:url value="/comment/insert/'+cno+'" />',
			type:'POST',
			data:{
				'user_id':user_id,
				'bcomment':bcomment,
				'no'	:no
			},
			success:function(data) {
				$('#taComment').val('');
				commentList = '<table>';
				$.each(data, setCommentList);
				commentList += '</table>';
				$('#cno').val('');
				$('#commentDisplay').html(commentList);
				$('#updateCommentOk').hide();
			}
		});
		
	});
		
});

	function setCommentList(){
		commentList += '<tr>';
		commentList += '<td class="comment">' + this['user_id'] + '</td>';
		commentList += '<td class="commentinsert">' + this['bcomment'] + '</td>';
		commentList += '<td>' + this['regdate'] + '</td><td>';
		if (this['user_id'] == '${sessionScope.user_id}'){
		var _cno = this['cno'];
		commentList += '<input type="button" value="삭제" ';
		commentList += 'onclick="javascript:deleteComment('+_cno +');"/> ';
		commentList += '<input type="button" value="수정" ';
		commentList += 'onclick="javascript:updateComment('+_cno +');"/>';
		}
		commentList += '</td></tr>';
	}

	function deleteComment(cno) {
		if(confirm(cno + '번 댓글을 정말 삭제하시겠습니까?'))
		{
			$.ajax({
				url : '<c:url value="/comment/${vo.no}/" />' + cno + '/delete',
				type : 'POST',
				success : function(data) {
					commentList = '<table>';
					$.each(data, setCommentList);
					commentList += '</table>';

					$('#commentDisplay').html(commentList);
				}
			});
		}
	}
	

	function updateComment(cno) {
		$.ajax({
			url : '<c:url value="/comment/${vo.no}/" />' + cno + '/update',
			type : 'POST',
			success : function(data) {
				$('#taComment').val(data['bcomment']);
				$('#cno').val(data['cno']);
			}
		});
		$('#updateCommentOk').show();
	}
	
	
</script>
</head>
<body>
<input type="hidden" id="cno" />
<input type="hidden" id="no" value="${vo.no}" />
<table>
	<caption>글 상세보기</caption>
	<tr>
		<th>글번호</th>
		<td>${vo.no}</td>
	</tr>
	<tr>
		<th>제목</th>
		<td class="width">${vo.title}</td>
	</tr>
	<tr>
		<th>작성자</th>
		<td>${vo.userVO.user_name}(${vo.userVO.user_id})</td>
	</tr>
	<tr>
		<th>조회수</th>
		<td>${vo.count}</td>
	</tr>
	<tr>
		<th>작성시간</th>
		<td>${vo.regdate}</td>
	</tr>
	<tr>
		<th>내용</th>
		<td>${vo.content}</td>
	</tr>
</table><br/>
<input type="button" value="리스트" id="list"/>
<c:if test="${sessionScope.user_id == vo.userVO.user_id}" >
<input type="button" value="수정" id="updateBoard"/>
<input type="button" value="삭제" id="deleteBoard"/><br/>
</c:if>
<hr/>
<table>
<tr>
	<td>
		<textarea cols="40" rows="5" name="taComment" id="taComment" 
			style="width:645px;height:100px;"></textarea>
	</td>
	<td>
		${sessionScope.user_id }<br/>
		<input type="button" id="btnCommentOk" value="확인" /><br/>
		
		<input type="button" value="수정" id="updateCommentOk"/>
	
	</td>
</tr>
</table><br/>
<div id="commentDisplay" ></div>
</body>
</html>