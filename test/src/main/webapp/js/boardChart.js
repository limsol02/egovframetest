let myChart = null;

// Chart.js 초기화 함수
function initializeChart(ctx, data) {
    if (myChart) {
        myChart.destroy();
    }

    myChart = new Chart(ctx, {
        type: 'doughnut', // 차트 유형
        data: {
            labels: ['첨부파일 게시글', '일반 게시글'],
            datasets: [{
                label: '게시글 유형',
                data: data,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)'
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)'
                ],
                borderWidth: 1
            }]
        },
        options: {
            responsive: true,
            plugins: {
                legend: {
                    position: 'top',
                },
                title: {
                    display: true,
                    text: '게시글 유형별 비율'
                }
            }
        }
    });
}

// 데이터를 서버에서 받아와 차트를 초기화하는 함수
function fetchDataChart() {
    fetch('/test/chartData.do')  // 서버에서 제공하는 올바른 경로로 수정
        .then(response => {
            if (!response.ok) {
                throw new Error('네트워크 에러 발생 : ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            // 서버에서 받은 데이터로 차트를 초기화
            const chartData = [data.boardWithFile, data.allData - data.boardWithFile];
            const ctx = document.getElementById('myChart').getContext('2d');
            initializeChart(ctx, chartData);
        })
        .catch(error => {
            console.log(error);
        });
}

// 페이지가 로드될 때 차트를 초기화하는 함수 호출
document.addEventListener('DOMContentLoaded', fetchDataChart);
