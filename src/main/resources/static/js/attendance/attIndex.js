/*const memberId = document.querySelectorId('#memberId');*/
const memberId = document.querySelector('#memberId');
const id = document.querySelector('#id');
const attendanceType = document.querySelector('#attendanceType');
const checkInTime = document.querySelector('#checkInTime');
const checkOutTime = document.querySelector('#checkOutTime');

const tbody = document.querySelector('.tbody');

//**************************

function checkOutTimeBtnFn(id) {


  const data = {
    checkInTime: checkInTime,
    checkOutTime: checkOutTime,
    attendanceType: attendanceType
  };

  $.ajax({
    type: 'PUT',
    url: `/api/admin/attendance/${id}`,
    contentType: "application/json",
    data: JSON.stringify(data),
    success: function (res) {
      if (res == 1) {
        alert('퇴근 완료');
        ajaxAttendanceList();
      }
    },
    error: function () {
      alert('퇴근 실패');
    }
  });
}

// ajaxAttendanceList 함수 업데이트
function ajaxAttendanceList() {
  const url = "/api/admin/attendance/attList";
  fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      let dataHtml = ``;
      data.forEach(el => {
        dataHtml += `
          <tr>
            <td class="id" id="id">${el.id}</td>
            <td>${el.memberEntity.id}</td>
            <td><input type="text" id="attendanceType-${el.id}" value="${el.attendanceType}"></td>
            <td><input type="text" id="checkInTime-${el.id}" value="${el.checkInTime}"></td>
            <td><input type="text" id="checkOutTime-${el.id}" value="${el.checkOutTime}"></td>
            <td>
              <button type="button" onclick="attendanceDeleteBtnFn(event, ${el.id})">삭제</button>
              <button type="button" onclick="checkOutTimeBtnFn(${el.id})">퇴근</button>
            </td>
          </tr>
        `;
      });
      tbody.innerHTML = dataHtml;
    }).catch((error) => {
      console.log(error);
    });
}


//**************************

function attendanceDeleteBtnFn(event, id){
 const memberId=$('#memberId').val()
 $.ajax({
    type: 'POST',
    url: `/api/admin/attendance/attendanceDelete/${id}/member/${memberId}`,

    success: function (res) {
      if(res==1){
         alert('삭제완료')
         console.log(res)

         ajaxAttendanceList();
        }
      },
    error: function () {
    alert('실패')
    }
  });
}

//**************************

function ajaxAttendanceList() {
  const url = "/api/admin/attendance/attList";
  fetch(url, {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    })
    .then((response) => response.json())
    .then((data) => {
      console.log(data);
      let dataHtml = ``;
      data.forEach(el => {
        dataHtml += `
                 <tr >
                  <td class="id" id="id">${el.id}</td>
                  <td >${el.memberEntity.id}</td>
                  <td >${el.attendanceType}</td>
                  <td >${el.checkInTime}</td>
                  <td >${el.checkOutTime}</td>
                  <td>


                    <button type="button" class="checkOutTimeBtn" name="checkOutTimeBtn"onclick="attendanceDeleteBtnFn(event,${el.id})">삭제</button>
                    <button type="button" class="updateAttendance" name="updateAttendance"onclick="checkOutTimeBtnFn(${el.id})">퇴근</button>
                  </td>
                </tr>
               `;
      });
      tbody.innerHTML = dataHtml;
    }).catch((error) => {
      console.log(error);
    });
}

///////////////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////////////


const checkInTimeBtn=$('#checkInTimeBtn');

checkInTimeBtn.on('click', checkInTimeWrite);

function checkInTimeWrite(){
//html input의 데이터 값 가져오려면 .val

const memberId=$('#memberId').val();

const dataVal={
'memberId': memberId,
};

console.log(`checkInTimeWrite fn call`);
console.log(dataVal);

$.ajax({

type: 'POST',
url: '/api/admin/attendance/checkInTime',
//서버로 전달할 데이터 형식 지정
contentType : "application/json",
//요청 시 서버로 전달할 데이터 지정
//JSON.stringify() : json객체를 문자
data: JSON.stringify(dataVal),  // ajax에서 data type 기본은 폼 데이터 (json은 문자열 변환 과정 필요)
success: function(res){
alert(`checkInTimeWrite ok`);



//let htmlData=`<tr>`;
let htmlData=`<tr>

<td>${res.id}</td>

<td>${res.memberId}</td>
<td>${res.attendanceType}</td>
<td>${res.checkInTime}</td>
<td>${res.checkOutTime}</td>
</tr>`;
//htmlData+=`</tr`;
$(".tData").append(htmlData);

ajaxAttendanceList();

},    //서버에서 응답
error:function(){
alert("fail");
}
});

}

(()=>{
  ajaxAttendanceList();
})();



///////////////////////////////////////////////////////////////////

