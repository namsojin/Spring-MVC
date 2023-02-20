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

	private final String SQL_INSERT="INSERT INTO MEMBER VALUES(?,?,?,?)";
	private final String SQL_UPDATE="UPDATE MEMBER SET MPW=? WHERE MID=?";
	private final String SQL_DELETE="DELETE FROM MEMBER WHERE MID=?";

	private final String SQL_SELECT_ONE="SELECT * FROM MEMBER WHERE MID=? AND MPW=?";

	public boolean insertMember(MemberVO vo) {
		jdbcTemplate.update(SQL_INSERT,vo.getMid(),vo.getMpw(),vo.getMname(),vo.getRole());
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
		Object[] args= {vo.getMid(),vo.getMpw()};
		return jdbcTemplate.queryForObject(SQL_SELECT_ONE, args, new MemberRowMapper());
	}
}

class MemberRowMapper implements RowMapper<MemberVO> {

	@Override
	public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MemberVO data=new MemberVO();
		data.setMid(rs.getString("MID"));
		data.setMname(rs.getString("MNAME"));
		data.setMpw(rs.getString("MPW"));
		data.setRole(rs.getString("ROLE"));
		return data;
	}
	
}