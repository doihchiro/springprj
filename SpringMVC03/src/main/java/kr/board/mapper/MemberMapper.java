package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;
import kr.board.entity.Member;

@Mapper
public interface MemberMapper {
	Member registerCheck(String memID);
	int register(Member member);
}
