package kr.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

import kr.board.entity.Board;

@Mapper
public interface BoardMapper {
	List<Board> getLists();
	void boardInsert(Board board);
	Board boardContent(int idx);
	void boardDelete(int idx);
	void boardUpdate(Board board);
	
	//조회수 증가
	@Update("update myboard set count=count+1 where idx=#{idx}")
	void boardCount(int idx);
}
