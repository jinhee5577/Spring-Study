<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
   <c:set var="contextPath" value="${pageContext.request.contextPath}" />
   <%-- 링크 경로의 pageContext.request.contextPath를 변수 : contextPath에 담겠다.  --%>

   <nav class="navbar navbar-inverse">
      <div class="container-fluid">
         <div class="navbar-header">
            <a class="navbar-brand" href="${contextPath }/">Spring Day3</a>  <%-- contextPath == /controller  이다. --%>
         </div>
         <ul class="nav navbar-nav">
            <li class="active"><a href="${contextPath }/">Home</a></li>
            <li><a href="boardMain.do">게시판</a></li>
         </ul>
         
         <c:if test="${empty mem}">  <!-- 조건식 이다. 로그인 안되있을경우 -->
	         <ul class="nav navbar-nav navbar-right">
	            <li><a href="joinForm.do"><span class="glyphicon glyphicon-user"></span>
	                  Sign Up</a></li>
	            <li><a href="loginForm.do"><span class="glyphicon glyphicon-log-in"></span>
	                  Login</a></li>
	         </ul>
         </c:if>
         <c:if test="${not empty mem}">  <!-- 조건식 이다. 로그인 되어있을경우-->
	         <ul class="nav navbar-nav navbar-right">
	            <li><a href=""><span class="glyphicon glyphicon-wrench"></span>
	                  회원정보수정</a></li>
	            <li><a href="imageForm.do"><span class="glyphicon glyphicon-film"></span>프로필사진등록</a></li>
	            <li><a href="logout.do"><span class="glyphicon glyphicon-log-out"></span>Logout</a></li>
	         </ul>
         </c:if>
      </div>
   </nav>

</body>
</html>