package com.spring.biz.myLike;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service("mylikeService")
public class MylikeServiceImpl implements MylikeService {
	
	
	
	@Autowired
	private MylikeDAO2 MylikeDAO;

	

	@Override
	public boolean insertMylike(MylikeVO vo) {
		
		return MylikeDAO.insertMylike(vo);
	}

	@Override
	public boolean deleteMylike(MylikeVO vo) {
		return MylikeDAO.deleteMylike(vo);
	}

	@Override
	public List<MylikeVO> selectAll(MylikeVO vo) {
		
		return MylikeDAO.selectAll(vo);
	}

	@Override
	public MylikeVO selectOne(MylikeVO vo) {
		
		return MylikeDAO.selectOne(vo);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
