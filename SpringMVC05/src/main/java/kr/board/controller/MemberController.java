package kr.board.controller;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import kr.board.entity.AuthVO;
import kr.board.entity.Member;
import kr.board.mapper.MemberMapper;

@Controller
public class MemberController {

	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
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
			m.getMemAge()==0 || m.getAuthList().size()==0 ||
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
		
		// 회원 테이블에 저장하기
		// 추가 : 비밀번호를 암호화 하기
		String encyptPw = passwordEncoder.encode(m.getMemPassword());
		m.setMemPassword(encyptPw);
		
		// register() 수정
		int result = memberMapper.register(m);
		
		if (result == 1) { // 회원 가입 성공 메세지
			// 추가 : 권한 테이블에 회원의 권한을 저장하기
			List<AuthVO> list = m.getAuthList();
			for (AuthVO auth : list) {
				if (auth.getAuth() != null) {
					AuthVO saveVO = new AuthVO();
					saveVO.setMemID(m.getMemID());
					saveVO.setAuth(auth.getAuth());
					memberMapper.authInsert(saveVO);
				}
			}
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원가입에 성공했습니다.");
			
			// 회원가입 성공하면 => 로그인처리하기
			// getMember() => 회원정보 + 권한정보
			Member mvo = memberMapper.getMember(m.getMemID());
			System.out.println("mvo = " + mvo);
			session.setAttribute("mvo", mvo);
			return "redirect:/";
		} else {
		    rttr.addFlashAttribute("msgType", "실패 메세지");
		    rttr.addFlashAttribute("msg", "이미 존재하는 회원입니다.");
		    return "redirect:/memJoin.do";
		}
	}
	
	//로그아웃 처리
	@RequestMapping("/memLogout.do")
	public String memLogout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	//로그인 화면으로 이동 
	@RequestMapping("/memLoginForm.do")
	public String memLoginForm() {
		return "member/memLoginForm";
	}
	
	//로그인 기능 구현
	@RequestMapping("/memLogin.do")
	public String memLogin(Member m, RedirectAttributes rttr, HttpSession session) {
			if(m.getMemID()==null || m.getMemID().equals("") || 
			   m.getMemPassword()==null || m.getMemPassword().equals("")) {
			   rttr.addFlashAttribute("msgType", "실패 메세지");
			   rttr.addFlashAttribute("msg", "모든 내용을 입력해주세요.");
			   return "redirect:/memLoginForm.do";
			}
			Member mvo = memberMapper.memLogin(m);
			//추가 : 비밀번호 일치여부 체크
			if(mvo!=null && passwordEncoder.matches(m.getMemPassword(), mvo.getMemPassword())) { // 로그인에 성공
			   rttr.addFlashAttribute("msgType", "성공 메세지");
			   rttr.addFlashAttribute("msg", "로그인에 성공했습니다.");
			   session.setAttribute("mvo", mvo); // ${!empty mvo}
			   return "redirect:/";	 // 메인		
			}else { // 로그인에 실패
			   rttr.addFlashAttribute("msgType", "실패 메세지");
			   rttr.addFlashAttribute("msg", "다시 로그인 해주세요.");
			   return "redirect:/memLoginForm.do";
			}
	}
	
	// 회원정보수정화면
	@RequestMapping("/memUpdateForm.do")
	public String memUpdateForm() {
		return "member/memUpdateForm";
	}
	
	// 회원정보수정
	@RequestMapping("/memUpdate.do")
	public String memUpdate(Member m, String memPassword1, String memPassword2, 
			  RedirectAttributes rttr, HttpSession session) {
		
		if (m.getMemID()==null || m.getMemID().equals("") ||
			memPassword1==null || memPassword1.equals("") ||
			memPassword2==null || memPassword2.equals("") ||
			m.getMemName()==null || m.getMemName().equals("") ||	
			m.getMemAge()==0 || m.getAuthList().size()==0 ||
			m.getMemGender()==null || m.getMemGender().equals("") ||
			m.getMemEmail()==null || m.getMemEmail().equals("")) {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "모든 내용을 입력하세요.");
			return "redirect:/memUpdateForm.do";
			}
		if (!memPassword1.equals(memPassword2)) {
		   rttr.addFlashAttribute("msgType", "실패 메세지");
		   rttr.addFlashAttribute("msg", "비밀번호가 서로 다릅니다.");
		   return "redirect:/memUpdateForm.do"; 
		}
		// 회원을 수정저장하기
		// 추가 : 비밀번호 암호화
		String encyptPw = passwordEncoder.encode(m.getMemPassword());
		m.setMemPassword(encyptPw);
		int result=memberMapper.memUpdate(m);
		if(result==1) { // 수정성공 메세지
			// 기존 권한을 삭제하고	
			memberMapper.authDelete(m.getMemID());
			
			// 새로운 권한을 추가
			List<AuthVO> list = m.getAuthList();
			for (AuthVO auth : list) {
				if (auth.getAuth() != null) {
					AuthVO saveVO = new AuthVO();
					saveVO.setMemID(m.getMemID());
					saveVO.setAuth(auth.getAuth());
					memberMapper.authInsert(saveVO);
				}
			}
			
			rttr.addFlashAttribute("msgType", "성공 메세지");
			rttr.addFlashAttribute("msg", "회원정보 수정에 성공했습니다.");
			// 회원수정이 성공하면=>로그인처리하기
			Member mvo = memberMapper.getMember(m.getMemID());
			System.out.println("updated mvo = " + mvo);
			session.setAttribute("mvo", mvo); // ${!empty mvo}
			return "redirect:/";
		}else {
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "회원정보 수정에 실패했습니다.");
			return "redirect:/memUpdateForm.do";
		}
	}
	
	// 회원의 사진등록 화면
	@RequestMapping("/memImageForm.do")
	public String memImageForm() {
		return "member/memImageForm";
	}
	
	// 회원사진 이미지 업로드
	@RequestMapping("/memImageUpdate.do")
	public String memImageUpdate(HttpServletRequest request, 
			RedirectAttributes rttr, HttpSession session) {
		
		// 파일업로드 API(cos.jar, 3가지)
		MultipartRequest multi = null;
		int fileMaxSize = 10*1024*1024; // 10MB
		String savePath = request.getRealPath("resources/upload");
		try {
			// 이미지 업로드
			multi = new MultipartRequest(request, savePath, fileMaxSize, "UTF-8", new DefaultFileRenamePolicy());
		} catch (Exception e) {
			e.printStackTrace();
			rttr.addFlashAttribute("msgType", "실패 메세지");
			rttr.addFlashAttribute("msg", "파일의 크기는 10MB를 넘을 수 없습니다.");			
			return "redirect:/memImageForm.do";
		}
		
		// 데이터베이스 테이블에 회원이미지를 업데이트
		String memID = multi.getParameter("memID");
		String newProfile = "";
		File file = multi.getFile("memProfile");
		
		if(file != null) { // 업로드가 된상태(.png, .jpg, .gif)
			// 이미파일 여부를 체크->이미지 파일이 아니면 삭제(1.png)
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			ext = ext.toUpperCase(); // PNG, GIF, JPG
			if(ext.equals("PNG") || ext.equals("GIF") || ext.equals("JPG")){
				// 새로 업로드된이미지(new->1.PNG), 현재DB에 있는 이미지(old->4.PNG)
				String oldProfile = memberMapper.getMember(memID).getMemProfile();
				File oldFile = new File(savePath+"/"+oldProfile);
				if(oldFile.exists()) {
					oldFile.delete();
				}
				newProfile = file.getName();
			}else { // 이미지 파일이 아니면
				if(file.exists()) {
					file.delete(); //삭제
				}
				rttr.addFlashAttribute("msgType", "실패 메세지");
				rttr.addFlashAttribute("msg", "이미지 파일만 업로드 가능합니다.");			
				return "redirect:/memImageForm.do";
			}
		}
		
		// 새로운 이미지를 테이블에 업데이트
		Member mvo = new Member();
		mvo.setMemID(memID);
		mvo.setMemProfile(newProfile);
		memberMapper.memProfileUpdate(mvo); // 이미지 업데이트 성공
		
		// 세션을 새롭게 생성한다.
		Member m = memberMapper.getMember(memID);
		session.setAttribute("mvo", m);
		rttr.addFlashAttribute("msgType", "성공 메세지");
		rttr.addFlashAttribute("msg", "이미지 변경이 성공했습니다.");	
		return "redirect:/";
	}
}





























