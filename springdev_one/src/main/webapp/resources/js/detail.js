/**
 * 	댓글
 */

$(document).ready(function(){
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
		var user_id = $('#user_id').val();
		var bcomment = $('#taComment').val();
		var bno = $('#bno').val();
		var no = $('#no').val();
		
		$.ajax({
			url:$('#comment_url').val(),
			type:'POST',
			data:{
				'user_id':user_id,
				'bcomment':bcomment,
				'bno'	 :bno,
				'no'	 :no
			},
			success:function(data) {
				alert(data + '입력성공');
				$('#taComment').val(' ');
			}
		});
		
	});
		
});