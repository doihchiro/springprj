package kr.board.service;

import java.util.List;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.Member;

public interface BoardService {
	
	List<Board> getList(Criteria cri);
	Member login(Member member);
	void register(Board board);
	Board get(int idx);
	void modify(Board board);
	void remove(int idx);
	void replyProcess(Board board);
	int totalCount(Criteria cri);
}
