<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="top.jsp" flush="true" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>뉴스레터 상세보기</title>
  <!-- CKEditor JavaScript 파일 로드 -->
  <script src="<%= request.getContextPath() %>/html/ckeditor/ckeditor.js"></script>
  <script src="<%= request.getContextPath() %>/html/ckeditor/config.js"></script>
    <script src="https://cdn.ckeditor.com/4.22.1/full-all/plugins/exportpdf/plugin.js"></script>

  <style>
  .ckeditor-custom {
      width: 100%;
      height: 700px;
      margin-top: 10px;
  }
  </style>
</head>
<script type="text/javascript">
$(document).ready(function(){
    $("#uptBtn").click(function(){
        if (confirm("수정하시겠습니까?")) {
            uptData();
        } else {
            alert("수정 취소");
        }
    });
    
    $("#delBtn").click(function(){
    	if(confirm("삭제하시겠습니까?")){
    		 delData();
    	}else{
    		alert("삭제 취소하셨습니다.");
    	}
    })
});

// 수정함수
function uptData(){
	var formData = new FormData();
	formData.append('news_id', $("#news_id").val());
	formData.append('title', $("#title").val());
	formData.append('writter', $("#writter").val());
	formData.append('content', CKEDITOR.instances.content.getData());
    $.ajax({
        url : "${path}/test/uptNews.do",
        type : "post",
        data : formData,
        //dataType : json,
        cache : false,
        contentType : false,
        processData : false,
        success : function(response) {
            if (response == 1) {
                alert("수정 완료");
                location.href = "${path}/test/newsList.do";
            } else {
                alert("수정 에러");
            }
        },
        error : function(err) {
            console.error("수정 실패:", err);
            alert("수정 중 오류가 발생했습니다.");
        }
    });
}

// 삭제함수
function delData(){
	$.ajax({
		url : "${path}/test/delNews.do?news_id="+$("#news_id").val(),
		dataType : "json",
		type: "get",
		success : function(res){
			if(res==1){
				alert("삭제 성공")
				location.href = "${path}/test/newsList.do";
			}else{
				alert("삭제 실패")
			}
		},
		error : function(err){
			console.log(err)
		}
	})
}
</script>
<body>
    <div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
        <h2>뉴스레터 상세보기</h2>
        <form id="frm" method="post" enctype="multipart/form-data" >
        	<input type="hidden" name="news_id" id="news_id" value="${news.news_id}">
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" name="title" value="${news.title}">
            </div>
            <div class="form-group">
                <label for="writter">작성자</label>
                <input type="text" class="form-control" id="writter" name="writter" value="${news.writter}">
            </div>
            <div class="form-group">
                <label for="writter">등록일</label>
                <input type="text" class="form-control" id="reg_date" name="reg_date" value="${news.reg_date}" readonly>
            </div>
            <div class="form-group">
                <label for="writter">수정일</label>
                <input type="text" class="form-control" id="upt_date" name="upt_date" value="${news.upt_date}" readonly>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea class="form-control ckeditor-custom" id="content" name="content">${news.content}</textarea>
            </div>
          <button type="button" class="btn btn-primary" id="uptBtn">수정하기</button>
			<button type="button" class="btn btn-warning" id="delBtn">삭제하기</button>
			<button type="button" class="btn btn-warning" id="sol">내용</button>
        </form>
    </div>

    <script type="text/javascript">
        // 링크, 자세히 버튼 없애고 업로드 탭 생성
        CKEDITOR.on('dialogDefinition', function (ev) {
            var dialogName = ev.data.name;
            var dialog = ev.data.definition.dialog;
            var dialogDefinition = ev.data.definition;
            if (dialogName == 'image') {
                dialog.on('show', function (obj) {
                    this.selectPage('Upload'); // 업로드 탭으로 시작
                });
                dialogDefinition.removeContents('advanced'); // 자세히 탭 제거
                dialogDefinition.removeContents('Link'); // 링크 탭 제거
            }
        });

        CKEDITOR.replace('content', {
            filebrowserUploadUrl: '<%= request.getContextPath() %>/imageUpload.do',
            //extraPlugins: 'exportpdf', 
            height: 700, // 원하는 높이 설정 (픽셀 단위)
            width: '100%' // 원하는 너비 설정 (백분율 또는 픽셀 단위)
        });
    </script>
</body>
</html>
