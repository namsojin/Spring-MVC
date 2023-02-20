package com.spring.biz.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDAO2 boardDAO;

	@Override
	public boolean insertBoard(BoardVO vo) {
		/*
		if(vo.getWriter().equals("timo")) {
			throw new IllegalArgumentException("일부러 발생시킨 예외");
		}
		*/
		return boardDAO.insertBoard(vo);
	}

	@Override
	public boolean updateBoard(BoardVO vo) {
		return boardDAO.updateBoard(vo);
	}

	@Override
	public boolean deleteBoard(BoardVO vo) {
		return boardDAO.deleteBoard(vo);
	}

	@Override
	public List<BoardVO> selectAll(BoardVO vo) {
		return boardDAO.selectAll(vo);
	}

	@Override
	public BoardVO selectOne(BoardVO vo) {
		return boardDAO.selectOne(vo);
	}

}
