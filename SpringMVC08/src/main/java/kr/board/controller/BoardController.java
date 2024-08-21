package kr.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Board;
import kr.board.entity.Criteria;
import kr.board.entity.PageMaker;
import kr.board.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;

	@GetMapping("/list")
	public String list(Criteria cri, Model model) {
		List<Board> list = boardService.getList(cri);
		model.addAttribute("list", list);
		
		// paging 처리에 필요한 부분
		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(cri);
		pageMaker.setTotalCount(boardService.totalCount());
		model.addAttribute("pageMaker", pageMaker);
		
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
	
	@GetMapping("/get")
	public String get(@RequestParam("idx") int idx, Model model, @ModelAttribute("cri") Criteria cri) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/get";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("idx") int idx, Model model, @ModelAttribute("cri") Criteria cri) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/modify";
	}
	
	@PostMapping("/modify")
	public String modify(Board board, Criteria cri, RedirectAttributes rttr) {
		boardService.modify(board);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		return "redirect:/board/list";
	}
	
	@GetMapping("/remove")
	public String remove(@RequestParam("idx") int idx, Criteria cri, RedirectAttributes rttr) {
		boardService.remove(idx);
		rttr.addAttribute("page", cri.getPage());
		rttr.addAttribute("perPageNum", cri.getPerPageNum());
		return "redirect:/board/list";
	}
	
	@GetMapping("/reply")
	public String reply(@RequestParam("idx") int idx, Model model) {
		Board vo = boardService.get(idx);
		model.addAttribute("vo", vo);
		return "board/reply";
	}
	
	@PostMapping("/reply")
	public String reply(Board board) {
		// 답글에 필요한 처리....
		System.out.println("reply = " + board);
		boardService.replyProcess(board); // 답글 저장됨
		
		return "redirect:/board/list";
	}
}




















