package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import domain.MemberVO;
import service.MemberService;
import service.MemberServiceImpl;


@WebServlet("/mem/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(MemberController.class);
	private RequestDispatcher rdp;
	private String destPage;
	private int isOk;
	
	private MemberService msv;
	
       

    public MemberController() {
    	msv = new MemberServiceImpl();
    }

	
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setCharacterEncoding("UTF-8");
		req.setCharacterEncoding("UTF-8");
		res.setContentType("text/html; charset=UTF-8");
		
		String uri = req.getRequestURI(); // /mem/list
		String path = uri.substring(uri.lastIndexOf("/")+1);
		
		log.info("URI : "+uri);
		log.info("PATH : "+path);
		switch (path) {
		case "login": //Login Page Open
			destPage = "/member/login.jsp";
			break;
		case "login_auth": //실제 로그인이 일어나는 케이스
			try {
				MemberVO mvo = msv.login(new MemberVO(
								req.getParameter("email"),
								req.getParameter("password")
								));
				log.info("login 객체 input");
				if(mvo != null) {
					//세션 가져오기, 연결된 세션이 없다면 새로 생성
					HttpSession ses = req.getSession();
					//ses로 mvo를 바인딩
					ses.setAttribute("ses", mvo);
					ses.setMaxInactiveInterval(10*60); //로그인 유지 시간(10분)
				}else {
					req.setAttribute("msg_login", 0);
				}
				destPage = "/";
			} catch (Exception e) {
				
			}
			break;
		case "join": //join page open
			destPage = "/member/join.jsp";
			break;
		case "register" : //회원가입 DB저장
			isOk = msv.register(new MemberVO(
					req.getParameter("email"),
					req.getParameter("password"),
					req.getParameter("nick_name")));
			log.info("join "+(isOk > 0 ? "ok" : "fail"));
			destPage="login";
			break;
		case "logout":
			//연결된 세션이 있다면 해당 세션을 가져오기
			try {
				HttpSession ses = req.getSession();
				MemberVO mvo = (MemberVO)ses.getAttribute("ses");
				String email = mvo.getEmail();
				log.info(email);
				ses.invalidate(); //세션 끊기
				//로그인정보 email을 주고 로그인 시간 업데이트
				isOk=msv.lastLogin(email);
				log.info("lastLogin "+(isOk > 0 ? "ok" : "fail"));
				destPage="/";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "list":
			List <MemberVO> list = msv.getList();
			req.setAttribute("list", list);
			destPage="/member/list.jsp";
			break;
		case "modify":
			destPage="/member/modify.jsp";
			break;
		case "update":
			isOk = msv.modify(new MemberVO(
					req.getParameter("email"),
					req.getParameter("password"),
					req.getParameter("nick_name")
					));
			destPage="list";
			break;
		case "remove":
			try {
				HttpSession ses = req.getSession(); 
				ses.invalidate(); 
				isOk = msv.remove(req.getParameter("email"));
				log.info("lastLogin "+(isOk > 0 ? "ok" : "fail"));
				destPage="list";
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		
		}
		//목적지 주소 값 세팅
		rdp=req.getRequestDispatcher(destPage);
		//정보 싣고 보내기
		rdp.forward(req, res);
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
	
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}

}
