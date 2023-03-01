package com.spring.biz.myLike;

import java.util.List;

public interface MylikeService {

	public boolean insertMylike(MylikeVO vo);
	
	public boolean deleteMylike(MylikeVO vo);
	public List<MylikeVO> selectAll(MylikeVO vo);
	
}
