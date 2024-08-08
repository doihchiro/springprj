package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Board;
import kr.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;

	@GetMapping("/list")
	public String list(Model model) {
		List<Board> list = boardService.getList();
		model.addAttribute("list", list);
		return "board/list";
	}
	
	@GetMapping("/register")
	public String register() {
		return "board/register";
	}
	
	@PostMapping("/register")
	public String register(Board board, RedirectAttributes rttr) {
		boardService.register(board);
		System.out.println(board);
		rttr.addFlashAttribute("result", board.getIdx()); // ${result}
		return "redirect:/board/list";
	}
}




















