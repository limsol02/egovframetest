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
<script type="text/javascript">
    $(document).ready(function() {
        var filename = $("#file").val();
        if (filename != "") {
            $("#file").click(function() {
                download(filename);
            });
        } else {
            return "";
        }
    });

    function download(fname) {
        location.href = "${path}/test/download.do?fileName=" + fname;
    }
</script>
</head>
<body>
    <!-- 메일 상세 -->
    <div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
        <h2>보낸 메일 상세보기</h2>
        <form id="frm">
            <input type="hidden" name="board_id" id="board_id"
                value="${detail.mail_id}">
            <div class="form-group">
                <label for="title">제목</label> 
                <input type="text" class="form-control" id="title" 
                    value="${detail.title}" name="title" readonly>
            </div>

            <div class="form-group">
                <label for="writter">받는 사람</label> 
                <input type="text" class="form-control" id="receiver" 
                    value="${detail.receiver}" name="receiver" readonly>
            </div>

            <div class="form-group">
                <label for="send_date">보낸 날짜</label> 
                <input type="text" value="${detail.send_date}" class="form-control" 
                    id="send_date" placeholder="Enter password" readonly>
            </div>

            <div class="form-group">
                <label for="detail">내용</label>
                <div class="form-control" id="content" readonly>${detail.content}</div>
            </div>

            <div class="form-group">
                <label for="file">파일</label>
                <input type="text" class="form-control" id="file" 
                    value="${detail.filestorage.fname}" name="file" readonly>
            </div>

            <button type="button" class="btn btn-primary" id="uptBtn">수정하기</button>
            <button type="button" class="btn btn-warning" id="delBtn">삭제하기</button>
        </form>
    </div>
</body>
</html>
