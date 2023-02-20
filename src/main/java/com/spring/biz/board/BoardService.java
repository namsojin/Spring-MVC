package com.spring.biz.board;

import java.util.List;

public interface BoardService {
	public boolean insertBoard(BoardVO vo);
	public boolean updateBoard(BoardVO vo);
	public boolean deleteBoard(BoardVO vo);
	public List<BoardVO> selectAll(BoardVO vo);
	public BoardVO selectOne(BoardVO vo);
}
