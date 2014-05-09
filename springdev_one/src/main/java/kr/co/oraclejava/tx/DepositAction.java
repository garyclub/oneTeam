package kr.co.oraclejava.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/tx")
public class DepositAction {
	@Autowired
	private DepositService service;
	
	@RequestMapping("/deposit")
	public ModelAndView deposit() throws Exception {
		try {
			service.deposit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		int cash = service.getCash();
		int bank = service.getBank();
		
		ModelAndView mav = new ModelAndView();
		
		mav.setViewName("tx/deposit");
		mav.addObject("cash", cash);
		mav.addObject("bank", bank);
		return mav;
	}

	
}
