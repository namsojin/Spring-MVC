package com.spring.controller;
//처리할 로직과 같은 레벨에 있어야하니까, 컨트롤러 패키지에 넣어준다.
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

//에러 발생시 -> 사용자에게 보여주는 페이지

//"어떤 입셉션에 대해 어떤 페이지로 가줘" 해줄 예정 -> 에러에 대한 페이지 관리
@ControllerAdvice("com.spring.controller")
public class CommonExceptionHandler {
	
	//입셉션 종류에 따라 보여주는 페이지가 다름
	@ExceptionHandler(ArithmeticException.class)
	public ModelAndView handleArithmeticException(Exception e) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/error/error01.jsp");
		
		return mav;
		
		
		
	} 
	
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView handleNullPointerException(Exception e) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/error/error02.jsp");
		
		return mav;
		
		
		
	} 
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleException(Exception e) {
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", e);
		mav.setViewName("/error/error03.jsp");
		
		return mav;
		
		
		
	} 
	
	
	
	
	
	
	
	
	
	
	
	

}
