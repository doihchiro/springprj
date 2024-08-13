package kr.board.service;

import java.util.List;

import kr.board.entity.Board;
import kr.board.entity.Member;

public interface BoardService {
	
	List<Board> getList();
	Member login(Member member);
	void register(Board board);
	Board get(int idx);
}
