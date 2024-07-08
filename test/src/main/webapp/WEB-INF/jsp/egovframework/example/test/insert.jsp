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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
</head>
<body>
    <!-- 게시판 입력 -->
    <div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
        <h2>게시물 등록</h2>
        <form id="frm" method="post" action="${path}/test/insertBoard.do" enctype="multipart/form-data">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" name="title">
            </div>
            <div class="form-group">
                <label for="writter">작성자</label>
                <input type="text" class="form-control" id="writter" name="writter">
            </div>
            <div class="form-group">
                <label for="detail">내용</label>
                <textarea class="form-control" rows="5" id="detail" name="detail"></textarea>
            </div>
            <div class="form-group">
                <label for="files">파일 첨부</label>
                <input type="file" class="form-control" id="files" name="files" multiple>
            </div>
        </form>
        <button type="button" class="btn btn-primary" id="insBtn" style="margin-top: 2%;">등록하기</button>
    </div>
</body>
</html>
