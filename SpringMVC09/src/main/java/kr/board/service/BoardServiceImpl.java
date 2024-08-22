package kr.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.Member;
import kr.board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	BoardMapper boardMapper;
	
	@Override
	public List<Board> getList(Criteria cri) {
		List<Board> list = boardMapper.getList(cri);
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

	@Override
	public void replyProcess(Board board) {
		// - 답글만들기
		// 1. 부모글(원글)의 정보를 가져오기(vo->idx)
		Board parent = boardMapper.read(board.getIdx());
		// 2. 부모글의 boardGroup의 값을 -> 답글(vo)정보에 저장하기
		board.setBoardGroup(parent.getBoardGroup());
		// 3. 부모글의 boardSequence의 값을 1을 더해서 --> 답글(vo)정보에 저장하기
		board.setBoardSequence(parent.getBoardSequence()+1);
		// 4. 부모글의 boardLevel의 값을 1을 더해서 --> 답글(vo)정보에 저장하기
		board.setBoardLevel(parent.getBoardLevel()+1);
		// 5. 같은 boardGroup에 있는 글 중에서
		// 	  부모글의 boardSequence보다 큰 값들을 모두 1씩 업데이트하기
		boardMapper.replySeqUpdate(parent);
		// 6. 답글(vo)을 저정하기
		boardMapper.replyInsert(board);
	}

	@Override
	public int totalCount(Criteria cri) {
		return boardMapper.totalCount(cri);
	}
	
	

}
