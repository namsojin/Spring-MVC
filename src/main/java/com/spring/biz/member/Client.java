package com.spring.biz.member;

import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");

		System.out.print("ID 입력 >>   ");
		String mid=new Scanner(System.in).next();
		System.out.print("PW 입력 >>   ");
		String mpw=new Scanner(System.in).next();

		MemberService bs=(MemberService)factory.getBean("memberService");
		MemberVO vo=new MemberVO();
		vo.setMid(mid);
		vo.setMpw(mpw);
		vo=bs.selectOne(vo);
		if(vo==null) {
			System.out.println("로그인 실패!");
		}
		else {
			System.out.println(vo);
		}

		factory.close();
	}
}
