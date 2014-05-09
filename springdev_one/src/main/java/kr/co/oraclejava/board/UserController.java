package kr.co.oraclejava.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import kr.co.oraclejava.mail.MailAction;

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	//logger.info(userVO.toString());
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public void login() {}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public ModelAndView loginAction(UserVO userVO, HttpSession session) {
		logger.info("로그인중...");
		userVO.setUser_pwd(DigestUtils.md5Hex(userVO.getUser_pwd()));
		ModelAndView mav = new ModelAndView("result");
		try {
			UserVO userInfo = userService.getUser(userVO);
			session.setAttribute("user_id", userInfo.getUser_id());
			session.setAttribute("user_name", userInfo.getUser_name());
			session.setAttribute("user_level", userInfo.getUser_level());
			
			
			mav.addObject("msg", userInfo.getUser_name() + "님이 로그인 되었습니다.");
			mav.addObject("url", "../board/1/1/");
		} catch (Exception e) {
			e.printStackTrace();
			mav.addObject("msg", e.getMessage().replace("\n", " "));
			mav.addObject("url", "login");
		}
		
		return mav;
	}

	
	@RequestMapping(value="/regist", method=RequestMethod.GET)
	public void regist() {}
	
	@RequestMapping(value="/regist", method=RequestMethod.POST)
	public String registAction(@Valid UserVO userVO, 
									BindingResult br, 
									Model model, 
									HttpServletRequest request){
		
		model.addAttribute("userVO", userVO);
		if (br.hasErrors()) { // valid : false
			return "regist";
		}
		// valid : true
		try {
			userVO.setUser_lastip(request.getRemoteAddr());
			userVO.setUser_pwd(DigestUtils.md5Hex(userVO.getUser_pwd()));
			userService.registUser(userVO);
			logger.info("가입성공");
			
			String smtpHost = "mx2.naver.com";
			String fromAddr = "administrator@oraclejava.co.kr";
			String toAddr = userVO.getUser_email();
			String subject = "가입을 축하합니다.";
			String mailBody = userVO.getUser_name() + "님의 회원가입을 축하합니다.";
			
			boolean result = MailAction.sendMail(smtpHost, fromAddr, toAddr, subject, mailBody);
			
			if (result) {
				logger.info("회원가입 축하메일 발송 완료");
			} else {
				logger.info("회원가입 축하메일 발송 실패");
			}
			return "user/registAction";
		} catch (Exception e) {
			logger.info("가입실패");
			throw new RuntimeException();
		}
	}
	
	@RequestMapping(value="/zipcode", method=RequestMethod.GET)
	public void zipcode() {}
	
	@RequestMapping(value="/zipcode", method=RequestMethod.POST)
	public void zipcodeAction(String area, Model model) throws Exception {
		
		List<ZipcodeVO> list = userService.searchZipcode(area);
		
		model.addAttribute("list", list);
		model.addAttribute("area", area);
	}
	
	@RequestMapping("/logout")
	public String logout(HttpSession session, Model model) {
		String user_name= (String) session.getAttribute("user_name");
		String user_id = (String) session.getAttribute("user_id");
		String msg = user_name + "(" + user_id + ") 회원이 로그아웃 하였습니다.";
		
		session.invalidate();
		model.addAttribute("msg", msg);
		model.addAttribute("url", "login");
		return "result";		
	}
}
