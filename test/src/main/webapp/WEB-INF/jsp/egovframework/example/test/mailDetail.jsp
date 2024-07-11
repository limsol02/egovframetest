<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<jsp:include page="top.jsp" flush="true" />

<!DOCTYPE html>
<html lang="en">
<style>
	#content {
        height: auto; /* 높이를 자동으로 조절 */
        overflow: visible; /* 스크롤바를 사용하지 않음 */
        white-space: pre-wrap; /* 공백과 줄바꿈을 유지 */
    }
</style>
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
        }

        $("#delBtn").click(function() {
            if (confirm("삭제하시겠습니까?")) {
                delData();
            } else {
                alert("삭제 취소");
            }
        });
    });

    function download(fname) {
        location.href = "${path}/test/download.do?fileName=" + fname;
    }
    
    function delData() {
        var mail_id = $("#mail_id").val();
        $.ajax({
            url: "${path}/test/delMail.do?mail_id=" + mail_id,
            type: "get",
            dataType: "json",
            success: function(response) {
                if (response == 1) {
                    alert("삭제 완료");
                    location.href = "${path}/test/mailList.do";
                } else {
                    alert("삭제 에러");
                }
            },
            error: function(err) {
                console.log(err);
            }
        });
    }
</script>
</head>
<body>
    <!-- 메일 상세 -->
    <div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
        <h2>보낸 메일 상세보기</h2>
        <form id="frm">
            <input type="hidden" name="mail_id" id="mail_id"
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

            <button type="button" class="btn btn-warning" id="delBtn">삭제하기</button>
        </form>
    </div>
</body>
</html>
