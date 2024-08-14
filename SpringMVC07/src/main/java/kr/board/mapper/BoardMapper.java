package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;
import kr.board.entity.Member;

@Mapper
public interface BoardMapper {
	
	List<Board> getList();
	void insert(Board board);
	void insertSelectKey(Board board);
	Member login(Member member);
	Board read(int idx);
	void update(Board board);
	void remove(int idx);
	void replySeqUpdate(Board parent);
	void replyInsert(Board board);
}
