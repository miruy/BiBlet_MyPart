<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보관함</title>
</head>
<body>
	
	총 몇개인지도 추가하기
	
	<c:if test="${!empty MyComment}">
		<p>
			<c:forEach var="myComment" items="${MyComment}">
				별점 : ${myComment.star}
				평가 : ${myComment.book_comment}
				시작 날짜 : ${myComment.start_date}
				다 읽은 날짜 : ${myComment.end_date}
			</c:forEach>
		</p>	
	</c:if>
	
	eh
	
</body>
</html>