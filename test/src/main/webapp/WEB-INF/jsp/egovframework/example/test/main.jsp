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
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<script src="<%=request.getContextPath()%>/js/boardChart.js"></script>
<style>
#myChartContainer {
	width: 500px;
	height: 500px;
}

#myChart {
	width: 100%;
	height: 100%;
}
</style>
</head>
<body>
	<!-- 메인은 차트 넣기 -->
	<div class="container" style="margin: 2%; width: 100%; max-width: 95%;">
		<h2>대시보드</h2>
		<div id="myChartContainer">
			<canvas id="myChart"></canvas>
		</div>
	</div>

	<script>
		// 페이지 로드 시 차트를 초기화
		window.onload = function() {
			var ctx = document.getElementById('myChart').getContext('2d');
			initializeChart(ctx);
		};
	</script>
</body>
</html>
