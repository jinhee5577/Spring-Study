<%@page import="com.smhrd.entity.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jstl 쓰는방법 -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!-- 줄바꾸는 작업 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<% pageContext.setAttribute("line", "\n"); %>


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
	    	<table class="table table-hover">
		     <tr>
		     	<th>제목</th>
		     	<th>${vo.title}</th>
		     </tr>	 	
		     <tr>
		     	<th>내용</th>
			    <%-- 방법1. <th><textarea class="form-control" rows="7" readonly="readonly">${vo.content}</textarea></th> --%>
			    <!-- 방법2.  -->
			    <!-- vo.content 에서 감쳐진 line을 발견하면 <br>로 갈아치워라.  -->
			    <th>${fn:replace(vo.content, line, "<br>")}</th>
		     </tr>	 	
		     <tr>
		     	<th>작성자</th>
		     	<th><%= vo.getWriter() %></th>
		     </tr>	 	
		     <tr>
		     	<th>작성일</th>
		     	<th>${fn:split(vo.indate, " ")[0]}</th>
		     </tr>
		     <tr>
		     	<td colspan="2" align="center">
		     	  <a href="boardList.do" class="btn btn-success btn-sm">돌아가기</a>
		     	  <a href="boardUpdateForm.do?idx=${vo.idx}" class="btn btn-warning btn-sm">수정화면</a>
		     	  <a href="boardDelete.do?idx=${vo.idx}" class="btn btn-danger btn-sm">삭제</a>
		     	</td>
		     </tr> 	
		    </table>
	    </div>
	    <div class="panel-footer success">스프링 - 오 진 희</div>
	  </div>
	</div>
    
    
</body>
</html>