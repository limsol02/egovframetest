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
		<h2>메일</h2>
		<p>보낸메일함</p>
		<button type="button" class="btn btn-primary" id="insBtn" style="margin-left : 90%; margin-bottom : 1%;">
		메일 보내기</button>
		<table class="table table-hover" style="width: 100%;">
			<thead>
				<tr>
					<th>메일 번호</th>
					<th>제목</th>
					<th>받는 사람</th>
					<th>발송일</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="mail" items="${mlist}" varStatus="sts">
					<tr ondblclick="goDetail('${mail.mail_id}')">
						<td>${sts.index + 1}</td>
						<td>${mail.title}</td>
						<td>${mail.receiver}</td>
						<td>${mail.send_date}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
<script type="text/javascript">
function goDetail(mail_id){
	location.href = '${path}/test/mailDetail.do?mail_id='+mail_id;
}
</script>

</body>
</html>
