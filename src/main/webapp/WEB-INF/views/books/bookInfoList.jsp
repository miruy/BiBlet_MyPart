<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>BiBlet 도서 전체 목록</title>
</head>
<body>
	 <c:if test="${!empty books}">
		<table border="1">
			<tr>
				<th>일련번호</th>
				<th>도서 이름</th>
				<th>출판사</th>
				<th>저자</th>
				<th>제작년도</th>
				<th>총 페이지</th>
				<th>카테고리</th>
				<th>연령등급</th>
				<th>줄거리</th>
				<th>표지</th>	
			</tr>
			
			<c:forEach var="books" items="${books}">
				<tr>
					<td>${books.isbn}</td>
					<td>${books.book_name}</td>
					<td>${books.publisher}</td>
					<td>${books.author}</td>
					<td>${books.produc_year}</td>
					<td>${books.book_page}</td>
					<td>${books.book_category}</td>
					<td>${books.age_grade}</td>
					<td>${books.book_sum}</td>
					<td>${books.book_cover}</td>
				</tr>
			</c:forEach>
		</table>	
	</c:if>
	
	
	
	
</body>
</html>