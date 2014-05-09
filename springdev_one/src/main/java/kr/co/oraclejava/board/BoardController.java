package kr.co.oraclejava.board;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board/{bno}/{pg}")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@RequestMapping(value = "/")
	public String list(@PathVariable int bno, Model model) throws Exception{

		Pagination p = new Pagination(bno);
		List<BoardVO> list = boardService.getBoardList(p);
		BoardInfoVO boardInfo = boardService.getBoardInfo(bno);
		model.addAttribute("bno", bno);
		model.addAttribute("list", list);
		model.addAttribute("boardInfo", boardInfo);
		return "board/list";
	}
	
	@RequestMapping(value = "/insert", method=RequestMethod.GET)
	public String insert(@PathVariable int bno, Model model) {
		
		model.addAttribute("bno", bno);
		return "board/insert";
	}
	
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public String insertAction(
			@PathVariable int bno, 
			Model model,
			BoardVO boardVO,
			HttpSession session) {
		
		UserVO userVO = new UserVO();
		userVO.setUser_id((String)session.getAttribute("user_id"));
		userVO.setUser_name((String)session.getAttribute("user_name"));
		
		boardVO.setUserVO(userVO);
		boardVO.setBno(bno);
		
		try {
			boardService.insertBoard(boardVO);
			return "redirect:";
			//redirect 는 액션을 찾아가기 위함.
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("msg", "게시판 등록 실패");
			model.addAttribute("url", "javascript:history.back();");
			return "result";
			//뷰를 보여줄 jsp를 찾아가기 위함.
		}
	}
}
