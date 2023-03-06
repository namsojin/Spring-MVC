package com.spring.biz.member;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("memberDAO")
public class MemberDAO2 {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_INSERT="INSERT INTO MEMBER(MID,MPW,MNAME,ROLE) VALUES(?,?,?,?)";
	private final String SQL_UPDATE="UPDATE MEMBER SET MPW=? WHERE MID=?";
	private final String SQL_DELETE="DELETE FROM MEMBER WHERE MID=?";

	private final String SQL_SELECT_ONE="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";
	private final String SQL_SELECT_KAKAO="SELECT * FROM MEMBER WHERE MID=?";

	public boolean insertMember(MemberVO vo) {
		jdbcTemplate.update(SQL_INSERT,vo.getMid(),vo.getMpw(),vo.getMname(),"MEMBER");
		return true;
	}
	public boolean updateMember(MemberVO vo) {
		int res=jdbcTemplate.update(SQL_UPDATE,vo.getMpw(),vo.getMid());
		if(res<1) {
			return false;
		}
		return true;
	}
	public boolean deleteMember(MemberVO vo) {
		int res=jdbcTemplate.update(SQL_DELETE,vo.getMid());
		if(res<1) {
			return false;
		}
		return true;
	}

	public MemberVO selectOne(MemberVO vo) {
		System.out.println("MemberDAO2의 selectOne() 동작중");
		if(vo.getMpw().equals("카카오미입력")) {
			System.out.println("카카오로그인중");
			Object[] args= {vo.getMid()};
			try {
			return jdbcTemplate.queryForObject(SQL_SELECT_KAKAO, args, new MemberRowMapper());
			}catch(Exception e) {
				return null;
			}
		}
		else {
			System.out.println("그냥로그인중");
		Object[] args= {vo.getMid(),vo.getMpw()};
		try {
		return jdbcTemplate.queryForObject(SQL_SELECT_ONE, args, new MemberRowMapper());
		}catch(Exception e) {
			return null;
		}
		}
	}
}

class MemberRowMapper implements RowMapper<MemberVO> {

	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		System.out.println("맴퍼 들어왔니");
		MemberVO data=new MemberVO();
		data.setMid(rs.getString("MID"));
		data.setMname(rs.getString("MNAME"));
		data.setMpw(rs.getString("MPW"));
		data.setRole(rs.getString("ROLE"));
		System.out.println("data:"+data);
		return data;
	}
	
}