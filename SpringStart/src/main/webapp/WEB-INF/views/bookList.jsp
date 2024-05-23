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
	
    <!--  http://localhost:8081/bshop/bookList.do 로 접속해야 보임. --> 

    <div class="container">
	  <h2>보유 도서 현황</h2>
	  <div class="panel panel-success">
	    <div class="panel-heading">Board</div>
	    <div class="panel-body mt-5">
	    	<table class="table table-hover">
		    	<thead>
		    	  <tr>
		    	  	<td>번호</td>
		    	  	<td>제목</td>
		    	  	<td>작가</td>
		    	  	<td>출판사</td>
		    	  	<td>ISBN</td>
		    	  	<td>보유도서수</td>
		    	  </tr>
		    	</thead>
		    	<tbody>		    	  
		    	  <!-- jstl 방법으로 -->
		    	  <c:forEach var="vo" items="${list}"> 
		    	  	 <!-- 스코프 영역만 접근 가능  -->
		    	  	 <tr>
		    	  	 	<td>${vo.num}</td>
		    	  	 	<td><c:out value="${vo.title}"></c:out></td>
		    	  	 	<td>${vo.author}</td>
		    	  	 	<td>${vo.company}</td>
		    	  	 	<td>${vo.isbn}</td>
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
    
    
     <div class="container">
	  <h2>BOOK INSERT</h2>
	  <div class="panel panel-success">
	    <div class="panel-heading">BOOK INSERT</div>
	    <div class="panel-body mt-5">
	      <form action="bookInsert.do" method="POST">
	    	<table class="table table-hover">
		     <tr>
		     	<th>제목</th>
		     	<th><input class="form-control" type="text" name="title" ></th>
		     </tr>	 	
		     <tr>
		     	<th>작가</th>
		     	<th><input type="text" name="author" class="form-control" ></th>
		     </tr>	 	
		     <tr>
		     	<th>출판사</th>
		     	<th><input type="text" class="form-control"  name="company" ></th>
		     </tr>
		     <tr>
		     	<th>ISBN</th>
		     	<th><input type="text" class="form-control"  name="isbn" ></th>
		     </tr>
		     <tr>
		     	<th>보유도서 수</th>
		     	<th><input type="number" class="form-control"  name="count" ></th>
		     </tr>
		     <tr>
		     	<td align="center">
		     	  <input type="submit" value="등록" >
		     	</td>
		     </tr>
		    </table>
		  </form> 
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