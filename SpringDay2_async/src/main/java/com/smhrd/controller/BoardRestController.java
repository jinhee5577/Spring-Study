package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.smhrd.entity.Board;
import com.smhrd.mapper.BoardMapper;

// 비동기 방식만 가능 하다. 동기 방식은 불가능하다.
// 데이터, 객체, 배열 이런것들만 리턴한다.
// 페이지 이동이 불가능 하다.
// 비동기 전용 컨트롤러 이다.
@RequestMapping("/board")  // 여기 컨트롤러에 들어온 모든 요청은 /bord경로로 들어와야한다!.
@RestController
public class BoardRestController {
	   // @RestController는 비동기 전용이기에 @ResponseBody가 필요 없다.
	 
		@Autowired
	    // 이 어노테이션을 쓰면 interface에서 만든 클래스 객체가 자동으로 만들어져서 여기로 불러올수 있다.
		// 그래서 불러온 객체를 요기 아래 mapper에 담아주었다.
		// BoardMapper객체를 주입 받는다.
		private BoardMapper mapper;	
	
	// 게시글 전체보기 (비동기 방식으로 가져 올것이다.)
		// 비동기 방식은 데이터를 반환한다.
		// 비동기 방식으로 데이터를 응답 받을때 @ResponseBody가 무조건 필요하다.
		// @ResponseBody에 담아서 보낸다.
		
	//	@GetMapping("boardList.do")
		@GetMapping("/all")  //  board/all
		public List<Board> boardList() {
			System.out.println("[게시글 전체보기]");
			List<Board> list = mapper.boardList();
				
			return list;
		}
		
		
		// 게시글 등록하기
		/*
		 * @PostMapping("boardInsert.do")
		 * public void boardInsert(Board board) {
		 * mapper.boardInsert(board); }
		 */
		
		@PostMapping("/new")
		public void boardInsert(Board board) { // post는 @RequestBody Board board 안붙여줘도 됨.
			mapper.boardInsert(board);
		}
		
		
		// 게시글 상세 보기
		// V -> C -> I -> M
	 //	@GetMapping("boardContent.do")
		/*
		 * public Board boardContent(@RequestParam("idx") int idx) {
		 * // @RequestParam("idx")생략 가능. Board board = mapper.boardContent(idx);

		 * return board; }
		 */
		@GetMapping("/{idx}")  //  board/{idx}
		public Board boardContent(@PathVariable("idx") int idx) { // @RequestParam("idx")생략 가능.
			Board board = mapper.boardContent(idx);
			
			return board;
		}
		
		
		// 게시글 하나 삭제하기.
		// 이제 두가지 방법 모두 다 잘됨.
	//	@DeleteMapping("boardDelete.do")
	  //  @GetMapping("boardDelete.do") 
	//	public void boardDelete(@RequestParam("idx") int idx) {
	//		mapper.boardDelete(idx);
	//	}
		
		@DeleteMapping("/{idx}")
		public void boardDelete(@PathVariable("idx") int idx) {
			mapper.boardDelete(idx);
		}
		
		
		// 게시글 수정하기
		/*
		 * @PostMapping("boardUpdate.do")
		 * public void boardUpdate(Board board) { // idx,
		 * title, content // 조회수 올리기. // mapper.boardCount(board.getIdx());
		 * mapper.boardUpdate(board); }
		 */
		
		@PutMapping("/update")
		public void boardUpdate(@RequestBody Board board) { // idx, title, content
			// 오로지 PUT요청만 @RequestBody 붙여준다.
			// @RequestBody Board board 는 ajax요청에서 넘겨준 json객체 데이터를 Board board로 다시 알아서 바꿔준다.
			// 조회수 올리기.
	    //	mapper.boardCount(board.getIdx());
			mapper.boardUpdate(board);
		}
		
		
		// 게시글 조회수 올리기
		/*
		 * @GetMapping("boardCount.do")
		 * public void boardCount(@RequestParam("idx") int
		 * idx) { mapper.boardCount(idx); }
		 */
		
		@PutMapping("/count/{idx}")
		public void boardCount(@PathVariable("idx") int idx) { 
			mapper.boardCount(idx); 
		}
	

}
