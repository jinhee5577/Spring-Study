package com.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.smhrd.entity.Member;

@Mapper
// Mapper 클래스이다.
// 요런 어노테이션들이 붙으면 여기서 만들어지는 클래스나 인터페이스 객체들이 전부다 IOC Container로 들어간다.
public interface MemberMapper {
	
	// Spring과 MyBatis를 연결하는 역활
	
	// 게시글 목록보기.
	// 몸통을 구현할 필요가 없다. MyBatis가 알아서 구현한다.
	// 따라서 추상메서드로 구현하고 class였던 MemberMapper를 interface로 바꿔준다.(추상 클래스로 만들어 주기 위해)
	// 내가 하고싶은걸 여기다 쓰면 sql세션 펙토리가 알아서 다 구현해준다.
	
	// 아이디 중복 검사 하기위해 
	public Member getMember(String memId);

	// 회원가입
	public int join(Member mem);
	
	
	

}


// 