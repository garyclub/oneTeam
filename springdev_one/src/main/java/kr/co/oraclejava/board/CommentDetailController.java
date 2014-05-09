package kr.co.oraclejava.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment/{no}/{cno}")
public class CommentDetailController {

	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentDetailController.class);
	@RequestMapping(
			value="/delete",
			headers="Accept=application/json;charset=UTF-8",
			produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<BoardCommentVO> deleteComment(
			@PathVariable long cno,
			BoardCommentVO boardCommentVO,
			HttpSession session,
			@PathVariable long no) throws Exception{
		
		boardCommentVO.setCno(cno);
		boardCommentVO.setUser_id((String)session.getAttribute("user_id"));
		logger.info("삭제할 커맨트 번호 : " + cno);
		boardService.deleteComment(boardCommentVO);
		return boardService.getCommentList(no);
	}
	
	@RequestMapping(
			value="/update",
			headers="Accept=application/json;charset=UTF-8",
			produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public BoardCommentVO updateComment(
			@PathVariable long cno,
			BoardCommentVO boardCommentVO,
			HttpSession session,
			@PathVariable long no) throws Exception{
		
		boardCommentVO.setCno(cno);
		boardCommentVO.setUser_id((String)session.getAttribute("user_id"));
		return boardService.getComment(boardCommentVO);
	}
}
