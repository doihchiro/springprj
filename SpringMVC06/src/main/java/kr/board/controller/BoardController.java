package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {
	
	@Autowired
	BoardMapper boardMapper;

	@GetMapping("/boardMain.do")
	public String main() {
		return "/board/main";
	}
	
//	@ResponseBody
//	@RequestMapping("/boardList.do")
//	public List<Board> boardList() {
//		List<Board> list = boardMapper.getLists();
//		return list;
//	}
//	
//	@ResponseBody
//	@RequestMapping("/boardInsert.do")
//	public void boardInsert(Board board) {
//		boardMapper.boardInsert(board);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/boardDelete.do")
//	public void boardDelete(@RequestParam("idx") int idx) {
//		boardMapper.boardDelete(idx);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/boardUpdate.do")
//	public void boardUpdate(Board board) {
//		boardMapper.boardUpdate(board);
//	}
//	
//	@ResponseBody
//	@RequestMapping("/boardContent.do")
//	public Board boardContent(int idx) {
//		Board board = boardMapper.boardContent(idx);
//		return board;
//	}
//	
//	@ResponseBody
//	@RequestMapping("/boardCount.do")
//	public Board boardCount(int idx) {
//		boardMapper.boardCount(idx);
//		Board board = boardMapper.boardContent(idx);
//		return board;
//	}
}




















