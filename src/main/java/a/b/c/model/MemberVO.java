package a.b.c.model;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
