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
  	<!-- 도서 검색 -->
    <script>
        $(document).ready(function () {
            var pageNum = 1;
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
                        $("div").append("<h2><a href='${pageContext.request.contextPath}/AppraisalPage/read/"+ msg.documents[i].isbn.slice(0, 10)+"'>" + msg.documents[i].title + "</a></h2>");	//제목
                        $("div").append("<strong>저자:</strong> " + msg.documents[i].authors + "<br>");		//저자	
                        $("div").append("<strong>출판사:</strong> " + msg.documents[i].publisher + "<br>");		//출판사
                        $("div").append("<strong>줄거리:</strong> " + msg.documents[i].contents + "...<br>");		//줄거리
                    	$("div").append("<strong>일련번호:</strong>" + msg.documents[i].isbn + "<br>");	//일련번호
                    }
                });
           
        })    
 	 </script>
</head>
<body>
	<form method="get" action="/BiBlet/AppraisalPage/list">
			<p>
			검색 키워드 입력 : 
			<select name="option">
				<option value="title">제목</option>
				<option value="author">저자</option>
				<option value="publisher">출판사</option>
			</select> 
			
			<input type="text" name="query" id="query" value="${query}" placeholder="제목, 저자 또는 출판사 검색" size=30> 
			<button id="search">검색</button>
 			</p>
	</form>
	
	 <div>
		 <img src="${msg.documents[i].thumbnail}"/><br> 
	 </div>


	<br>

	<form method="post">
		<table border="1">
			<tr>
				<th>별점</th>
				<td>
					<div class="star-rating space-x-4 mx-auto">
						<input type="radio" id="1star" name="star" value=5 v-model="ratings" />1점(지울 예정) 
						<input type="radio" id="2star" name="star" value=4 v-model="ratings" />2점(지울 예정) 
						<input type="radio" id="3star" name="star" value=3 v-model="ratings" />3점(지울 예정) 
						<input type="radio" id="4star" name="star" value=2 v-model="ratings" />4점(지울 예정) 
						<input type="radio" id="5star" name="star" value=1 v-model="ratings" />5점(지울 예정)
					</div>
				</td>
			</tr>

			<tr>
				<th>평가</th>
				<td><textarea name="book_comment"></textarea></td>
			</tr>

			<tr>
				<th>구독 시작 날짜</th>
				<td><input type="date" name="start_date" /></td>
			</tr>
			<tr>
				<th>구독 완료 날짜</th>
				<td><input type="date" name="end_date" /></td>
			</tr>

			<tr>
				<th>평가 공개 여부</th>
				<td><input type="checkbox" name="co_prv" value="공개" />공개 <input
					type="checkbox" name="co_prv" value="비공개" />비공개</td>
			</tr>
		</table>
		<input type="submit" value="도서 평가 등록">
	</form>
	
	<br>
	
<%-- 	<c:if test="${!empty commentCount}"> --%>
<%-- 		평가 총 개수 : ${commentCount}	 --%>
<%-- 	</c:if> --%>
	
<!-- 	<br> -->
	
<%-- 	<c:if test="${!empty commentsByMembers}"> --%>
<%-- 		<c:forEach var="commentsByMember" items="${commentsByMembers}"> --%>
<!-- 			<p> -->
<%-- 			평가 번호 : ${commentsByMember.appraisal_num} --%>
<%-- 			회원 : ${commentsByMember.mem_id} --%>
<%-- 			프로필 : ${commentsByMember.mem_pic} --%>
<%-- 			별점 : ${commentsByMember.star} --%>
<%-- 			시작날짜 : ${commentsByMember.start_date} --%>
<%-- 			다 읽은 날짜 : ${commentsByMember.end_date} --%>
<%-- 			평가 : ${commentsByMember.book_comment} --%>
<!-- 			</p> -->
<%-- 		</c:forEach> --%>
<%-- 	</c:if> --%>


</body>
</html>











