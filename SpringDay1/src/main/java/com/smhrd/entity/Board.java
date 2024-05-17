package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor 	// 스프링 에서는 무조건 디폴트 생성자가 있어야한다.
@ToString // 데이터 확인할때 이것만큼 편하다.
public class Board {
	// Board vo 클래스는 IOC컨트롤러에 안올라 간다.
	// 데이터 묶어주기 위한 클래스
	// 번호, 제목, 내용, 작성자, 날짜, 조회수
	private int idx;
	private String title;
	private String content;
	private String writer;
	private String indate;
	private int count;
	
	// 스프링 에서는 무조건 디폴트 생성자가 있어야한다.
	
	

}
