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
		$("#uptBtn").click(function() {
			if (confirm("수정하시겠습니까?")) {
				uptData();
			} else {
				alert("수정 취소")
			}
		});
		
		$("#delBtn").click(function() {
			if (confirm("삭제하시겠습니까?")) {
				delData();
			} else {
				alert("수정 취소")
			}
		});
		
		var filename = $("#file").val();
		if(filename!=""){
			$("#file").click(function(){
				download(filename)
			})
		}else{
			return "";
		}
	});
	// 수정 함수
	function uptData() {
		var formData = $("#frm").serialize();
		console.log(formData); // 디버깅을 위해 데이터를 출력합니다.

		$.ajax({
			url : "${path}/test/uptBoard.do",
			type : "POST",
			data : formData,
			dataType : "json",
			success : function(response) {
				if (response == 1) {
					alert("수정 완료")
					location.href = "${path}/test/board.do"
				} else {
					alert("수정 에러")
				}

			},
			error : function(err) {
				console.error("수정 실패:", err);
				alert("수정 중 오류가 발생했습니다.");
			}
		});
	}
	// 삭제함수
	function delData() {
		$.ajax({
			url : "${path}/test/delBoard.do?board_id=" + $("#board_id").val(),
			type : "get",
			dataType : "json",
			success : function(response) {
				if (response == 1) {
					alert("삭제 완료")
					location.href = "${path}/test/board.do"
				} else {
					alert("삭제 에러")
				}

			},
			error : function(err) {
				console.error("삭제 실패:", err);
				alert("삭제 중 오류가 발생했습니다.");
			}
		})
	}
	
	function download (fname){
		location.href = "${path}/test/download.do?fileName="+fname;
	}
</script>
<body>
	<!-- 게시판 상세 -->
	<div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
		<h2>게시물 상세보기</h2>
		<form id="frm">
			<input type="hidden" name="board_id" id="board_id"
				value="${detail.board_id}">
			<div class="form-group">
				<label for="title">제목</label> <input type="text"
					class="form-control" id="title" value="${detail.title}"
					name="title">
			</div>

			<div class="form-group">
				<label for="writter">작성자</label> <input type="text"
					class="form-control" id="writter" value="${detail.writter}"
					name="writter">
			</div>

			<div class="form-group">
				<label for="reg_date">등록일</label> <input type="text"
					value="${detail.reg_date}" class="form-control" id="reg_date"
					placeholder="Enter password">
			</div>

			<div class="form-group">
				<label for="reg_date">수정일</label> <input type="text"
					value="${detail.upt_date}" class="form-control" id="upt_date">
			</div>

			<div class="form-group">
				<label for="detail">내용</label>
				<textarea class="form-control" rows="5" id="detail" name="detail">${detail.detail}</textarea>
			</div>
			
			 <div class="form-group">
                <label for="file">파일</label>
                <input type="text" class="form-control" id="file" 
                 value="${file.fname}" name="file" readonly>
            </div>

			<button type="button" class="btn btn-primary" id="uptBtn">수정하기</button>
			<button type="button" class="btn btn-warning" id="delBtn">삭제하기</button>
		</form>
	</div>
</body>
</html>
