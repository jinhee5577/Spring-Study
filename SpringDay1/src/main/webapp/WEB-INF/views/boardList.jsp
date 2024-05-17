<%@page import="com.smhrd.entity.Board"%>
<%@page import="java.util.ArrayList"%>
<%@page import="org.springframework.ui.Model"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!-- jstl 쓰는방법 -->    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- 줄바꾸는 작업, 시간 짜르기도 -->
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


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
    
    <%-- <%
    	// Model 영역안에 있는 list 꺼내기
    	// model은 request객체안에 있다.
    	// forward방식이다. 다른페이지로 데이터를 들고 갈수 있다.
    	List<Board> list = (List<Board>) request.getAttribute("list");
   		System.out.println(list.get(1));
   		
    %> --%>
    
    <!-- jstl list 가져올 필요가 없다. -->
    
    
    <div class="container">
	  <h2>Spring Day1</h2>
	  <div class="panel panel-success">
	    <div class="panel-heading">Board</div>
	    <div class="panel-body mt-5">
	    	<table class="table table-hover">
		    	<thead>
		    	  <tr>
		    	  	<td>번호</td>
		    	  	<td>제목</td>
		    	  	<td>작성자</td>
		    	  	<td>조회수</td>
		    	  </tr>
		    	</thead>
		    	<tbody>
		    	  <!--  BoardController에서 받아온 게시판 리스트를 아래에 출력 -->
		    	  <%-- <% for(Board x: list) {%>
		    	  		<tr>
		    	  			<th><%= x.getContent() %></th>
		    	  			<th><%= x.getTitle() %></th>
		    	  			<th><%= x.getWriter() %></th>
		    	  			<th><%= x.getIndate() %></th>
		    	  			<th><%= x.getCount() %></th>
		    	  		</tr>
		    	  <%} %> --%>
		    	  
		    	  
		    	  <!-- jstl 방법으로 -->
		    	  <c:forEach var="vo" items="${list}"> 
		    	  	 <!-- 스코프 영역만 접근 가능  -->
		    	  	 <tr>
		    	  	 	<td>${vo.idx}</td>
		    	  	 	<td><a href="boardContent.do?idx=${vo.idx}"><c:out value="${vo.title}"></c:out></a></td>
		    	  	 	<td>${vo.writer}</td>
		    	  	 	<td>${fn:split(vo.indate, " ")[0]}</td>
		    	  	 	<td>${vo.count}</td>
		    	  	 </tr>
		    	  	 
		    	  </c:forEach>
		    	</tbody>
		    </table>
		    <a href="boardForm.do" class="btn btn-success btn-sm">글쓰기</a>	
	    </div>
	    <div class="panel-footer success">스프링 - 오 진 희</div>
	  </div>
	</div>
    
    
    
    <div id="myModal" class="modal fade" role="dialog">
	  <div class="modal-dialog">
	
	    <!-- Modal content-->
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal">&times;</button>
	        <h4 class="modal-title">게시글 안내</h4>
	      </div>
	      <div class="modal-body">
	        <p>게시글이 등록 되었습니다. :D</p>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	
	
	<script type="text/javascript">
		$(document).ready(function(){
			let result = '${result}';
			// 이데이터를 사용하고 바로 사라진다.
			
			if(result == 'ok'){ // 새로운 게시글 작성 했을때
			   $('#myModal').modal('show');
			}
		});
	
	</script>
    
</body>
</html>