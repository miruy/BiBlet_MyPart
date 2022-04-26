<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BiBlet 도서 상세/평가</title>
 <style>
 .star-rating {
  border:solid 1px #ccc;
  display:flex;
  flex-direction: row-reverse;
  font-size:1.5em;
  justify-content:space-around;
  padding:0 .2em;
  text-align:center;
  width:5em;
}

.star-rating input {
  display:none;
}

.star-rating label {
  color:#ccc;
  cursor:pointer;
}

.star-rating :checked ~ label {
  color:#f90;
}

.star-rating label:hover,
.star-rating label:hover ~ label {
  color:#fc0;
}

/* explanation */

article {
  background-color:#ffe;
  box-shadow:0 0 1em 1px rgba(0,0,0,.25);
  color:#006;
  font-family:cursive;
  font-style:italic;
  margin:4em;
  max-width:30em;
  padding:2em;
}
 </style>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous"></script>
</head>
<body>
	<form method="get" action="../list">
		<p>
			검색 키워드 입력 : <select name="keyword">
				<option value="title">제목</option>
				<option value="author">저자</option>
				<option value="publisher">출판사</option>
			</select> 
			<input type="text" name="query" id="query" value="${query}" placeholder="제목, 저자 또는 출판사 검색" size=30>
			<button id="search">검색</button>
		</p>
	</form>
	
	<div id="bookInfo"></div>

	<form onsubmit="return bookSubmit()">
			<p>
				독서 상태 : <select id="option" name="option" onChange="bookStatus()">
					<option value="none">=== 선택 ===</option>
					<option value=0>찜</option>
					<option value=1>보는 중</option>
					<option value=2>독서 완료</option>
				</select> 
				
			</p>
				 
		 	별점 :	<div class="star-rating">
						   <input type="radio" id="5-star" name="star" value=5 />
						   <label for="5-stars" class="star">&#9733;</label>
						   <input type="radio" id="4-stars" name="star" value=4 />
						   <label for="4-stars" class="star">&#9733;</label>
						   <input type="radio" id="3-stars" name="star" value=3 />
						   <label for="3-stars" class="star">&#9733;</label>
						   <input type="radio" id="2-stars" name="star" value=2 />
						   <label for="2-stars" class="star">&#9733;</label>
						   <input type="radio" id="1-star" name="star" value=1 />
						   <label for="1-star" class="star">&#9733;</label>
					</div>
			
		<p>
			평가 : <textarea id="book_comment" name="book_comment"></textarea>
		</p>


			구독 시작 날짜 : <input type="date" id="start_date" name="start_date" /> 
			구독 완료 날짜 : <input type="date" id="end_date"  name="end_date"/>
			공개 : <input type="checkbox" id="co_prv" name="co_prv" value="공개" />
			비공개 : <input type="checkbox" id="co_prv" name="co_prv" value="비공개" />
			<input type="hidden" name="isbn" id="isbn" value="${isbn}" />
			<input type="button" value="등록" id="writeComment" onClick="writeBtn()" />
			<span id="msg"></span>
	</form>

	
	<p>
		<c:if test="${!empty commentCount}">
			평가 총 개수 : ${commentCount}	
		</c:if>
	</p>
	
		<c:if test="${!empty commentsByMembers}">
			<c:forEach var="commentsByMember" items="${commentsByMembers}">
				<p>
					평가 번호 : ${commentsByMember.appraisal_num} 
					회원 : ${commentsByMember.mem_id} 
					프로필 : ${commentsByMember.mem_pic} 
					별점 : ${commentsByMember.star} 
					시작날짜 : ${commentsByMember.start_date} 
					다 읽은 날짜 : ${commentsByMember.end_date} 
					평가 : ${commentsByMember.book_comment}
					비밀번호 : ${commentsByMember.mem_pass}
				</p>					
				
				<input type="button" value="삭제" onclick='deleteBtn(${commentsByMember.appraisal_num})'>
				<input type='button' value='수정' onclick='updateBtn(${commentsByMember.appraisal_num})'/>
				
					<input type="hidden" name="isbn" id="isbn" value="${isbn}" /> 
					<input type="hidden" name="query" id="query" value="${query}" /> 
					<input type="hidden" name="appraisal_num" id="appraisal_num" value="${commentsByMember.appraisal_num}" />
					<input type="hidden" name="mem_pass" id="mem_pass" value="${commentsByMember.mem_pass}" />
		
				
				<div id="pd${commentsByMember.appraisal_num}" style="display:none;">
						 비밀번호 입력 : 
						<input type="password" name="passCheck" id="passCheck" />
						<input type="button" value="확인" id="passCheckBtnD" onClick="passCheckAndDelete(${commentsByMember.appraisal_num})"/>
				</div>
				
				<div id="pu${commentsByMember.appraisal_num}" style="display:none;">
						 비밀번호 입력 : 
						<input type="password" name="passCheck" id="passCheck" />
						<input type="button" value="확인" id="passCheckBtnU" onClick="passCheckAndUpdate(${commentsByMember.appraisal_num})"/>
				</div>
			
					<form:form method="post" action="/read?actionFlag=4" commandName="updateCmd"  name="updateForm" id="updateForm">
						<div id="u${commentsByMember.appraisal_num}" style="display:none;">
								독서 상태 : 
									<select id="option" name="option">
										<option value="none">=== 선택 ===</option>
										<option value=0>찜</option>
										<option value=1>보는 중</option>
										<option value=2>독서 완료</option>
									</select> * 평가 작성은 독서 완료 시 가능합니다. 
									
							별점 : 		<div class="star-rating">
										  <input type="radio" id="5-stars" name="star" value=5 />
										  <label for="5-stars" class="star">&#9733;</label>
										  <input type="radio" id="4-stars" name="star" value=4 />
										  <label for="4-stars" class="star">&#9733;</label>
										  <input type="radio" id="3-stars" name="star" value=3 />
										  <label for="3-stars" class="star">&#9733;</label>
										  <input type="radio" id="2-stars" name="star" value=2 />
										  <label for="2-stars" class="star">&#9733;</label>
										  <input type="radio" id="1-star" name="star" value=1 />
										  <label for="1-star" class="star">&#9733;</label>
										</div>
						
					
									평가 : <textarea name="book_comment"></textarea>
					
									구독 시작 날짜 : <input type="date" name="start_date" /> 
									구독 완료 날짜 : <input type="date" name="end_date" />
									공개 : <input type="checkbox" name="co_prv" value="공개" />
									비공개 : <input type="checkbox" name="co_prv" value="비공개" />
									<input type="hidden" name="isbn" id="isbn" value="${isbn}" /> 
									<input type="hidden" name="query" id="query" value="${query}" /> 
									<input type="hidden" name="appraisal_num" id="appraisal_num" value="${commentsByMember.appraisal_num}" />
									<input type="hidden" name="book_status_num" id="book_status_num" value="${commentsByMember.book_status_num}" />
									
									<input type="submit" value="저장">
						</div>	
					</form:form>
			</c:forEach>
		</c:if>
		
		<script>
		
