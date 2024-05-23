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
      if(${not empty msgType}){
         if(${msgType eq "실패 메세지"}){ // msgType "실패 메세지"가 아닐 수도있기 때문
            $("#messageType").attr("class", "modal-content panel-warning");
         }
         $("#myMessage").modal("show");
      }
   });

   
</script>

</head>
<body>

   <div class="container">

      <jsp:include page="../common/header.jsp" />
      <h2>Panel Heading</h2>
      <div class="panel panel-default">
         <div class="panel-heading">프로필 사진등록</div>
         <div class="panel-body">
			<!-- enctype  아래와 같은 타입을 MIME타입.
				 1). application/x-www-form-urlencoded : 폼테그 안에서 보내는 요청데이터를 name값과 value값의 쌍으로 보냄.
				 2). multipart/form-data : 사진파일과 같이 용량이 큰 데이터를 보낼때 사용. 
				     -> 무조건 POST로 보내야함.!! get방식으로 못보냄!. body부분에 담아서 보내야함.
			 -->
            <form action="imageUpdate.do" method="post" enctype="multipart/form-data">
               <table class="table table-bordered"
                  style="border: 1px solid #dddddd; text-align: center;">

                  <tr>
                     <td style="vertical-align: middle; width: 110px;">아이디</td>
                     <td>
                     	<!-- el문법으로 mem객체 가져오자. 자바코드가 아니라 getter로 가져오지 않고 필드 이름으로 가져온다. -->
                     	${mem.memId}
                     </td>
                  </tr>
                  
                  <tr>
                     <td style="vertical-align: middle; width: 110px;">사진업로드</td>
                     <td>
                       <span class="btn btn-default"> 
                         이미지 업로드 하세요.
                         <input type="file" name="memProfile">
                       </span>
                     </td>
                  </tr>
                  <tr>
                     <td colspan="2">
                        <input type="submit" class="btn btn-sm btn-primary" value="업로드">
                        <input type="reset" class="btn btn-sm btn-warning" value="초기화">
                     </td>
                  </tr>                  
               </table>

            </form>

         </div>
         <div class="panel-footer">스프링 - 이주희</div>
      </div>
   </div>

   <!-- 회원가입 실패 시 띄울 모달창 -->
   <!-- Modal -->
   <div class="modal fade" id="myMessage" role="dialog">
      <div class="modal-dialog">

         <!-- Modal content-->
         <div id="messageType" class="modal-content panel-info">
            <div class="modal-header panel-heading">
               <button type="button" class="close" data-dismiss="modal">&times;</button>
               <h4 class="modal-title">${msgType }</h4>
            </div>
            <div class="modal-body">
               <p id="">${msg }</p>
            </div>
            <div class="modal-footer">
               <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
         </div>

      </div>
   </div>

   <!-- 아이디 중복체크를 위한 모달창 -->
   <!-- Modal -->
   <div class="modal fade" id="myModal" role="dialog">
      <div class="modal-dialog">

         <!-- Modal content-->
         <div id="checkType" class="modal-content panel-info">
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


</body>
</html>