<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
      var title = $("#title").val();
      var writter = $("#writter").val();
      var detail = $("#detail").val();

      if (title !== "" && writter !== "" && detail !== "") {
        alert("등록되었습니다.");
        $("#frm").submit();
      } else {
        alert("모든 필드를 채워주세요.");
      }
    });
  });
</script>

<body>
	<!-- 게시판 입력 -->
	<div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
		<h2>게시물 상세보기</h2>
		<form id="frm" method="post" action = "${path}/test/insertBoard.do">
			<div class="form-group">
				<label for="title">제목</label> <input type="text"
					class="form-control" id="title" value="" name="title">
			</div>

			<div class="form-group">
				<label for="writter">작성자</label> <input type="text"
					class="form-control" id="writter" value="" name="writter">
			</div>


			<div class="form-group">
				<label for="detail">내용</label>
				<textarea class="form-control" rows="5" id="detail" name="detail">${detail.detail}</textarea>
			</div>

			<button type="button" class="btn btn-primary" id="insBtn">등록하기</button>
		</form>
	</div>


</body>
</html>
