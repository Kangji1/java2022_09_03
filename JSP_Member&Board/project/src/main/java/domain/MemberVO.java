package domain;

public class MemberVO {


	private String email;
	private String password;
	private String nick_name;
	private String reg_at;
	private String last_login;
	
	public MemberVO() {}
	
	//login(email, pwd)
	public MemberVO(String email, String password) {
		this.email = email;
		this.password = password;
	}

	//join, modify(email, pwd, nick_name)
	public MemberVO(String email, String password, String nick_name) {
		this.email = email;
		this.password = password;
		this.nick_name = nick_name;
	}
	
	//list(all)
	public MemberVO(String email, String password, String nick_name, String reg_at, String last_login) {
		this.email = email;
		this.password = password;
		this.nick_name = nick_name;
		this.reg_at = reg_at;
		this.last_login = last_login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNick_name() {
		return nick_name;
	}

	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}

	public String getReg_at() {
		return reg_at;
	}

	public void setReg_at(String reg_at) {
		this.reg_at = reg_at;
	}

	public String getLast_login() {
		return last_login;
	}

	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	

}
