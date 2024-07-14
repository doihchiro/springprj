package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.board.entity.Board;
import kr.board.mapper.BoardMapper;

@Controller
public class BoardController {

	@Autowired
	private BoardMapper mapper;
	
	@GetMapping("/boardList.do")
	public String boardList(Model model) {
		List<Board> list = mapper.getLists();
		model.addAttribute("list", list);
		return "boardList";
	}
	
	@GetMapping("/boardForm.do")
	public String boardForm() {
		return "boardForm";
	}
	
	@PostMapping("/boardInsert.do")
	public String boardInsert(Board board) {
		mapper.boardInsert(board);
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardContent.do") 
	public String boardContent(@RequestParam("idx") int idx, Model model) {
		Board board = mapper.boardContent(idx);
		// 조회수 증가
		mapper.boardCount(idx);
		
		model.addAttribute("vo", board);
		return "boardContent";
	}
	
	@GetMapping("/boardDelete.do/{idx}")
	public String boardDelete(@PathVariable("idx") int idx) {	
		mapper.boardDelete(idx);
		return "redirect:/boardList.do";
	}
	
	@GetMapping("/boardUpdateForm.do/{idx}")
	public String boardUpdateForm(@PathVariable("idx") int idx, Model model) {
		Board board = mapper.boardContent(idx);
		model.addAttribute("vo", board);
		return "boardUpdate";
	}
	
	@PostMapping("/boardUpdate.do")
	public String boardUpdate(Board board) {
		mapper.boardUpdate(board);
		return "redirect:/boardList.do";
	}
	
}




















