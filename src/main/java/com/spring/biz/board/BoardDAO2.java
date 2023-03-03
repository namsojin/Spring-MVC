package com.spring.biz.board;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("boardDAO")
public class BoardDAO2 { // 스프링 JDBC를 활용하는 DAO
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT="INSERT INTO BOARD(TITLE,WRITER,CONTENT,IMAGE) VALUES(?,?,?,?)";
	private final String SQL_UPDATE="UPDATE BOARD SET TITLE=?,CONTENT=? WHERE BID=?";
	private final String SQL_DELETE="DELETE FROM BOARD WHERE BID=?";

	private final String SQL_SELECT_ALL="SELECT * FROM BOARD";
	private final String SQL_SELECT_ONE="SELECT * FROM BOARD WHERE BID=?";
	
	private final String SQL_SELECT_TITLE="SELECT * FROM BOARD WHERE TITLE LIKE CONCAT('%',?,'%') ORDER BY BID DESC";
	private final String SQL_SELECT_CONTENT="SELECT * FROM BOARD WHERE CONTENT LIKE CONCAT('%',?,'%') ORDER BY BID DESC";
	private final String SQL_SELECT_WRITER="SELECT * FROM BOARD WHERE WRITER LIKE CONCAT('%',?,'%') ORDER BY BID DESC";

	public boolean insertBoard(BoardVO vo) {
		System.out.println("BoardDAO2의 insert()");
		jdbcTemplate.update(SQL_INSERT, vo.getTitle(),vo.getWriter(),vo.getContent(),vo.getFileName());
		return true;
	}
	public boolean updateBoard(BoardVO vo) {
		int res=jdbcTemplate.update(SQL_UPDATE, vo.getTitle(),vo.getContent(),vo.getBid());
		if(res<1) {
			return false;
		}
		return true;
	}
	public boolean deleteBoard(BoardVO vo) {
		int res=jdbcTemplate.update(SQL_DELETE, vo.getBid());
		if(res<1) {
			return false;
		}
		return true;
	}

	public List<BoardVO> selectAll(BoardVO vo) {
		System.out.println("BoardDAO2 selectAll() 동작중");
		
		if(vo.getSearchCondition() == null) {
			System.out.println("전체목록출력");
			return jdbcTemplate.query(SQL_SELECT_ALL, new BoardRowMapper());
		}
	 if(vo.getSearchCondition().equals("TITLE")) {
			System.out.println("제목목록출력");
			System.out.println("단어:"+vo.getSearchContent());
			Object[] args= {vo.getSearchContent() };
			return jdbcTemplate.query(SQL_SELECT_TITLE,args,new BoardRowMapper());
		}
		else if(vo.getSearchCondition().equals("WRITER")) {
			System.out.println("작가목록출력");
			Object[] args= { vo.getSearchContent() };
			return jdbcTemplate.query(SQL_SELECT_WRITER,args,new BoardRowMapper());
		}
		else if(vo.getSearchCondition().equals("CONTENT")) {
			System.out.println("내용목록출력");
			Object[] args= { vo.getSearchContent() };
			return jdbcTemplate.query(SQL_SELECT_CONTENT,args,new BoardRowMapper());
		}
		else {
			System.out.println("전체목록출력");
			return jdbcTemplate.query(SQL_SELECT_ALL, new BoardRowMapper());
			
		}
			
	}
		
		
		
	
	
	
	
	
	
	public BoardVO selectOne(BoardVO vo) {
		Object[] args= { vo.getBid() };
		return jdbcTemplate.queryForObject(SQL_SELECT_ONE, args, new BoardRowMapper());
	}
}

class BoardRowMapper implements RowMapper<BoardVO> {

	@Override
	public BoardVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		BoardVO data=new BoardVO();
		data.setBid(rs.getInt("BID"));
		data.setContent(rs.getString("CONTENT"));
		data.setTitle(rs.getString("TITLE"));
		data.setWriter(rs.getString("WRITER"));
		data.setFileName(rs.getString("IMAGE"));
		return data;
	}
	
}
