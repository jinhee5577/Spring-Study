package com.smhrd.controller2;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.entity.Member;
import com.smhrd.mapper.MemberMapper;

@Controller
public class MemberController {
	// v -> c -> i -> m- > db
	
	@Autowired
	private MemberMapper memberMapper;
	
	// 회원가입 페이지로 이동 /joinForm.do
	
	@GetMapping("/joinForm.do")
	public String joinForm() {
		return "member/joinForm";
	}
	
	
	// 아이디 중복 검사 기능 /idCheck.do
	@GetMapping("/idCheck.do")
	// 일반 @Controller 이기때문에 @ResponseBody가 반드시 필요함.
	public @ResponseBody int idCheck(@RequestParam("memId") String memId) {
		// 있으면 1 없으면 0 리턴
		Member mem = memberMapper.getMember(memId);
		// mem = null -> 사용 가능한 아이디
		// mem != null -> 사용 불가능한 아이디
		if(mem != null) { return 0; }
		else { return 1; }
    }
	
	
	// 회원가입 기능. /join.do
	@PostMapping("/join.do")
	public String join(Member mem, RedirectAttributes rttr, HttpSession session) {// 아이디, 비밀번호, 나이, 이름, 등등...
		// RedirectAttributes
		// Redirect방식으로 페이지 이동할때 뭔가 전달할 데이터가 있는데
		// request에 담자니 데이터가 사라지고 session담자니 뭔가 아까움...
		// 다음 페이지에 딱 한번만 데이터를 저장해서 뿌려주는 저장소(객체) (잠시 저장해줌. rttr을 사용하면 사라짐.)
		System.out.println(mem.toString());
		
		// 회원가입 실패시
		// null -> name값이 틀린거(못받아옴),  "" -> 아무것도 안적은 것 
		// 쌤 if문
		
		/* 내꺼 if문
		 * if(mem.getMemId() == null || mem.getMemId().equals("") ||
		 * mem.getMemPassword() == null || mem.getMemPassword().equals("") ||
		 * mem.getMemName() == null || mem.getMemName().equals("") || mem.getMemAge() ==
		 * 0 || mem.getMemEmail() == null || mem.getMemEmail().equals("")){
		 * 
		 * 
		 * }
		 */
	
		
		// 쌤if문
		 if(mem.getMemId() == null || mem.getMemId().equals("") ||
				 mem.getMemPassword() == null || mem.getMemPassword().equals("") ||
				 mem.getMemName() == null || mem.getMemName().equals("") || mem.getMemAge() ==
				 0 || mem.getMemEmail() == null || mem.getMemEmail().equals("")){
			// 회원가입 실패시
			rttr.addFlashAttribute("msgType","실패메세지");
			rttr.addFlashAttribute("msg","모든 내용을 입력해주세요.");
		//  return "member/joinForm";
			 // @GetMapping("/joinForm.do")클라이언트단에서 다시 요청해라.(페이지 이동)
			return "redirect:/joinForm.do";  // joinForm.jsp -> ${msgType}, ${msg} 페이지컨텍스트에 저정됨? 스코프가 페이지 이다.
		} else {
			// 사진파일이 없기 때문에 null값이 아닌 ""로 DB에 넣자!. 혹시나 에러 날까봐
			mem.setMemProfile("");
		    int cnt = memberMapper.join(mem);
		   
		   if(cnt == 1) {// 회원가입 성공
			   rttr.addFlashAttribute("msgType", "성공메세지");  // ${msgType} 이케 사용.
			   rttr.addFlashAttribute("msg", "회원가입 성공했습니다.");
			
			   // 회원가입 성공시 로그인 처리까지 하기(카카오로그인 같은거 사용시) 세션에 저장하기.
			   // 회원가입 로그인 성공했으니 회원정보를 session에 저장해준다.
			   session.setAttribute("mem", mem);
			   
			   // rttr사용하고 있으니 redirect로 이동해야함.
			   return "redirect:/";  //메인 홈페이지 이동
			   
		   } else {// 회원가입 실패 데이타입 길이가 길 경우등.	
			   rttr.addFlashAttribute("msgType", "실패 메세지");  // ${msgType} 이케 사용.
			   rttr.addFlashAttribute("msg", "회원가입 실패했습니다.");
			   
			   // rttr 사용하고 있으니 redirect로 이동.
			   return "redirect:/joinForm.do";  // joinForm.jsp로 클라이언트에서 요청해서 이동함.
		   }
			
			
		}
	}
	

}
