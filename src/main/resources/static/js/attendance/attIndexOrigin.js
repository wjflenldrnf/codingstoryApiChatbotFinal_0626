let attendanceId; // 출근 시 생성된 attendance ID를 저장할 변수

const memberId = document.querySelector('#memberId');
const id = document.querySelector('#id');
const attendanceType = document.querySelector('#attendanceType');
const tbody = document.querySelector('.tbody');

// 출근 버튼 이벤트 핸들러
const checkInTimeBtn = $('#checkInTimeBtn');
checkInTimeBtn.on('click', handleCheckIn);

// 퇴근 버튼 이벤트 핸들러
const checkOutTimeBtn = $('#checkOutTimeBtn');
checkOutTimeBtn.on('click', handleCheckOut);

function handleCheckIn() {
    if (!confirm("출근하시겠습니까?")) {
        return;
    }
    checkInTimeWrite();
}

function checkInTimeWrite() {
    const memberId = $('#memberId').val();
    const dataVal = { 'memberId': memberId };

    console.log(`checkInTimeWrite fn call`);
    console.log(dataVal);

    $.ajax({
        type: 'POST',
        url: '/api/admin/attendance/test/checkInTime',
        contentType: "application/json",
        data: JSON.stringify(dataVal),
        success: function(res) {
            alert('출근완료');
            
            // 반환된 attendance ID 저장
            attendanceId = res.id;

            let htmlData = `
                <tr>
                    <td>${res.id}</td>
                    <td>${res.memberId}</td>
                    <td>${res.attendanceType}</td>
                    <td>${res.checkInTime}</td>
                    <td>${res.checkOutTime}</td>
                </tr>
            `;
            $(".tData").append(htmlData);

            // 출근 정보 표시
            let infoHtml = `
                <div class="attendance-info">
                    <p>Attendance ID: ${res.id}</p>
                    <p>Member ID: ${res.memberId}</p>
                    <p>Attendance Type: ${res.attendanceType}</p>
                    <p>Check-In Time: ${res.checkInTime}</p>
                    <p>Check-Out Time: ${res.checkOutTime}</p>
                </div>
            `;
            $("#attendanceInfo").html(infoHtml); // 정보 표시 영역에 추가

            // 퇴근 버튼 표시
            $("#checkOutTimeBtn").show();
        },
        error: function() {
            alert('이미 출근하셨습니다.');
        }
    });
}

function handleCheckOut() {
    if (!confirm("퇴근하시겠습니까?")) {
        return;
    }
    checkOutTimeWrite();
}

function checkOutTimeWrite() {
    if (!attendanceId) {
        alert("출근 기록이 없습니다.");
        return;
    }

    const data = {
        checkOutTime: new Date().toISOString()
    };

    $.ajax({
        type: 'PUT',
        url: `/api/admin/attendance/test/${attendanceId}`, // 저장된 attendanceId를 URL에 포함시킵니다.
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function(res) {
            alert('퇴근 완료');

            // 퇴근 정보 표시


            let infoHtml = `
                <div class="attendance-info">
                    <p>Attendance ID: ${res.id}</p>
                    <p>Member ID: ${res.memberId}</p>
                    <p>Attendance Type: ${res.attendanceType}</p>
                    <p>Check-In Time: ${res.checkInTime}</p>
                    <p>Check-Out Time: ${res.checkOutTime}</p>
                    <p>Work Time: ${res.workTime}</p>
                </div>
            `;
            $("#attendanceInfo").html(infoHtml); // 정보 표시 영역에 추가


        },
        error: function() {
            alert('퇴근 실패');
        }
    });
}
