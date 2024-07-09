<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="top.jsp" flush="true" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Main Page</title>
</head>
<script type="text/javascript">
    $(document).ready(function() {
        $("#insBtn").click(function() {
            location.href = "${path}/test/ckEditor.do";
        });
    });
</script>
<body>
	<!-- 게시판 -->
	<div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
		<h2>뉴스레터</h2>
		<p>CK 에디터</p>
		<button type="button" class="btn btn-primary" id="insBtn" style="margin-left : 90%; margin-bottom : 1%;">
		글 등록하기</button>
		<table class="table table-hover" style="width: 100%;">
			<thead>
				<tr>
					<th>뉴스레터 번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>작성일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="newsletter" items="${nlist}" varStatus="sts">
					<tr ondblclick="goDetail('${newsletter.news_id}')">
						<td>${sts.index + 1}</td>
						<td>${newsletter.title}</td>
						<td>${newsletter.writter}</td>
						<td>${newsletter.reg_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script type="text/javascript">
function goDetail(news_id){
	location.href = '${path}/test/news.do?news_id='+news_id;
}
</script>

</body>
</html>
