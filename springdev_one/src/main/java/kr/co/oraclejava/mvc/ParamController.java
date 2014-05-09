package kr.co.oraclejava.mvc;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/param")
public class ParamController {
	private static final Logger logger = LoggerFactory.getLogger(ParamController.class);
	@RequestMapping(value = "/insert", method=RequestMethod.GET)
	public void insert(){
		
	}
	
	@RequestMapping(value = "/insert", method=RequestMethod.POST)
	public ModelAndView insertAction(@Valid MemoVO memoVO, BindingResult br){
		ModelAndView mav = new ModelAndView();
		mav.addObject("memoVO", memoVO);
		
		//하나라도 에러가 잡히면 true
		if (br.hasErrors()){
			mav.setViewName("param/insert");
			return mav;
		} else { //정상 처리된 경우
			mav.setViewName("param/insertAction");
			return mav;
		}
	}
}
