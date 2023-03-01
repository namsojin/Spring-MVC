package com.spring.biz.myLike;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


@Repository("mylikeDAO")
public class MylikeDAO2 {
	
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_INSERT="INSERT INTO MYLIKE(MID,BID) VALUES(?,?)";
	private final String SQL_DELETE="DELETE FROM MYLIKE WHERE MYNUM=?";
	private final String SQL_SELECT_ALL="SELECT * FROM MYLIKE";
	
	public boolean insertMylike(MylikeVO vo) {
		jdbcTemplate.update(SQL_INSERT,vo.getMid(),vo.getBid());
		return true;
	}
	
	public boolean deleteMylike(MylikeVO vo) {
		int res=jdbcTemplate.update(SQL_DELETE,vo.getMynum());
		if(res<1) {
			return false;
		}
		return true;
	}
	
	public List<MylikeVO> selectAll(MylikeVO vo) {
		
		return jdbcTemplate.query(SQL_SELECT_ALL, new MylikeRowMapper());
	}
}

class MylikeRowMapper implements RowMapper<MylikeVO> {

	@Override
	public MylikeVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		MylikeVO data=new MylikeVO();
		data.setBid(rs.getInt("BID"));
		data.setMid(rs.getString("MID"));
		data.setMynum(rs.getInt("MYNUM"));
		
		return data;
	}
	
}

