package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	MemberVO selectOne(MemberVO mvo);

	int insert(MemberVO mvo);

	int update(String email);

	List<MemberVO> select();

	int update2(MemberVO mvo);

	int delete(String email);

}
