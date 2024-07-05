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
<body>

	<div class="jumbotron text-center" style="margin-bottom: 0">
		<h1>My First Bootstrap 4 Page</h1>
		<p>Resize this responsive page to see the effect!</p>
	</div>

	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<a class="navbar-brand" href="${path}/main.do">메인</a>
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
	<!-- 게시판 상세 -->
	<div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
		<h2>게시물 상세보기</h2>
		<form >
			<div class="form-group">
				<label for="title">제목</label> 
				<input type="text" class="form-control" id="title" value="${detail.title}"
					name="title">
			</div>
			
			<div class="form-group">
				<label for="writter">작성자</label> <input type="text"
					class="form-control" id="writter" value="${detail.writter}"
					name="writter">
			</div>
			
			<div class="form-group">
				<label for="reg_date">등록일</label> <input type="text" value="${detail.reg_date}"
					class="form-control" id="reg_date" placeholder="Enter password"
					name="reg_date">
			</div>
			
			<div class="form-group">
			  <label for="detail">내용</label>
			  <textarea class="form-control" rows="5" id="detail" name="detail">${detail.detail}</textarea>
			</div>
			
			<button type="submit" class="btn btn-primary">수정하기</button>
			<button type="submit" class="btn btn-warning">삭제하기</button>
		</form>
	</div>


</body>
</html>
