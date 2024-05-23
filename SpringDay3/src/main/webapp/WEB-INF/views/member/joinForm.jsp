<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Bootstrap Example</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
   src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
   
<script type="text/javascript">

	$(document).ready(function(){ 
		// 어차피 실패 메세지 일꺼다.
		if(${not empty msgType}){
			if(${msgType eq "실패 메세지"}){ //  eq == 똑같다. msgType "실패 메세지"가 아닐수도 있기 때문.
				$("#messageType").attr("class", "modal-content panel panel-warning");
			}
			$("#myMessage").modal("show");
		}
		
	});

		
	function idCheck(){
		// 아이디 중복체크
		var memId = $("#memId").val();
		// console.log(memId);
	
		$.ajax({
			url: "idCheck.do",
			type: "get",
			data: {"memId": memId },
			success: function(data){
				// db에 있다면 == 1 : 사용가능 or data == 0 : 사용 불가능
				console.log(data);
				if(data == 1){ 
					$("#checkMessage").text("사용가능한 아이디 입니다."); 
					$("#checkType").attr("class", "modal-content panel panel-success")
				}
				else {
					$("#checkMessage").text("사용할수 없는 아이디 입니다."); 
					$("#checkMessage").css("color","red"); 
					$("#checkType").attr("class", "modal-content panel panel-warning")
				}
				
				$("#myModal").modal("show");
				
			},
			error: function(){alert("error");}
		});
		
	}
	
	
	// 패스워드 체크
	function passwordCheck(){
		let memPassword1 = $("#memPassword1").val();
		let memPassword2 = $("#memPassword2").val();
		
		if(memPassword1 != memPassword2){
			$("#passCheck").text("비밀번호가 일치하지 않습니다.");
			$("#passCheck").css("color","red");
		}else { // 일치할경우
			$("#passCheck").text("비밀번호가 일치합니다.");
			$("#passCheck").css("color","green");
			$("#memPassword").val(memPassword2);			
		}
	}
	
	
	


</script>   
   
</head>
<body>

   <div class="container">
   	 <jsp:include page="../common/header.jsp" />	
      <h2>Sign-Up</h2>
      <div class="panel panel-default">
         <div class="panel-heading">회원가입</div>
         <div class="panel-body">
	        <form action="join.do" method="post" >
	          <input type="hidden" id="memPassword" name="memPassword" value="">	
	          <table class="table table-bordered" style="border:1px solid #dddddd; text-align: center; border-radius: 20px;">
	          	<tr>
	          		<td style="vertical-align: middle; width: 110px;">아이디</td>
	          		<td><input class="form-control" type="text" name="memId" id="memId" placeholder="아이디를 입력하세요~!" maxlength="20" /></td>
	          		<td style="vertical-align: middle; width: 110px;">
	          	  	  <button type="button" class="btn btn-sm btn-primary" onclick="idCheck()">아이디 중복검사</button>
	          		</td>
	          	</tr>
	          	<tr>
	          		<td style="vertical-align: middle; width: 110px;">비밀번호</td>
	          		<td colspan="2"><input class="form-control" type="password"  name="memPassword1" id="memPassword1" maxlength="20" placeholder="비밀번호를 입력하세요~!"/></td>
	          	</tr>	
	          	<tr>
	          		<td style="vertical-align: middle; width: 110px;">비밀번호 확인</td>
	          		<td colspan="2"><input class="form-control" type="password" onkeyup="passwordCheck()" name="memPassword2" id="memPassword2" maxlength="20" placeholder="비밀번호를 다시한번 입력하세요~!"/></td>
	          	</tr>	
	          	<tr>
	          		<td style="vertical-align: middle; width: 110px;">User 이름</td>
	          		<td colspan="2"><input class="form-control" type="text" name="memName" id="memName" placeholder="이름을 입력하세요~!" maxlength="20"/></td>
	          	</tr>
	          	<tr>
	          		<td style="vertical-align: middle; width: 110px;">나이</td>
	          		<td colspan="2"><input class="form-control" type="number" name="memAge" id="memAge" 
	          		     placeholder="나이를 선택하세요." min=1 max= 100/></td>
	          	</tr>
	          	
	          	<tr>
	          		<td style="vertical-align: middle; width: 110px;">성별</td>
	          		<td colspan="2">
	          			<div class="form-group" style="text-align: center; margin: 0 auto; ">
	          				<div class="btn-group" data-toggle="buttons">
	          					<label class="btn btn-primary active">
	          						<input type="radio" id="memGender" name="memGender" 
	          						   autocomplete="off" value="남자" checked="checked">남자
	          					</label>
	          					<label class="btn btn-primary">
	          						<input type="radio" id="memGender" name="memGender" 
	          						   autocomplete="off" value="여자" >여자
	          					</label>
	          				</div>
	          			</div> 
	          		</td>
	          	</tr>
	          	
          		<tr>
	          		<td style="vertical-align: middle; width: 110px;">이메일</td>
	          		<td colspan="2"><input class="form-control" type="email" name="memEmail" id="memEmail" 
	          		     placeholder="이메일을 입력 하세요." maxlength="50"/></td>
	          	</tr>
          		<tr>
	          		<td colspan="3">
	          		  <span id="passCheck" style="color:red;"></span>	
	          		  <input type="submit" class="btn btn-sm btn-primary" value="회원가입">			          		  <input type="reset" class="btn btn-sm btn-waring" value="초기화">		          		</td>
	          	</tr>
	          	
	          </table>
	        </form>
         
         </div>
         <div class="panel-footer">스프링 - 오진희</div>
      </div>
   </div>
      
   
   <!-- 회원가입 실패시 띄울 모달창 -->
   <!-- Modal -->
	  <div class="modal fade" id="myMessage" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div id="messageType" class="modal-content panel panel-info">
	        <div class="modal-header panel-heading">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">${msgType}</h4> <!-- 실패시만 -->
	        </div>
	        <div class="modal-body">
	          <p id="">${msg}</p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
	  
   
    <!-- 아이디 중복 검사시 띄울 모달창. -->
    <!-- Modal -->
	  <div class="modal fade" id="myModal" role="dialog">
	    <div class="modal-dialog">
	    
	      <!-- Modal content-->
	      <div id="checkType" class="modal-content panel panel-info">
	        <div class="modal-header panel-heading">
	          <button type="button" class="close" data-dismiss="modal">&times;</button>
	          <h4 class="modal-title">메세지 확인</h4>
	        </div>
	        <div class="modal-body">
	          <p id="checkMessage"></p>
	        </div>
	        <div class="modal-footer">
	          <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
	        </div>
	      </div>
	      
	    </div>
	  </div>
	  
	  
	  <script type="text/javascript">
	  	
	  </script>

</body>
</html>