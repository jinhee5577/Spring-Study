package com.smhrd.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.smhrd.entity.Board;


@Mapper
// 요런 어노테이션들이 붙으면 여기서 만들어지는 클래스나 인터페이스 객체들이 전부다 IOC Container로 들어간다.
public interface BoardMapper {
	// Spring과 MyBatis를 연결하는 역활
	
	// 게시글 목록보기.
	// 몸통을 구현할 필요가 없다. MyBatis가 알아서 구현한다.
	// 따라서 추상메서드로 구현하고 class였던 BoardMapper를 interface로 바꿔준다.(추상 클래스로 만들어 주기 위해)
	// 내가 하고싶은걸 여기다 쓰면 sql세션 펙토리가 알아서 다 구현해준다.
	
	// 게시글 목록보기.
 //	@Select("SELECT * FROM BOARD")
	// 내가 받고싶은 데이터 타입 써주고 위에 쿼리문 써준다.
	public List<Board> boardList();

//	@Select("SELECT * FROM BOARD WHERE IDX= #{idx}")
	public Board boardContent(int idx);

//	@Insert("INSERT INTO BOARD (TITLE,CONTENT,WRITER) VALUES(#{title},#{content},#{writer})")
	public void boardInsert(Board vo);

//	@Delete("DELETE FROM BOARD WHERE IDX = #{idx}")
	public void boardDelete(int idx);

//	@Update("UPDATE BOARD SET TITLE=#{title}, CONTENT=#{content}, WRITER=#{writer} WHERE IDX=#{idx}")
	public void boardUpdate(Board vo);

	// 조회수 올리기.
//	@Update("UPDATE BOARD SET COUNT=COUNT+1 WHERE IDX=#{idx}")
	public void boardCount(int idx);
	
	
	

}


// 