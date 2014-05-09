package kr.co.oraclejava.board;

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
@RequestMapping("/board/{bno}/{pg}/{no}")
public class BoardDetailController {
	private static final Logger logger = LoggerFactory.getLogger(BoardDetailController.class);
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping(value = "/", method=RequestMethod.GET)
	public String detail(@PathVariable long no, @PathVariable int bno,Model model) throws Exception{
		BoardVO vo = null;
		
		try {
			vo = boardService.getDetailUpCount(no);
			model.addAttribute("vo", vo);
			model.addAttribute("bno", bno);
			return "board/detail";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage().replace("\n", " "));
			model.addAttribute("url", "..");
			return "result";
		}
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.GET)
	public String update(@PathVariable long no, Model model) throws Exception {
		try {
			BoardVO vo = boardService.getDetail(no);
			model.addAttribute("vo", vo);
			return "board/update";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage().replace("\n", " "));
			model.addAttribute("url", "..");
			return "result";
		}		
	}
	
	@RequestMapping(value = "/update", method=RequestMethod.POST)
	public String updateAction(
			@PathVariable int bno, 
			Model model,
			BoardVO boardVO,
			HttpSession session,
			@PathVariable long no) {
		
		UserVO userVO = new UserVO();
		userVO.setUser_id((String)session.getAttribute("user_id"));		
		boardVO.setUserVO(userVO);
		boardVO.setBno(bno);
		boardVO.setNo(no);
		
		try {
			boardService.updateBoard(boardVO);
			return "redirect:";
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("msg", e.getMessage().replace("\n", " "));
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
	
	@RequestMapping(value = "/delete", method=RequestMethod.GET)
	public String deleteAction(@PathVariable int bno, 
			Model model,
			BoardVO boardVO,
			HttpSession session,
			@PathVariable long no) throws Exception {
		
		UserVO userVO = new UserVO();
		userVO.setUser_id((String)session.getAttribute("user_id"));		
		boardVO.setUserVO(userVO);
		boardVO.setBno(bno);
		boardVO.setNo(no);
		
		try {
			boardService.deleteBoard(boardVO);		
			return "redirect:..";
		} catch (Exception e) {
			logger.info(e.getMessage());
			model.addAttribute("msg", e.getMessage().replace("\n", " "));
			model.addAttribute("url", "javascript:history.back();");
			return "result";
		}
	}
}
