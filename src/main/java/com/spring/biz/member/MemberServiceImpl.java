package com.spring.biz.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberDAO MemberDAO;

	@Override
	public boolean insertMember(MemberVO vo) {
		return MemberDAO.insertMember(vo);
	}

	@Override
	public boolean updateMember(MemberVO vo) {
		return MemberDAO.updateMember(vo);
	}

	@Override
	public boolean deleteMember(MemberVO vo) {
		return MemberDAO.deleteMember(vo);
	}

	@Override
	public MemberVO selectOne(MemberVO vo) {
		return MemberDAO.selectOne(vo);
	}

}
