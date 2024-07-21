package kr.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	MemberMapper memberMapper;
	
	@RequestMapping("/memJoin.do")
	public String memJoin() {
		return "member/join";
	}
	
	@ResponseBody
	@RequestMapping("/memRegisterCheck.do")
	public int memRegisterCheck(@RequestParam("memID") String memID) {
		Member member = memberMapper.registerCheck(memID);
		if (member != null || memID.equals("")) {
			return 0; //이미 존재하는 회원, 입력불가
		}
		return 1; //사용가능한 아이디
	}
	
	@RequestMapping("/memRegister.do")
	public String memRegister(Member m, String memPassword1, String memPassword2, 
							  RedirectAttributes rttr, HttpSession session) {
		
		if (m.getMemID()==null || m.getMemID().equals("") ||
			memPassword1==null || memPassword1.equals("") ||
			memPassword2==null || memPassword2.equals("") ||
			m.getMemName()==null || m.getMemName().equals("") ||	
			m.getMemAge()==0 ||
			m.getMemGender()==null || m.getMemGender().equals("") ||
			m.getMemEmail()==null || m.getMemEmail().equals("")) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/memJoin.do";
		}
		if (!memPassword1.equals(memPassword2)) {
		   rttr.addFlashAttribute("msgType", "실패 메세지");
		   rttr.addFlashAttribute("msg", "비밀번호가 서로 다릅니다.");
		   return "redirect:/memJoin.do"; 
		}
		m.setMemProfile(""); // 사진이미는 없다는 의미 ""
		int result = memberMapper.register(m);
		
		if (result == 1) {
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원가입에 성공했습니다.");
			
			session.setAttribute("mvo", m);
			return "redirect:/";
		} else {
		    rttr.addFlashAttribute("msgType", "실패 메세지");
		    rttr.addFlashAttribute("msg", "이미 존재하는 회원입니다.");
		    return "redirect:/memJoin.do";
		}
	}
}





























