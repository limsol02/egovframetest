<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="top.jsp" flush="true" />

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Main Page</title>
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
        $("#insBtn").click(function(){
            var formData = new FormData(form);
            formData.append('receiver', $("#receiver").val());
            formData.append('title', $("#title").val());
            formData.append('files', $("#files").val());
            formData.append('content', CKEDITOR.instances.content.getData());

            $.ajax({
                url: '${path}/sendMail.do',
                type: 'POST',
                data: formData,
                processData: false,
                contentType: false,
                success: function(response){
                    alert("메일 전송 성공: " + response);
                },
                error: function(jqXHR, textStatus, errorThrown){
                    alert("메일 전송 실패: " + textStatus);
                }
            });
        });
    });
</script>
<body>
    <div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
        <h2>메일 전송</h2>
        <form id="frm" method="post" enctype="multipart/form-data" action="${path}/test/insNews.do">
            <div class="form-group">
                <label for="receiver">받는 사람</label>
                <input type="email" class="form-control" id="receiver" name="receiver">
            </div>
            <div class="form-group">
                <label for="title">제목</label>
                <input type="text" class="form-control" id="title" name="title">
            </div>
            <div class="form-group">
                <label for="files">파일 첨부</label>
                <input type="file" class="form-control" id="files" name="files" multiple>
            </div>
            <div class="form-group">
                <label for="content">내용</label>
                <textarea class="form-control ckeditor-custom" id="content" name="content" ></textarea>
            </div>
            <button type="button" id="insBtn" class="btn btn-primary" style="margin-top: 2%;">발송하기</button>
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
            //filebrowserUploadMethod: 'form',
            height: 700, // 원하는 높이 설정 (픽셀 단위)
            width: '100%' // 원하는 너비 설정 (백분율 또는 픽셀 단위)
        });
    </script>
</body>
</html>
