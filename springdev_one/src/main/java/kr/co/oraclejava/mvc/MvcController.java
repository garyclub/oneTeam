package kr.co.oraclejava.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/mvc")
public class MvcController {
	private static final Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@RequestMapping("/hello")
	public ModelAndView hello() {
		return new ModelAndView("hello", "msg", "Hello @MVC");
	}
	
	@RequestMapping("/source")
	//리턴값이 없이 보이드로 하면 @RequestMapping("/mvc")의
		//RequestMapping("/source") 의 이름으로 찾아감.
	public void source() {}
	
	@RequestMapping("/target")
	public void target(String name, int age, ModelMap model) {
		//ModelMap model 또는 Model model
		model.addAttribute("iam", "그래, 난 " +age+"세 "+name+ ". 포기하지 않는 남자지.");
	}
	
	@RequestMapping("/{dong}")
	public String dong(@PathVariable String dong, Model model){
		model.addAttribute("jumin", dong + "주민입니다.");
		return "mvc/dong";
	}
	
	@RequestMapping("/target/{name}/{age}")
	public String target2(@PathVariable String name, @PathVariable int age, ModelMap model) {
		//ModelMap model 또는 Model model
		model.addAttribute("iam", "그래, 난 " +age+"세 "+name+ ". 포기하지 않는 남자지.");
		return "mvc/target";
	}
}
