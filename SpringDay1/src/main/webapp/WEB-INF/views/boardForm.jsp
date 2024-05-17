<%@page import="com.smhrd.entity.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jstl 쓰는방법 -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style>
	a: hover{text-decoration: none;}
</style>
</head>
<body>
	
    <!-- http://localhost:8081/controller/boardList.do 로 접속해야 보임. --> 
    
     <%
    	// Model 영역안에 있는 list 꺼내기
    	// model은 request객체 안에있다.
		// 스프링의 기본적인 페이지 이동방식은 forward방식이다.
    	// forward방식이다. 다른페이지로 데이터를 들고 갈수 있다.
    	Board vo = (Board) request.getAttribute("vo");   		
     %> 
    
    <!-- jstl list 가져올 필요가 없다. -->
    
    
    <div class="container">
	  <h2>Spring Day1</h2>
	  <div class="panel panel-success">
	    <div class="panel-heading">Board</div>
	    <div class="panel-body mt-5">
	      <form action="boardInsert.do" method="POST">
	    	<table class="table table-hover">
		     <tr>
		     	<th>제목</th>
		     	<th><input required="required" class="form-control" type="text" name="title" ></th>
		     </tr>	 	
		     <tr>
		     	<th>내용</th>
		     	<th><textarea name="content" class="form-control" rows="7" cols=""></textarea></th>
		     </tr>	 	
		     <tr>
		     	<th>작성자</th>
		     	<th><input required="required"  type="text" class="form-control"  name="writer" ></th>
		     </tr>
		     <tr>
		     	<td colspan="2" align="center">
		     	   <a class="btn btn-info btn-sm" href="boardList.do" >돌아가기</a>
		     	   <button type="rest" class="btn btn-warning">취소</button>
		     	   <button type="submit" class="btn btn-success">등록</button>
		     	</td>
		     </tr>
		    </table>
		  </form> 
	    </div>
	    <div class="panel-footer success">스프링 - 오 진 희</div>
	  </div>
	</div>
    
    
</body>
</html>