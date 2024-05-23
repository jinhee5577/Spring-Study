package com.smhrd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.entity.BookVO;
import com.smhrd.mapper.BookMapper;

@Controller // 해당 class가 Controller의 역활을 수행한다.
public class BookController {
	
	@Autowired
	private BookMapper mapper;
	
	// 메인실행
	@RequestMapping("/")
	public String home (Model model) {
		return "redirect:/bookList.do";
	}
	
	@RequestMapping("/bookList.do")
	public void bookList(Model model) {
		List<BookVO> list = mapper.bookList();
		model.addAttribute("list", list);
	}
	
	@RequestMapping("/bookInsert.do")
	public String bookInsert(BookVO vo) {
		mapper.bookInsert(vo);
		//어디로 리턴하는거야?
		return "redirect:/bookList.do";
	}
	
	
	
}
