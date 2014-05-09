package kr.co.oraclejava.jdbc;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/jdbc")
public class EmpDeptController {

	@Autowired
	@Qualifier("ibatis")
	private EmpDeptDAO dao;
	
	private static final Logger logger = LoggerFactory.getLogger(EmpDeptController.class);
	
	@RequestMapping(value = "/empList", method=RequestMethod.GET)
	public ModelAndView empList() {
		int totalSal = dao.getTotalSal();
		List<EmpVO> empList = dao.getEmpList();
		
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("empList", empList);
		mav.addObject("totalSal", totalSal);
		return mav;
	}

	@RequestMapping(value = "/empInsert", method=RequestMethod.GET)
	public ModelAndView empInsert(){
		List<DeptVO> deptList = dao.getDeptList();
		ModelAndView mav = new ModelAndView();
		mav.addObject("deptList", deptList);
		return mav;
	}
	
	@RequestMapping(value = "/empInsert", method=RequestMethod.POST)
	public String empInsertAction(EmpVO vo, Model model){
		
		try {
			dao.empInsert(vo);
			return "redirect:empList";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "입력 실패");
			model.addAttribute("url", "javascript:history.back();");
			return "jdbc/result";
		}
		
	}
	
	@RequestMapping(value = "/{empno}", method=RequestMethod.GET)
	public String empUpdate(@PathVariable int empno, Model model) {
		EmpVO vo = dao.getEmp(empno);
		List<DeptVO> depList = dao.getDeptList();
		model.addAttribute("empVO", vo);
		model.addAttribute("deptList", depList);
		return "jdbc/empUpdate";
	}
	
	@RequestMapping(value = "/empUpdate", method=RequestMethod.POST)
	public String empUpdateAction(EmpVO vo, Model model) {
		try {
			dao.updateEmp(vo);
			return "redirect:empList";
		} catch (RuntimeException e) {
			model.addAttribute("msg", e.getMessage().replace("\n", " "));
			//에러메세지가 엔터가 들어가거나 하면 에러가나고 alert 창에 뿌려지지
			//않을 수 있으니 잘라내거나 엔터를 공백으로 바꾸어 출력하게 한다.
			model.addAttribute("url", "javascript:history.back();");
			return "jdbc/result";
		}
	}
	
	@RequestMapping(value = "/{empno}/delete", method=RequestMethod.GET)
	public String empDelete(@PathVariable int empno, Model model) {
		try {
			dao.deleteEmp(empno);
			return "redirect:/jdbc/empList";
		} catch (RuntimeException e) {
			model.addAttribute("msg", e.getMessage());
			model.addAttribute("url", "javascript:history.back();");
			return "jdbc/result";
		}
	}
}
