package kr.board.entity;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import lombok.Data;

//인증후 사용자 정보를 저장하기 - ③ (UserDetails)

@Data
public class MemberUser extends User { // UserDetails

	private Member member;
	
	public MemberUser(String username, String password, 
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		
	}
	
	public MemberUser(Member mvo) {
		// User클래스에 생성자를 호출하는 코드 작성
		super(mvo.getMemID(), mvo.getMemPassword(), 
				mvo.getAuthList().stream()
				.map(auth -> new SimpleGrantedAuthority(auth.getAuth()))
				.collect(Collectors.toList()));
		System.out.println("User 생성자 호출");
		this.member = mvo;
		// List<AuthVO> -> Collection<SimpleGrantedAuthority>
	}

}
