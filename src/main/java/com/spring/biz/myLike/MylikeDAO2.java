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
	private final String SQL_SELECT_ALL="SELECT * FROM MYLIKE WHERE MID=?;";
	private final String SQL_SELECT_ONE="SELECT * FROM MYLIKE WHERE BID=? AND MID=? ";
	
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
		Object[] args= {vo.getMid()};
		return jdbcTemplate.query(SQL_SELECT_ALL,args, new MylikeRowMapper());
	}
	
	public MylikeVO selectOne(MylikeVO vo) {
		System.out.println("mylike selectOneDAO수행중");
		System.out.println(vo.getBid());
		System.out.println(vo.getMid());
		Object[] args= {vo.getBid(),vo.getMid()};
		try {
			return jdbcTemplate.queryForObject(SQL_SELECT_ONE, args, new MylikeRowMapper());
		}catch(Exception e) {
			
			return null;
		}
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

