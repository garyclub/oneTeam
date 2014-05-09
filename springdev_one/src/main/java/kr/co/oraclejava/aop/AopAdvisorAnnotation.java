package kr.co.oraclejava.aop;

import kr.co.oraclejava.board.UserVO;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAdvisorAnnotation {
private static final Logger logger = LoggerFactory.getLogger(AopAdvisorAnnotation.class);
	
	public void ad_before() {
		logger.info("☆☆☆☆☆☆☆☆☆☆ before advice 수행중1... ☆☆☆☆☆☆☆☆☆☆");
	}
	
	public void ad_before2() {
		logger.info("☆☆☆☆☆☆☆☆☆☆ before advice 수행중2... ☆☆☆☆☆☆☆☆☆☆");
	}
	
	@Before("execution(* kr.co.oraclejava.board.UserController.loginAction(..))")
	public void ad_before3(JoinPoint joinpoint) {
		logger.info("☆☆☆☆☆☆☆☆☆☆ before advice 수행중3... ☆☆☆☆☆☆☆☆☆☆");
		Object[] args = joinpoint.getArgs();
		
		for(Object obj:args){
			if (obj instanceof UserVO){
				UserVO userVO = (UserVO) obj;
				logger.info(userVO.toString());
			}
		}
	}
	
	public void ad_after() {
		logger.info("★★★★★★★★★★★★★★★★★★★★★");
		logger.info("★          after returning advice      ★");
		logger.info("★★★★★★★★★★★★★★★★★★★★★");
	}
	
	public Object ad_around(ProceedingJoinPoint joinpoint) {//첫시작점과 마지막시작점을 잡아서 
															//수행시간을 측정.
		logger.info("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
		logger.info("♠          around advice(before)       ♠");
		logger.info("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");

		Object obj = null;
		
		try {
			logger.info("시간 측정을 시작합니다.");
			long start = System.currentTimeMillis();
			obj = joinpoint.proceed();
			long end = System.currentTimeMillis();
			logger.info("시간 측정을 종료합니다.");
			logger.info("소요 시간 : " + ((double)end - start) / 1000 + "초");
		} catch (Throwable e) {
			logger.info(e.getMessage());
		}

		logger.info("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
		logger.info("♠          around advice(after)       ♠");
		logger.info("♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠♠");
		return obj;
	}
}
