package com.spring.controller;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;  //@Controller 달면 생김
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.spring.biz.board.BoardService;
import com.spring.biz.board.BoardVO;
import com.spring.biz.member.MemberVO;
import com.spring.biz.myLike.MylikeService;
import com.spring.biz.myLike.MylikeVO;

//@Component -->일반 new / 하위에 밑의 세 개의 어노테이션을 상속한다.
//@Controller  --> controller 타입의 객체를 new
//@Service
//@Repository

@Controller  //컨트롤러이다. new <bean> + implements 
public class BlogController {
	//핸들러맵핑은 객체를 보고, 리퀘스트 맵핑은 메서드를 본다. =>리퀘스트 맵핑는 메서드들 합치는거 가능해짐. => 응집도가 높아진 것. 
	 
	@Autowired  //DI주입 //메모리에 있는 자료형을 보고 주입한다.->MemberService타입이 있는지 확인 how? @Service("memberService") 있는지 확인함.
	private BoardService boardService;
	
	@Autowired  //DI주입 //메모리에 있는 자료형을 보고 주입한다.->MemberService타입이 있는지 확인 how? @Service("memberService") 있는지 확인함.
	private MylikeService mylikeService;
	
	
	
	
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
	public String selectAllBoard(BoardVO vo, Model model, MylikeVO myvo){
		System.out.println("selectAllBoard 수행");
		//System.out.println("searchcondition: "+vo.getSearchCondition());
		//System.out.println("searchcontent: "+vo.getSearchContent());
		System.out.println("로그 list:"+boardService.selectAll(vo));
		model.addAttribute("datas", boardService.selectAll(vo));
		
		 
		return "main.jsp";
	}
	
	@RequestMapping(value="/blog.do")
	public String selcetOneBoard(BoardVO vo,HttpSession session,MylikeVO myvo,Model model){ 
		//HttpServletRequest request : 사용자의 입력값이 request에 저장되어있음.
	    //-> 사용자에게 입력값 받아와서 BoardVO에 담을 것. 그걸 vo라 부를 것. == Command 객체 
		//Command 객체를 사용하면 자동으로 이루어지는 것: 1) new 2) request로부터 추출(사용자 입력값 추출) 3) 해당 객체(vo)에 자동으로 set 매핑해서 저장. <<-스프링컨테이너가 해줌.
		System.out.println("selcetOneBoard 수행");
		session.setAttribute("data", boardService.selectOne(vo));
		//model.addAttribute("data", boardDAO.selectOne(vo));
		myvo.setBid(vo.getBid());
		MemberVO member = (MemberVO)session.getAttribute("member");
		myvo.setMid(member.getMid());
		
		model.addAttribute("isMylike",mylikeService.selectOne(myvo) );
		System.out.println("isMylike:"+mylikeService.selectOne(myvo));
		
		
		return "blog.jsp";
	}
	
	@RequestMapping(value="/write.do")
	public String insertBoard(BoardVO vo){  //BoardVO vo에 파일입출력관련 멤버변수 만들어줘야한다.
		System.out.println("insertBoard 수행");
		
		//이미지 업로드
		MultipartFile uploadFile = vo.getUploadFile();  
		if(!uploadFile.isEmpty()) { //이미지 업로드 되었다면,
			System.out.println("이미지 업로드 확인");
			String fileName = uploadFile.getOriginalFilename(); //파일이름
			System.out.println("파일이름:"+fileName );
			vo.setFileName(fileName);
			
			//사용자의 로컬PC에 저장된 이미지를 업로드한 것이기 때문에, 
			//서버(해당 프로젝트)에서 해당 이미지를 별도 관리하기위해 데이터를 복사해서 저장해야한다. 
			try {
			uploadFile.transferTo(new File("D:\\NAM1005\\workspace2\\day71\\src\\main\\webapp\\images\\"+fileName));
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		else{
			try {
				vo.setFileName("default.jpg");
				}catch(Exception e) {
					e.printStackTrace();
				}
			
		}
		
		if(boardService.insertBoard(vo)) { //글추가 성공
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
	public String updateBoard(BoardVO vo){ 
		System.out.println("updateBoard 입장");
		
		if(boardService.updateBoard(vo)) {
			//글수정 성공
			return "redirect:main.do";
		}
		else {
			//글수정 실패
			return "main.jsp";
		}		
	}
	
	@RequestMapping(value="/delete.do")
	public String deleteBoard(BoardVO vo){ 
		System.out.println("deleteBoard 입장");
		
		if(boardService.deleteBoard(vo)) {
			//글수정 성공
			return "redirect:main.do";
		}
		else {
			//글추가 실패
			return "main.jsp";
		}		
	}
	
	@ResponseBody
	@RequestMapping(value="/heart.do",method=RequestMethod.POST, produces="application/json; charset=utf-8")
	public String heartBoard(MylikeVO vo){ 
		System.out.println("heartBoard 입장");
		    
		  if(mylikeService.insertMylike(vo)) {
			  return "success";
			  
		  }
		  else {
			  return "fail";
			  
		  }
			
				
	}
	
	
	
	
	
	
	
	
	
	
}

//command 객체를 사용하므로 
//코드가 간단해졌다.
//request, response가 없어졌다 => pojo 가 됨. 가벼워진다.
//new가 없으니 결합도가 낮아졌다.
