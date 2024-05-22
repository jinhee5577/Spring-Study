package com.smhrd.controller2;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.smhrd.entity.Board;
import com.smhrd.mapper.BoardMapper;


@Controller // 해당 class가 Controller의 역활을 수행한다.
public class BoardController {
	// 게시판 관련 기능 수행.
	// Req(URL) : ip:port/context_path/- 	
	//
	
	@Autowired
    // 이 어노테이션을 쓰면 interface에서 만든 클래스 객체가 자동으로 만들어져서 여기로 불러올수 있다.
	// 그래서 불러온 객체를 요기 아래 mapper에 담아주었다.
	// BoardMapper객체를 주입 받는다.
	private BoardMapper mapper;	
	
	// View -> F.CController -> Interface -> Mapper
	
	
	// "/"로 요청이 들어왓을때 main.jsp로 이동.
	@GetMapping("/")  // 이것만 동기 이다.
    public String main() {
		
		return "main";
	}
	
	
	/*
	 * // 게시글 전체보기 (비동기 방식으로 가져 올것이다.) // 비동기 방식은 데이터를 반환한다. // 비동기 방식으로 데이터를 응답
	 * 받을때 @ResponseBody가 무조건 필요하다. // @ResponseBody에 담아서 보낸다.
	 * 
	 * @GetMapping("boardList.do") 
	 * public @ResponseBody List<Board> boardList() {
	 * System.out.println("[게시글 전체보기]"); 
	 * List<Board> list = mapper.boardList();
	 * 
	 * return list; }
	 * 
	 * 
	 * // 게시글 등록하기
	 * 
	 * @PostMapping("boardInsert.do") 
	 * public @ResponseBody void boardInsert(Board board) { mapper.boardInsert(board); }
	 * 
	 * 
	 * // 게시글 상세 보기 // V -> C -> I -> M
	 * 
	 * @GetMapping("boardContent.do") 
	 * public @ResponseBody Board boardContent(@RequestParam("idx") int idx) { // @RequestParam("idx")생략 가능.
	 * Board board = mapper.boardContent(idx);
	 * 
	 * return board; }
	 * 
	 * 
	 * // 게시글 하나 삭제하기. // 이제 두가지 방법 모두 다 잘됨.
	 * 
	 * @DeleteMapping("boardDelete.do") 
	 * // @GetMapping("boardDelete.do")
	 * public @ResponseBody void boardDelete(@RequestParam("idx") int idx) {
	 * mapper.boardDelete(idx); }
	 * 
	 * 
	 * // 게시글 수정하기
	 * 
	 * @PostMapping("boardUpdate.do") 
	 * public @ResponseBody void boardUpdate(Board board) { // idx, title, content // 조회수 올리기. //
	 * mapper.boardCount(board.getIdx()); 
	 * mapper.boardUpdate(board); 
	 * }
	 * 
	 * // 게시글 조회수 올리기
	 * 
	 * @GetMapping("boardCount.do") 
	 * public @ResponseBody void boardCount(@RequestParam("idx") int idx) { mapper.boardCount(idx); }
	 */
	
	
}
