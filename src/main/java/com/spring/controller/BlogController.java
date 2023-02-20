package com.spring.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;  //@Controller 달면 생김
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.spring.biz.board.BoardDAO;
import com.spring.biz.board.BoardVO;

//@Component -->일반 new / 하위에 밑의 세 개의 어노테이션을 상속한다.
//@Controller  --> controller 타입의 객체를 new
//@Service
//@Repository

@Controller  //컨트롤러이다. new <bean> + implements 
public class BlogController {
	//핸들러맵핑은 객체를 보고, 리퀘스트 맵핑은 메서드를 본다. =>리퀘스트 맵핑는 메서드들 합치는거 가능해짐. => 응집도가 높아진 것. 
	 
	//검색시 조건들 전달하기위한 메서드
	//1.메서드 호출 순서 
	// : @RequestMapping 보다 먼저 호출된다. 
	//2.싱글톤 유지 여부 
	// : (별도 설정이 없다면)스프링 컨테이너가 유지해줍니다.
	@ModelAttribute("searchMap")
	public Map<String,String> searchConditionMap(){
		
		Map<String,String> map =new HashMap<String,String>();
		map.put("제목", "TITLE");  //대문자인 이유: 향후 DB랑 연결될거니까.
		map.put("작성자", "WRITER");
		map.put("내용", "CONTENT");
		
		return map;
		
		
	}
	
	
	//이 요청(/main.do)에 대해 여기로 와 
	@RequestMapping(value="/main.do") 
	public String selectAllBoard(BoardVO vo, BoardDAO boardDAO,Model model){
		System.out.println("selectAllBoard 수행");
		//System.out.println("searchcondition: "+vo.getSearchCondition());
		//System.out.println("searchcontent: "+vo.getSearchContent());
		System.out.println("로그 list:"+boardDAO.selectAll(vo));
		model.addAttribute("datas", boardDAO.selectAll(vo));
		 
		return "main.jsp";
	}
	
	@RequestMapping(value="/blog.do")
	public String selcetOneBoard(BoardVO vo,BoardDAO boardDAO,HttpSession session){ 
		//HttpServletRequest request : 사용자의 입력값이 request에 저장되어있음.
	    //-> 사용자에게 입력값 받아와서 BoardVO에 담을 것. 그걸 vo라 부를 것. == Command 객체 
		//Command 객체를 사용하면 자동으로 이루어지는 것: 1) new 2) request로부터 추출(사용자 입력값 추출) 3) 해당 객체(vo)에 자동으로 set 매핑해서 저장. <<-스프링컨테이너가 해줌.
		System.out.println("selcetOneBoard 수행");
		session.setAttribute("data", boardDAO.selectOne(vo));
		//model.addAttribute("data", boardDAO.selectOne(vo));
		
		return "blog.jsp";
	}
	
	@RequestMapping(value="/write.do")
	public String insertBoard(BoardVO vo,BoardDAO boardDAO){ 
		System.out.println("insertBoard 수행");
		if(boardDAO.insertBoard(vo)) { //글추가 성공
			return "redirect:main.do";
		}
		else { //글추가 실패
			return "main.jsp";
		}		
	}
	

	 /*	
		//vo에 멤버변수에 있지 않은 값을 가져올때는 @RequestParam 를 사용한다.
		@RequestMapping(value="/search.do") 
		public String selectAllBoard(@RequestParam(value="searchCondition",defaultValue="TITLE",required=false)String searchCondition,@RequestParam(value="searchContent",defaultValue="",required=false)String searchContent,BoardVO vo,BoardDAO boardDAO,Model model){
			System.out.println("SearchController 입장");
			System.out.println("searchcondition: "+vo.getSearchCondition);
			System.out.println("searchcontent: "+vo.getSearchContent);
			
			model.addAttribute("datas", boardDAO.selectAll(vo));
			 
			return "main.jsp";
		}
	   */
	
	
	@RequestMapping(value="/update.do", method=RequestMethod.GET) 
	public String updateView() {
		
		System.out.println("updateBlog.jsp로 이동");
		
		//model.addAttribute("data", boardDAO.selectOne(vo));

		return "updateBlog.jsp";
	}

	
	
	
	@RequestMapping(value="/update.do", method=RequestMethod.POST)
	public String updateBoard(BoardVO vo,BoardDAO boardDAO){ 
		System.out.println("updateBoard 입장");
		
		if(boardDAO.updateBoard(vo)) {
			//글수정 성공
			return "redirect:main.do";
		}
		else {
			//글수정 실패
			return "main.jsp";
		}		
	}
	
	@RequestMapping(value="/delete.do")
	public String deleteBoard(BoardVO vo,BoardDAO boardDAO){ 
		System.out.println("deleteBoard 입장");
		
		if(boardDAO.deleteBoard(vo)) {
			//글수정 성공
			return "redirect:main.do";
		}
		else {
			//글추가 실패
			return "main.jsp";
		}		
	}
	
	
	
	
	
	
	
	
	
	
}

//command 객체를 사용하므로 
//코드가 간단해졌다.
//request, response가 없어졌다 => pojo 가 됨. 가벼워진다.
//new가 없으니 결합도가 낮아졌다.
