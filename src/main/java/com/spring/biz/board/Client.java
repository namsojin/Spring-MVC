package com.spring.biz.board;

import java.util.Scanner;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Client {
	public static void main(String[] args) {
		AbstractApplicationContext factory=new GenericXmlApplicationContext("applicationContext.xml");
		
		System.out.print("작성자의 이름을 입력하세요. >>   ");
		String name=new Scanner(System.in).next();
		
		BoardService bs=(BoardService)factory.getBean("boardService");
		BoardVO vo=new BoardVO();
		vo.setTitle("제목");
		vo.setWriter(name);
		vo.setContent("내용");
		if(bs.insertBoard(vo)) {
			System.out.println("insert 잘됨");
		}
		else {
			System.out.println("insert 잘안됨");
		}
		
		System.out.print("변경할 글 번호를 입력하세요. >>   ");
		int bid=new Scanner(System.in).nextInt();
		vo.setBid(bid);
		vo.setTitle("변경된 제목");
		vo.setContent("변경된 내용");
		if(bs.updateBoard(vo)) {
			System.out.println("update 잘됨");
		}
		else {
			System.out.println("update 잘안됨");
		}
		
		System.out.print("삭제할 글 번호를 입력하세요. >>   ");
		bid=new Scanner(System.in).nextInt();
		vo.setBid(bid);
		if(bs.deleteBoard(vo)) {
			System.out.println("delete 잘됨");
		}
		else {
			System.out.println("delete 잘안됨");
		}
		
		/*
		System.out.print("출력할 글 번호를 입력하세요. >>   ");
		bid=new Scanner(System.in).nextInt();
		vo.setBid(bid);
		System.out.println(bs.selectOne(vo));
		*/
		
		for(BoardVO v:bs.selectAll(vo)) {
			System.out.println(v);
		}
		
		factory.close();
	}
}
