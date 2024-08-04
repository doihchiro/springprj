package kr.board.service;

import java.util.List;

import kr.board.entity.Board;
import kr.board.entity.Member;

public interface BoardService {
	
	public List<Board> getList();
	public Member login(Member member);
}