//		평가 삭제를 위한 비밀번호 입력 폼 
		function deleteBtn(appraisal_num) {
			$("#pd"+appraisal_num).toggle();
		}
		
//		비밀번호 확인 및 평가 삭제	
		function passCheckAndDelete(appraisal_num){
			
			let isbn = $("#isbn").val();
			let query = $("#query").val();
			let passCheck = $("#passCheck").val();
			let mem_pass = $("#mem_pass").val();
			
			$.ajax({
				url: '<c:url value="/passCheck"/>',
				type: 'POST',
				data: JSON.stringify({
					"appraisal_num": appraisal_num,
					"passCheck": passCheck,
					"mem_pass": mem_pass,
					"isbn": isbn,
					"query": query
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function(data) {
					if(data == 1){
					 	alert("비밀번호 일치");
					 	deleteComment(appraisal_num);
					 	
					 	//비밀번호 입력 폼 사라지기
					 	$("#pd"+appraisal_num).hide();
					}else if(data == 0){
						alert("비밀번호 불일치");
						
						//비밀번호 입력 폼 사라지기
						$("#pd"+appraisal_num).hide();
					}
				}
			});
		}

// 		평가 삭제 요청		
		function deleteComment(appraisal_num){
			$.ajax({
				url: '<c:url value="/delete"/>',
				type: 'POST',
				data: JSON.stringify({
					"appraisal_num": appraisal_num
				}),
				dataType: "json",
				contentType: 'application/json',
				success: function(data) {
					location.reload(); 
				}
			});
		}
			
//			평가 수정을 위한 비밀번호 입력 폼 
			function updateBtn(appraisal_num) {
				$("#pu"+appraisal_num).toggle();
			}		  
	
		
//			비밀번호 확인 및 평가 수정
			function passCheckAndUpdate(appraisal_num){
				
				let isbn = $("#isbn").val();
				let query = $("#query").val();
				let passCheck = $("#passCheckU").val();
				let mem_pass = $("#mem_pass").val();
				
				$.ajax({
					url: '<c:url value="/passCheck"/>',
					type: 'POST',
					data: JSON.stringify({
						"appraisal_num": appraisal_num,
						"passCheck": passCheck,
						"mem_pass": mem_pass,
						"isbn": isbn,
						"query": query
					}),
					dataType: "json",
					contentType: 'application/json',
					success: function(data) {
						if(data == 1){
						 	alert("비밀번호 일치");
						 	
						 	updateForm(appraisal_num);
						 	
						 	//비밀번호 입력 폼 사라지기
						 	$("#pu"+appraisal_num).hide();
						}else if(data == 0){
							alert("비밀번호 불일치");
							
							//비밀번호 입력 폼 사라지기
							$("#pu"+appraisal_num).hide();
						}
					}
				});
				
			}	

// 			평가 수정 폼 보여주기
			function updateForm(appraisal_num) {
				 $("#u"+appraisal_num).toggle();
			}


// 			평가 작성	
			function writeBtn(){

				let isbn = $("#isbn").val();
				let option = $("#option").val();
				let star = $("input[name=star]").val();
				let book_comment = $("#book_comment").val();
				let start_date = $("#start_date").val();
				let end_date = $("#end_date").val();
				let co_prv = $("#co_prv").val();
				
				$.ajax({
					url: '<c:url value="/write"/>',
					type: 'POST',
					data: JSON.stringify({
						"isbn": isbn,
						"option": option,
						"star": star,
						"book_comment": book_comment,
						"start_date": start_date,
						"end_date": end_date,
						"co_prv": co_prv
					}),
					dataType: "json",
					contentType: 'application/json',
					success: function(data) {
						location.reload(true);
					}
				});
			}


	
// 		도서 검색 버튼 클릭 시 도서 데이터 요청
	   	 $(document).ready(function () {
	        var pageNum = 1;
	       $("#search").click(function () {	//검색 버튼 클릭시 ajax실행
	        	$.ajax({	//카카오 검색요청 / [요청]
	                method: "GET",
	                url: "https://dapi.kakao.com/v3/search/book",
	                data: { query: $("#query").val(), page: pageNum},
	                headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
	            })
	           
	            .done(function (msg) {	//검색 결과 담기 / [응답]
	            	console.log(msg);
	                for (var i = 0; i < 10; i++){
	                    $("div").append("<img src='" + msg.documents[i].thumbnail + "'/><br>");		//표지
	                    $("div").append("<h2><a href='${pageContext.request.contextPath}/read/"+ msg.documents[i].isbn.slice(0, 10)+"?query="+$("#query").val()+ "'>" + msg.documents[i].title + "</a></h2>");	//제목
	                    $("div").append("<strong>저자:</strong> " + msg.documents[i].authors + "<br>");		//저자	
	                    $("div").append("<strong>출판사:</strong> " + msg.documents[i].publisher + "<br>");		//출판사
	                    $("div").append("<strong>줄거리:</strong> " + msg.documents[i].contents + "...<br>");		//줄거리
	                	$("div").append("<strong>일련번호:</strong>" + msg.documents[i].isbn + "<br>");	//일련번호
	                }
	            });
	        }) 
	       
	  		
//     		 상세페이지 실행하자마자 도서 데이터 요청
             var pageNum = 1;
            	$.ajax({	//카카오 검색요청 / [요청]
                    method: "GET",
                    url: "https://dapi.kakao.com/v3/search/book",
                    data: { query: $("#query").val(), page: pageNum},
                    headers: {Authorization: "KakaoAK 6f9ab74953bbcacc4423564a74af264e"} 
                })
               
                .done(function (msg) {	//검색 결과 담기 / [응답]
                	console.log(msg);
                        $("#bookInfo").append("<img src='" + msg.documents[0].thumbnail + "'/><br>");		//표지
                        $("#bookInfo").append("<h2>"+ msg.documents[0].title + "</h2>");	//제목
                        $("#bookInfo").append("<strong>저자:</strong> " + msg.documents[0].authors + "<br>");		//저자	
                        $("#bookInfo").append("<strong>출판사:</strong> " + msg.documents[0].publisher + "<br>");		//출판사
                        $("#bookInfo").append("<strong>줄거리:</strong> " + msg.documents[0].contents + "...<br>");		//줄거리
                        $("#bookInfo").append("<strong>제작년도:</strong> " + msg.documents[0].datetime.slice(0,10) + "<br>");		//일련번호
                        $("#bookInfo").append("<strong>ISBN:</strong> " + msg.documents[0].isbn.slice(0,10) + "<br>");		//일련번호
                        $("#isbn").val(msg.documents[0].isbn.slice(0,10));
                });   
            
            	
      		 
      		  })  
      		
//       	'독서 완료'시 평가 작성 가능 
      		let submitFlag = false;
      		
      		let bookStatus = function(){
      			let select = document.getElementById("option");
      			let selectValue = select.options[document.getElementById("option").selectedIndex].value;
      			if(selectValue == 2){
      				submitFlag = true;
      			}else{
      				submitFlag = false;
      			}
      			console.log("flag : " + submitFlag);
      		}
			
      		let bookSubmit = function(){
      			let msg = document.getElementById("msg");
      			if(!submitFlag){
      				
      				msg.innerHTML = "독서 완료 시에만 평가 작성이 가능합니다.";
      			}
      			return submitFlag;
      		}
      		
    

			
 	 </script>




</body>
</html>











