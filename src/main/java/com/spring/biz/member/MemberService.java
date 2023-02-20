package com.spring.biz.member;

public interface MemberService {
	public boolean insertMember(MemberVO vo);
	public boolean updateMember(MemberVO vo);
	public boolean deleteMember(MemberVO vo);
	public MemberVO selectOne(MemberVO vo);
}
