package a.b.c.model;

import java.util.Date;

public class MemberVO {

	private Long mem_num; // 회원 번호(PK)
	private String mem_name; // 회원 이름
	private String mem_id; // 회원 아이디
	private String mem_pass; // 회원 비밀번호
	private Date mem_regdate; // 회원 등록 날짜
	private String mem_email; // 회원 이메일
	private String mem_pic; // 회원 프로필 사진
	private String authkey; // 이메일 인증을 위한 인증키
	private String authstatus; // 이메일 인증을 위한 인증 상태

	public MemberVO() {}

	public MemberVO(Long mem_num, String mem_name, String mem_id, String mem_pass, Date mem_regdate, String mem_email,
			String mem_pic, String authkey, String authstatus) {
		this.mem_num = mem_num;
		this.mem_name = mem_name;
		this.mem_id = mem_id;
		this.mem_pass = mem_pass;
		this.mem_regdate = mem_regdate;
		this.mem_email = mem_email;
		this.mem_pic = mem_pic;
		this.authkey = authkey;
		this.authstatus = authstatus;
	}

	public Long getMem_num() {
		return mem_num;
	}

	public void setMem_num(Long mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_name() {
		return mem_name;
	}

	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}

	public String getMem_pass() {
		return mem_pass;
	}

	public void setMem_pass(String mem_pass) {
		this.mem_pass = mem_pass;
	}

	public Date getMem_regdate() {
		return mem_regdate;
	}

	public void setMem_regdate(Date mem_regdate) {
		this.mem_regdate = mem_regdate;
	}

	public String getMem_email() {
		return mem_email;
	}

	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}

	public String getMem_pic() {
		return mem_pic;
	}

	public void setMem_pic(String mem_pic) {
		this.mem_pic = mem_pic;
	}

	public String getAuthkey() {
		return authkey;
	}

	public void setAuthkey(String authkey) {
		this.authkey = authkey;
	}

	public String getAuthstatus() {
		return authstatus;
	}

	public void setAuthstatus(String authstatus) {
		this.authstatus = authstatus;
	}
	
}
