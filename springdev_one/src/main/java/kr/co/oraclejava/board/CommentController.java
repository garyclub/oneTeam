package kr.co.oraclejava.board;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/comment")
public class CommentController {

	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(CommentController.class);
	@RequestMapping(
			value="/insert",
			headers="Accept=application/json;charset=UTF-8",
			produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<BoardCommentVO> insertComment(BoardCommentVO boardCommentVO) throws Exception{
		boardService.insertComment(boardCommentVO);		
		return boardService.getCommentList(boardCommentVO.getNo());
	}
//	@ResponseBody // 내가 원하는 데이터타입으로 바꿔서 뷰에 쏴준다.
				  //4.0 스프링부터는 @ResponseBody 없이 컨트롤러를 @RestController 로 바꿔 사용하면 됨. 
	@RequestMapping(
			value="/{no}",
			headers="Accept=application/json;charset=UTF-8",
			produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<BoardCommentVO> listComment(@PathVariable long no) throws Exception{
		
		return boardService.getCommentList(no);
	}
	
	@RequestMapping(
			value="/insert/{cno}",
			headers="Accept=application/json;charset=UTF-8",
			produces={MediaType.APPLICATION_JSON_VALUE})
	@ResponseBody
	public List<BoardCommentVO> updateComment(BoardCommentVO boardCommentVO, @PathVariable long cno) throws Exception{
		boardCommentVO.setCno(cno);
		boardService.updateComment(boardCommentVO);		
		
		return boardService.getCommentList(boardCommentVO.getNo());
	}
}
