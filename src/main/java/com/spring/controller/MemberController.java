package com.spring.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.member.MemberDAO;
import com.spring.biz.member.MemberVO;

@Controller
public class MemberController {
	
	//같은 기능인데 get이냐 post이냐가 다를 경우에 쓰는건 method=RequestMethod.GET 이다. 
	@RequestMapping(value="/login.do", method=RequestMethod.GET) 
	public String loginView(@ModelAttribute("user")MemberVO vo) {
		//메서드 시그니쳐 String 타입으로 바꿈 -> 왜? 여기서는 ModelAndView가 필요하기않으니까.데이터를 보내지않고 "어디로갈지"만 리턴해주면 되므로 String 타입으로 리턴해준다.
		//@ModelAttribute : 뷰의 EL식과 모델의 vo,클래스명이 다를 때 컨트롤에서 연결하기위해 쓰는 어노테이션이다. 
		System.out.println("login.jsp로 이동");
		
		//command객체 : MemberVO vo 
		//컨테이너가 new했기 때문에, 컨테이너가 관리하는 페이지까지는 그 데이터를 볼 수 있다. 
		vo.setMid("test");
		vo.setMpw("1234");
		
		return "login.jsp";
	}

	@RequestMapping(value="/login.do", method=RequestMethod.POST) 
	public String selectOneMember(MemberVO vo,MemberDAO memberDAO,HttpSession session) {
		System.out.println("MemberController 입장");
		
		vo=memberDAO.selectOne(vo);
	
		if(vo==null) {
			//로그인 실패
			System.out.println("로그: 로그인 실패");
			return "redirect:login.do";
		}
		else {
			//로그인 성공
			System.out.println("로그: 로그인 성공");
			 
			session.setAttribute("member", vo);  //request에 있던 HttpSession session 만 가져오면 된다.
			return "redirect:main.do";
		}
		
	}
	

	@RequestMapping(value="/logout.do") 
	public String logoutMember(HttpSession session) {
		System.out.println("MemberController 입장");
		
		session.invalidate();  //request에 있던 HttpSession session 만 가져오면 된다. 
		
		return "main.jsp";
	}
	
	
	@RequestMapping(value="/join.do", method=RequestMethod.GET) 
	public String joinView() {
		//메서드 시그니쳐 String 타입으로 바꿈 -> 왜? 여기서는 ModelAndView가 필요하기않으니까.데이터를 보내지않고 "어디로갈지"만 리턴해주면 되므로 String 타입으로 리턴해준다.
		//@ModelAttribute : 뷰의 EL식과 모델의 vo,클래스명이 다를 때 컨트롤에서 연결하기위해 쓰는 어노테이션이다. 
		System.out.println("join.jsp로 이동");
		
		
		
		return "join.jsp";
	}
	
	@RequestMapping(value="/join.do", method=RequestMethod.POST) 
	public String insertMember(MemberVO vo,MemberDAO memberDAO) {
		System.out.println("insertMember 입장");
		
		
		
		
		if(memberDAO.insertMember(vo)) {
			//회원가입성공
			System.out.println("로그: 회원가입성공");
			return "redirect:login.do";
		}
		else {
			//회원가입실패
			System.out.println("로그: 회원가입실패");
			 
			
			return "redirect:join.jsp";
		}
		
	}
	
	
	
	
	
	
	

}
