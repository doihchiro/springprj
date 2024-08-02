package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;
import kr.board.entity.Member;

@Mapper
public interface MemberMapper {
	Member registerCheck(String memID);
	int register(Member member); // 회원등록(성공1, 실패0)
	Member memLogin(Member member);
	int memUpdate(Member member);// 로그인 체크
	Member getMember(String memID);
	void memProfileUpdate(Member member);
}
