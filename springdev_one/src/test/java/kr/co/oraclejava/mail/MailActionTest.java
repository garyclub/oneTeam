package kr.co.oraclejava.mail;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"file:src/test/**/*-context.xml"})
public class MailActionTest {

//	@Autowired
//	private MailVO mailVO;
	@Test
	public void testSendMail() {
		String smtpHost = "mx2.naver.com";
		String fromAddr = "administrator@oraclejava.co.kr";
		String toAddr = "garyclub@naver.com";
		String subject = "가입을 축하합니다.";
		String mailBody = "노경국 님의 회원가입을 축하합니다.";		
		assertTrue(MailAction.sendMail(smtpHost, fromAddr, toAddr, subject, mailBody));
//		assertTrue(MailAction.sendMail(
//				mailVO.getSmtpHost(), 
//				mailVO.getFromAddr(), 
//				mailVO.getToAddr(), 
//				mailVO.getSubject(), 
//				mailVO.getMailBody()));
	
	}

}
