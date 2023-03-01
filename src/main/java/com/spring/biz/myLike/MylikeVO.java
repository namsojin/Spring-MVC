package com.spring.biz.myLike;

public class MylikeVO {
	
	private int mynum;
	private String mid;
	private int bid;
	
	
	public int getMynum() {
		return mynum;
	}
	public void setMynum(int mynum) {
		this.mynum = mynum;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	
	
	@Override
	public String toString() {
		return "MylikeVO [mynum=" + mynum + ", mid=" + mid + ", bid=" + bid + "]";
	}
	

	

}
