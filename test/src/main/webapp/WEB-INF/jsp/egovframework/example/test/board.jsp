<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<c:set var="path" value="${pageContext.request.contextPath }" />
<fmt:requestEncoding value="utf-8" />
<!DOCTYPE html>
<html lang="en">
<head>
<title>테스트 페이지</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<style>
.fakeimg {
	height: 200px;
	background: #aaa;
}
</style>
</head>
<script type="text/javascript">
	$(document).ready({
		
	})
</script>
<body>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>My First Bootstrap 4 Page</h1>
		<p>Resize this responsive page to see the effect!</p>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="${path}/main.do">MAIN</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link"
					href="${path}/board.do">게시판</a></li>
				<li class="nav-item"><a class="nav-link" href="#">뉴스레터</a></li>
				<li class="nav-item"><a class="nav-link" href="#">메일</a></li>
			</ul>
		</div>
	</nav>
	<!-- 게시판 -->
	<div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
		<h2>게시판</h2>
		<p>파일 업로드 & 다운로드</p>
		<table class="table table-hover" style="width: 100%;">
			<thead>
				<tr>
					<th>게시글번호</th>
					<th>제목</th>
					<th>내용</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="board" items="${blist}" varStatus="sts">
					<tr ondblclick="goDetail('${board.board_id}')">
						<td>${sts.index + 1}</td>
						<td>${board.title}</td>
						<td>${board.detail}</td>
						<td>${board.writter}</td>
						<td>${board.reg_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script type="text/javascript">
function goDetail(board_id){
	location.href = '${path}/boardDetail.do?board_id='+board_id;
}
</script>

</body>
</html>
