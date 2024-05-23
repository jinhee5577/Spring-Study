package com.smhrd.controller2;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.smhrd.entity.Member;
import com.smhrd.mapper.MemberMapper;

@Controller
public class MemberController {
	// v -> c -> i -> m- > db
	
	@Autowired
	private MemberMapper memberMapper;
	
	// 로그인 기능 / login.do
	@PostMapping("/loin.do")
	public String loin(Member mem, RedirectAttributes rttr, HttpSession session) {		
		Member logMem = memberMapper.loin(mem);
		
		if(logMem == null) { // 로그인 실패			
			rttr.addFlashAttribute("msgType","실패메세지");
			rttr.addFlashAttribute("msg","아이디와 비밀번호를 다시 입력해주세요.");
			
			// rttr사용 했으니 클라이언트에서 redirect로 페이지 전환 요청해야한다.
			return "redirect:/loginForm.do";
		} else { // 로그인 성공
			rttr.addFlashAttribute("msgType","성공메세지");
			rttr.addFlashAttribute("msg","로그인에 성공했습니다.");
			session.setAttribute("mem", logMem); // 로그인 성공 했으니 로그인 유저 정보 session에 저장.
			
			return "redirect:/";
		}
	}
	
	
	// 로그인 페이지로 이동 /loginForm.do
	@GetMapping("/loginForm.do")
    public String loginForm() {
       return "member/loginForm";
    }
	
	
	// 이미지 업로드하는 페이지 이동.
	@GetMapping("/imageForm.do")
	public String imageForm() {
		return "member/imageForm";
	}
	
	
	// 이미지 업로드 기능. imageUpdate.do
	@PostMapping("/imageUpdate.do")
	public String imageUpdate(HttpServletRequest request, RedirectAttributes rttr, HttpSession session) {
		// 파일업로드시 필요한API -> cos.jar -> MultipartRequest객체를 생성해서 사용할수 있음.
		MultipartRequest multi = null;
		
		// MultipartRequest객체 생성하기위해 매개변수 필요.
		// 1. request 객체
		// 2. 이미지를 저장할 폴더의 경로(String)
		// 3. 허용가능한 크기(int)
		// 4. 파일이름에 대한 인코딩(String)
		// 5. 파일명 중복 제거
		
	//	String savePath = request.getRealPath("resources/upload");  // 본인 pc의 절대경로.
		String savePath = request.getSession().getServletContext().getRealPath("resources/upload");  // 본인 pc의 절대경로.
		// System.out.println(savePath);
		int maxSize = 1024*1024*100;  //100mb
		String encoding ="UTF-8";
		DefaultFileRenamePolicy rename = new DefaultFileRenamePolicy();
		
		// 기존 가지고 있는 로그인한 사용자의 프로필 이미지 삭제
		String memId = ((Member)session.getAttribute("mem")).getMemId();
		String oldImg = memberMapper.getMember(memId).getMemProfile();
		
		File oldFile = new File(savePath + "/" +oldImg);  // upload폴더에 있는 기존 저장 이미지파일을 가지고옴.
		
		// 파일이 존재할때만 삭제.
		if(oldFile.exists()) {
			oldFile.delete();
		}
		
		
		try {
			// 객체 생성한걸 안담아도 거치기만 해도, 이코드를 만나기만 해도 savePath가 생성됨. 이미지가 등록됨.
			multi = new MultipartRequest(request, savePath,maxSize,encoding, rename);			
			
		} catch (IOException e) {
			// savePath 이경로를 틀렸을때, 파일저장 경로를 못찾았을대 예외처리.
			rttr.addFlashAttribute("msgType","실패메세지");
			rttr.addFlashAttribute("msg","경로를 찾을수 없습니다.");
			e.printStackTrace();
			
			return "redirect:/imageForm.do";
		}
		
		// img파일인지 판별하기.(jpg, png, jpeg, gif)
		// 내가 보낸 파일 가져오기.
		File file = multi.getFile("memProfile");  // input에서 넘어온 type=file의 name값이다. => .getFile("memProfile"); 
	//	System.out.println(file.getName());
		
		if(file != null) { 
			// 확장자만 잘라서 판별.
			String ext = file.getName().substring(file.getName().lastIndexOf(".")+1);
			// 다 대문자로 바꿔주기.,
			ext = ext.toUpperCase();
			boolean extResult = ext.equals("JPG") || ext.equals("PNG") || ext.equals("JPEG") || ext.equals("GIF");
			
			if(!extResult) {
				// 이미지 파일이 아닌경우
				if(file.exists()) {
					file.delete();
					rttr.addFlashAttribute("msgType","실패메세지");
					rttr.addFlashAttribute("msg","이미지 파일만 가능합니다.");
					
					return "redirect:/imageForm.do";
				}
			}			
		}
		
		// 이미지 파일만 저장.
		// 서버에서 받은 사진을 저장.
	    String newProfile = multi.getFilesystemName("memProfile");
		// Mapper에 넣기위해 memId, newProfile ->  Member객체 묶어서 DB에 넣어주기.
	    		
		return "member/imageForm";
	}	
			
	
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
		// 다음페이지에서 사용할 작은 저장소.
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
		} else { // 입력한 값이 null값도 없고 ""도 없다!.
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
			   //만약
				/*
				 * if(mem.getMemId().length() > 20) { 
				 * // 이렇게 데이터 타입 길이가 db에 정한 길이보다 더 길어진 경우들 
				 * // 회원 가입이 안됨. }
				 */
			   rttr.addFlashAttribute("msgType", "실패 메세지");  // ${msgType} 이케 사용.
			   rttr.addFlashAttribute("msg", "회원가입 실패했습니다.");
			   
			   // rttr 사용하고 있으니 redirect로 이동.
			   return "redirect:/joinForm.do";  // joinForm.jsp로 클라이언트에서 요청해서 이동함.
		   }
			
			
		}
	}
	
	
	// 로그아웃 기능.
	@GetMapping("/logout.do")
	public String logout(HttpSession session, RedirectAttributes rttr) { // HttpSession 주입 받아 쓴다.
		    session.removeAttribute("mem");
		//  session.invalidate();  이건 세션 몽땅다 지워버린다.
		
		    rttr.addFlashAttribute("msgType","로그아웃메세지");
		    rttr.addFlashAttribute("msg","정상 로그아웃 되었습니다.");		    
		// rttr 사용했으니 redirect로 페이지 이동해야한다.
		// 메인페이지로 이동. 
		return "redirect:/";  
	}
	

	 
	
	
	
	

}
