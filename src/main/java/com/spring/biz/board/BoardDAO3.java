package com.spring.biz.board;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository("boardDAO")
public class BoardDAO3 { //Mybatis 프레임워크로 바꿔줘 : 새로운 DAO를 만드는 것이다.(기존꺼에 수정X) 
	//Mabatis 프레임워크를 이용.
	//-> 해당 프레임워크에서 제공해주는 객체를 사용해야 한다.
	//SqlSessionTemplate mybatis을 활용한다.
	
	@Autowired
	private SqlSessionTemplate mybatis;
	
	public boolean insertBoard(BoardVO vo) {
		System.out.println("BoardDAO3의 insert()");
		//"namespace.id" 한 것 ↓
		mybatis.insert("BoardDAO.insertBoard",vo);
		return true;
	}
	
	public boolean updateBoard(BoardVO vo) {
		
		int res=mybatis.update("BoardDAO.updateBoard",vo);
		if(res<1) {
			return false;
		}
		return true;
	}
	
	
	public boolean deleteBoard(BoardVO vo) {
		int res=mybatis.delete("BoardDAO.deleteBoard",vo);
		if(res<1) {
			return false;
		}
		return true;
	}

	public List<BoardVO> selectAll(BoardVO vo) {
		System.out.println("BoardDAO3 selectAll() 동작중");
		return mybatis.selectList("BoardDAO.selectBoardList",vo);
		
	}

	public BoardVO selectOne(BoardVO vo) {
		System.out.println("BoardDAO3 selectOne() 동작중");
		return mybatis.selectOne("BoardDAO.selectBoard", vo);
	}


}
