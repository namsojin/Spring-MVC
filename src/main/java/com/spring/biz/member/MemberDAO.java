package com.spring.biz.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.spring.biz.common.JDBCUtil;

//@Repository("memberDAO")
public class MemberDAO {
	private Connection conn;
	private PreparedStatement pstmt;

	private final String SQL_INSERT="INSERT INTO MEMBER(MID,MPW,MNAME) VALUES(?,?,?)";
	private final String SQL_UPDATE="UPDATE MEMBER SET MPW=? WHERE MID=?";
	private final String SQL_DELETE="DELETE FROM MEMBER WHERE MID=?";

	private final String SQL_SELECT_ONE="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";

	public boolean insertMember(MemberVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SQL_INSERT);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			pstmt.setString(3, vo.getMname());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return true;
	}
	public boolean updateMember(MemberVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SQL_UPDATE);
			pstmt.setString(1, vo.getMpw());
			pstmt.setString(2, vo.getMid());
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
	public boolean deleteMember(MemberVO vo) {
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SQL_DELETE);
			pstmt.setString(1, vo.getMid());
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

	public MemberVO selectOne(MemberVO vo) {
		MemberVO data=null;
		conn=JDBCUtil.connect();
		try {
			pstmt=conn.prepareStatement(SQL_SELECT_ONE);
			pstmt.setString(1, vo.getMid());
			pstmt.setString(2, vo.getMpw());
			ResultSet rs=pstmt.executeQuery();
			if(rs.next()) {
				data=new MemberVO();
				data.setMid(rs.getString("MID"));
				data.setMname(rs.getString("MNAME"));
				data.setMpw(rs.getString("MPW"));
				data.setRole(rs.getString("ROLE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.disconnect(conn, pstmt);
		}
		return data;
	}
}
