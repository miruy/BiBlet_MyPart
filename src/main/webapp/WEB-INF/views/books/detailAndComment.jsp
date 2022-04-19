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


	<form method="post" action="/read?actionFlag=1" commandName="insertCmd">
		<p>
			독서 상태 : <select name="option">
				<option value="none">=== 선택 ===</option>
				<option value=0>찜</option>
				<option value=1>보는 중</option>
				<option value=2>독서 완료</option>
			</select> * 평가 작성은 독서 완료 시 가능합니다. 
		</p>
		
		
			별점 : 
			<div class="star-rating space-x-4 mx-auto">
				<input type="radio" id="1star" name="star" value=5 v-model="ratings" />1점(지울 예정) 
				<input type="radio" id="2star" name="star" value=4 v-model="ratings" />2점(지울 예정)
				<input type="radio" id="3star" name="star" value=3 v-model="ratings" />3점(지울 예정)
				<input type="radio" id="4star" name="star" value=2 v-model="ratings" />4점(지울 예정) 
				<input type="radio" id="5star" name="star" value=1 v-model="ratings" />5점(지울 예정)
			</div>
		
		
		<p>
			평가 : <textarea name="book_comment"></textarea>
		</p>


			구독 시작 날짜 : <input type="date" name="start_date" /> 
			구독 완료 날짜 : <input type="date" name="end_date" />
			공개 : <input type="checkbox" name="co_prv" value="공개" />
			비공개 : <input type="checkbox" name="co_prv" value="비공개" />
			<input type="hidden" name="isbn" id="isbn" value="${isbn}" />
			<input type="hidden" name="query" id="query" value="${query}" /> 
			<input type="submit" value="도서 평가 등록">
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
					상태번호 : ${commentsByMember.book_status_num}
				</p>					
	
				<form method="post"  action="/read?actionFlag=2" commandName="deleteCmd">
					<input type="hidden" name="isbn" id="isbn" value="${isbn}" /> 
					<input type="hidden" name="query" id="query" value="${query}" /> 
					<input type="hidden" name="appraisal_num" id="appraisal_num" value="${commentsByMember.appraisal_num}" />
					<input type="submit" value="삭제" >
				</form>
				
				<input type='button' value='수정' onclick='updateComment(${commentsByMember.appraisal_num})'/>
								
				<form method="post"  action="/read?actionFlag=3" commandName="updateCmd">
					<div id="${commentsByMember.appraisal_num}" style="display:none;">
							독서 상태 : 
								<select name="option">
									<option value="none">=== 선택 ===</option>
									<option value=0>찜</option>
									<option value=1>보는 중</option>
									<option value=2>독서 완료</option>
								</select> * 평가 작성은 독서 완료 시 가능합니다. 
								
								별점 : 
								<input type="radio" id="1star" name="star" value=5 v-model="ratings" />1점(지울 예정) 
								<input type="radio" id="2star" name="star" value=4 v-model="ratings" />2점(지울 예정)
								<input type="radio" id="3star" name="star" value=3 v-model="ratings" />3점(지울 예정)
								<input type="radio" id="4star" name="star" value=2 v-model="ratings" />4점(지울 예정) 
								<input type="radio" id="5star" name="star" value=1 v-model="ratings" />5점(지울 예정)
					
				
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
				</form>
			</c:forEach>
		</c:if>
	
		<script>
			function updateComment(updateForm) {
				 $("#"+updateForm).toggle();
			} 
		</script>
		
	<script>
	
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
	                    $("div").append("<h2><a href='${pageContext.request.contextPath}/AppraisalPage/read/"+ msg.documents[i].isbn.slice(0, 10)+"?query="+$("#query").val()+ "'>" + msg.documents[i].title + "</a></h2>");	//제목
	                    $("div").append("<strong>저자:</strong> " + msg.documents[i].authors + "<br>");		//저자	
	                    $("div").append("<strong>출판사:</strong> " + msg.documents[i].publisher + "<br>");		//출판사
	                    $("div").append("<strong>줄거리:</strong> " + msg.documents[i].contents + "...<br>");		//줄거리
	                	$("div").append("<strong>일련번호:</strong>" + msg.documents[i].isbn + "<br>");	//일련번호
	                }
	            });
	        }) 
	       
            
	        
	        
	  		
//     	상세페이지 실행하자마자 도서 데이터 요청
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
      		 
				  
 	 </script>

</body>
</html>











