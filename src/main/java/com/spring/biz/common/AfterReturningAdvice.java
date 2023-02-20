package com.spring.biz.common;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Service;

import com.spring.biz.board.BoardVO;
import com.spring.biz.member.MemberVO;

@Service
@Aspect
public class AfterReturningAdvice {
	@AfterReturning(pointcut="PointcutCommon.bPointcut()", returning="obj")
	public void printLogARA(JoinPoint jp, Object obj) {
		 System.out.println("[ARA 로그] 비즈니스 메서드 수행 이후에 호출되는 로그");
		
			if(obj instanceof BoardVO) {
				System.out.println("[ 글을 조회했습니다 ]");
			}
			else if(obj instanceof MemberVO) {
				MemberVO mvo = (MemberVO)obj;
				System.out.println(mvo.getRole());
				if(mvo.getRole().equals("ADMIN")) {
					System.out.println("[[ 관리자 모드 수행함 ]]");
				}
				System.out.println("[ 회원을 조회했습니다 ]");
					
			}
			else {
				System.out.println("[ 전체 조회했습니다 ]");
			}
		 
		 
		 
		 
	//	System.out.println(jp.getSignature().getName()+"()");
		//System.out.println(jp.getArgs()[0]);
		
	//	System.out.println("결과값은 "+obj);
	}
}
