package com.smhrd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor 	// 스프링 에서는 무조건 디폴트 생성자가 있어야한다.
@ToString // 데이터 확인할때 이것만큼 편하다.
public class Member {
   private int memIdx;
   private String memId;
   private String memPassword;
   private String memName;
   private int memAge;
   private String memGender;
   private String memEmail;
   private String memProfile;
	
}
