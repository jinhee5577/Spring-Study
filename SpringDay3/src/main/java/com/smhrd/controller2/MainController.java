package com.smhrd.controller2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller // 해당 class가 Controller의 역활을 수행한다.
public class MainController {
	
	// index.jsp 이동하는 기능.
	@RequestMapping("/")
	public String index() {
		return "index";
	}
//	public void index() {  이렇게 생략 가능.
//	}
	

}
