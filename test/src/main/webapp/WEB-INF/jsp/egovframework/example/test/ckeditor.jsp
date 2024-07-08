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
  <script src="<%= request.getContextPath() %>/html/egovframework/com/cmm/utl/ckeditor/ckeditor.js"></script>

<style>
.ckeditor-custom {
    width: 80%;
    height: 100%;
    margin-top: 
}
</style>
</head>
<body>
    <h1>CKEditor</h1>
    
    <tr><th>내용</th>
		<td><textarea id="content" name=""></textarea>
		<script type="text/javascript">	// 글쓰기 editor 및 사진 업로드 기능
			CKEDITOR.replace('content')
			//,{filebrowserUploadUrl:'/food/imageUpload.do'
			//});
		</script></td>
	</tr>
    


</body>
</html>
