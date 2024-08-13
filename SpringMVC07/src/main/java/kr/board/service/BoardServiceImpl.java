package kr.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.board.entity.Board;
import kr.board.entity.Member;
import kr.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> getList() {
		List<Board> list = boardMapper.getList();
		return list;
	}

	@Override
	public Member login(Member member) {
		Member loginMember = boardMapper.login(member);
		return loginMember;
	}

	@Override
	public void register(Board board) {
		boardMapper.insertSelectKey(board);
	}

	@Override
	public Board get(int idx) {
		return boardMapper.read(idx);
	}

	@Override
	public void modify(Board board) {
		boardMapper.update(board);
	}

	@Override
	public void remove(int idx) {
		boardMapper.remove(idx);
	}
	
	

}
