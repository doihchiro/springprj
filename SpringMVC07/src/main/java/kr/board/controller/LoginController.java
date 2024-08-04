package kr.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import kr.board.entity.Member;
import kr.board.service.BoardService;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	@Autowired
	BoardService boardService;

	@PostMapping("/loginProcess")
	public String login(Member member, HttpSession session) {
		Member mvo = boardService.login(member);
		if (mvo != null) {
			session.setAttribute("mvo", mvo);
		}
		return "redirect:/board/list";
	}
	
	@PostMapping("/logoutProcess")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/board/list";
	}
}
