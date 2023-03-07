package com.spring.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;

//@Repository("boardDAO")
public class BoardDAO {
	//JDBCUtil클래스 이용
	//conn,pstmt,rs 등을 활용한다.
	private Connection conn;
	private PreparedStatement pstmt;

	private final String SQL_INSERT="INSERT INTO BOARD(TITLE,WRITER,CONTENT,IMAGE) VALUES(?,?,?,?)";
	private final String SQL_UPDATE="UPDATE BOARD SET TITLE=?,CONTENT=? WHERE BID=?";
	private final String SQL_DELETE="DELETE FROM BOARD WHERE BID=?";

	private final String SQL_SELECT_ALL="SELECT * FROM BOARD";
	private final String SQL_SELECT_ONE="SELECT * FROM BOARD WHERE BID=?";
	private final String SQL_SELECT_TITLE="SELECT * FROM BOARD WHERE TITLE LIKE CONCAT('%',?,'%') ORDER BY BID DESC";
	private final String SQL_SELECT_CONTENT="SELECT * FROM BOARD WHERE CONTENT LIKE CONCAT('%',?,'%') ORDER BY BID DESC";
	private final String SQL_SELECT_WRITER="SELECT * FROM BOARD WHERE WRITER LIKE CONCAT('%',?,'%') ORDER BY BID DESC";
	
	public boolean insertBoard(BoardVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setString(4, vo.getFileName());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true;
	}
	
	public boolean updateBoard(BoardVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setInt(3, vo.getBid());
			int res=pstmt.executeUpdate();
			if(res<=0) {
				System.out.println("   로그 : UPDATE 수행결과없음");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true;
	}
	
	public boolean deleteBoard(BoardVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SQL_DELETE);
			pstmt.setInt(1, vo.getBid());
			int res=pstmt.executeUpdate();
			if(res<=0) {
				System.out.println("   로그 : DELETE 수행결과없음");
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true;
	}

	public List<BoardVO> selectAll(BoardVO vo) {
		List<BoardVO> datas=new ArrayList<BoardVO>();
		conn=JDBCUtil.connect();
		try {
			if(vo.getSearchCondition() == null) {  //전체검색
				System.out.println("전체목록출력");
				pstmt=conn.prepareStatement(SQL_SELECT_ALL);
			}
			else if(vo.getSearchCondition().equals("TITLE")){
				System.out.println("제목목록출력");
				pstmt=conn.prepareStatement(SQL_SELECT_TITLE);
				pstmt.setString(1, vo.getSearchContent());
			}
			else if(vo.getSearchCondition().equals("WRITER")){
				System.out.println("작성자목록출력");
				pstmt=conn.prepareStatement(SQL_SELECT_WRITER);
				pstmt.setString(1, vo.getSearchContent());
			}
			else if(vo.getSearchCondition().equals("CONTENT")){
				System.out.println("내용목록출력");
				pstmt=conn.prepareStatement(SQL_SELECT_CONTENT);
				pstmt.setString(1, vo.getSearchContent());
			}
			
			ResultSet rs=pstmt.executeQuery();
			while(rs.next()) {
				System.out.println("들어왔니?");
				BoardVO data=new BoardVO();
				data.setBid(rs.getInt("BID"));
				data.setContent(rs.getString("CONTENT"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				data.setFileName(rs.getString("IMAGE"));
				datas.add(data);
				System.out.println("data:"+data);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return datas;
	}
	
	
	
	public BoardVO selectOne(BoardVO vo) {
		BoardVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SQL_SELECT_ONE);
			pstmt.setInt(1, vo.getBid());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new BoardVO();
				data.setBid(rs.getInt("BID"));
				data.setContent(rs.getString("CONTENT"));
				data.setTitle(rs.getString("TITLE"));
				data.setWriter(rs.getString("WRITER"));
				data.setFileName(rs.getString("IMAGE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return data;
	}
}
