package service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import controller.MemberController;
import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImpl;

public class MemberServiceImpl implements MemberService {
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImpl();
	}

	@Override
	public MemberVO login(MemberVO mvo) {
		log.info("login check 2");
		return mdao.selectOne(mvo);
	}

	@Override
	public int register(MemberVO mvo) {
		log.info("join check 2");
		return mdao.insert(mvo);
	}

	@Override
	public int lastLogin(String email) {
		log.info("lastLogin check 2");
		return mdao.update(email);
	}

	@Override
	public List<MemberVO> getList() {
		log.info("list check 2");
		return mdao.select();
	}

	@Override
	public int modify(MemberVO mvo) {
		log.info("modify check 2");
		return mdao.update2(mvo);
	}

	@Override
	public int remove(String email) {
		log.info("remove check 2");
		return mdao.delete(email);
	}
}
