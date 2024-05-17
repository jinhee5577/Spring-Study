package com.smhrd.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.entity.Board;
import com.smhrd.mapper.BoardMapper;


@Controller // 해당 class가 Controller의 역활을 수행한다.
public class BoardController {
	// 게시판 관련 기능 수행.
	// Req(URL) : ip:port/context_path/- 	
	
	@Autowired
    // 이 어노테이션을 쓰면 interface에서 만든 클래스 객체가 자동으로 만들어져서 여기로 불러올수 있다.
	// 그래서 불러온 객체를 요기 아래 mapper에 담아주었다.
	// 객체를 주입 받는다.
	private BoardMapper mapper;	
	
	// 메인실행
	@RequestMapping("/")
	public String home (Model model) {
		return "redirect:/boardList.do";
	}
	
	// 게시판 목록
	// 해당 요청이 들어왔을대 특정 기능을 연결 시켜준다.
	@RequestMapping("boardList.do")
	public String boardList (Model model) {		
		// 번호, 제목, 내용, 작성자, 날짜, 조회수 -> 하나에 담자 DTO
//		Board vo1 = new Board(1, "봄이 얼마 안남았쪄","봄좀봄"," 버스커", "2024-05-16",3);
//		Board vo2 = new Board(2, "파묘 1000만 넘음","이도현 넘 잘생김", "이도현", "2024-05-16",13);
//		Board vo3 = new Board(3, "오늘 수업은 오전만 진행한다고?","힝 속았네"," 송승호", "2024-05-16",6);
//		Board vo4 = new Board(4, "여러분 점심 맛나게 먹어용.","존맛챙"," 병관박", "2024-05-16",23);
//		Board vo5 = new Board(5, "남도의 자랑 낙지탕탕이!","but난 조개가 더 맛있어"," 지니오", "2024-05-16",63);
		
		// System.out.println(vo1.toString());
		
		/* List<Board> list = new ArrayList<Board>(); */
	    List<Board> list = mapper.boardList();
		/*
		  list.add(vo1);
		  list.add(vo2);
		  list.add(vo3);
		  list.add(vo4);
		  list.add(vo5);
		 */	    
	    
	    model.addAttribute("list", list);
		
		// 뷰네임을 돌려줌.
		return "boardList"; // WEB-INF/views/boardList.jsp  (이걸 만드는걸 (View Resolver)가 해준다.)
	}
	
	
	// 게시판 상세보기
	@GetMapping("boardContent.do")
	public String boardContent (@RequestParam("idx") int idx, Model model) { // @RequestParam("idx")생략 가능.
		// 조회수
		mapper.boardCount(idx);
		Board vo = mapper.boardContent(idx);
		model.addAttribute("vo", vo);
		// model은 request객체 안에있다.
		// 스프링의 기본적인 페이지 이동방식은 forward방식이다.
		// System.out.println(vo.toString());  // 모든값이 잘있는지 확인. 
		
		return "boardContent";
	}
	
	
	// 게시글 작성 페이지 이동.
	@GetMapping("boardForm.do")
	public String boardForm (Model model) { // @RequestParam("idx")생략 가능.\		
		// a테그에서 클릭해서 페이지 이동해도 스프링에서는 반드시 Dispatcher Servlet을 거쳐야 한다.
		return "boardForm"; 		
	}
	
	
	// 게시글 작성.
	@PostMapping("boardInsert.do")  // 스프링에서는 post에서만 보안을 걸수 있다.
	public String boardInsert (Board vo, RedirectAttributes rttr) {
		// Board vo 클래스는 IOC컨트롤러에 안올라 간다.
		mapper.boardInsert(vo);
		// forward방식으로 이동할수 없을때 redirect로 이동할때 잠깐 동안 이용할수있는 저장할수있는 메모리.
		// 이데이터를 사용하고 바로 사라진다.
		rttr.addFlashAttribute("result", "ok");
        // redirect 키워드가 서블릿에서의 sendredirect 기능이 들어가 있다.	
		return "redirect:/boardList.do";
	}
	
	
	// 게시글 삭제.
	@GetMapping("boardDelete.do")  // 스프링에서는 post에서만 보안을 걸수 있다.
	public String boardDelete (@RequestParam("idx") int idx, Model model) {
		// Board vo 클래스는 IOC컨트롤러에 안올라 간다.
		mapper.boardDelete(idx);
		
        // redirect 키워드가 서블릿에서의 sendredirect 기능이 들어가 있다.	
		return "redirect:/boardList.do";
	}
	
	
	// 게시글 수정화면 이동.
	@GetMapping("boardUpdateForm.do")  // 스프링에서는 post에서만 보안을 걸수 있다.
	public String boardUpdateForm (@RequestParam("idx") int idx, Model model) {
		// Board vo 클래스는 IOC컨트롤러에 안올라 간다.
	    Board vo = mapper.boardContent(idx);
	    // model에 담겨서 페이지 이동한다.
	    model.addAttribute("vo", vo);
       
		return "boardUpdateForm";
	}
	
	
	// 게시글 수정.
	@PostMapping("boardUpdate.do")  // 스프링에서는 post에서만 보안을 걸수 있다.
	public String boardUpdate(Board vo) { 
        // Board vo 이걸 쓸려면 디폴트 생성자와 setter메소드가 정의 되어 있어야한다.
		// Board vo 클래스는 IOC컨트롤러에 안올라 간다.
		mapper.boardUpdate(vo);
		
        // redirect 키워드가 서블릿에서의 sendredirect 기능이 들어가 있다.	
		return "redirect:/boardContent.do?idx=" + vo.getIdx();
	}    
	
	
	// 조회수 기능.
	
	
}
