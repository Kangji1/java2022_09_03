package service;


import java.util.List;

import domain.MemberVO;

public interface MemberService {

	MemberVO login(MemberVO mvo);

	int register(MemberVO mvo);

	int lastLogin(String email);

	List<MemberVO> getList();

	int modify(MemberVO mvo);

	int remove(String email);


}
