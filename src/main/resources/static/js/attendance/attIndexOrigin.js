/*const memberId = document.querySelectorId('#memberId');*/
const memberId = document.querySelector('#memberId');
const id = document.querySelector('#id');
const attendanceType = document.querySelector('#attendanceType');
const checkInTime = document.querySelector('#checkInTime');
const checkOutTime = document.querySelector('#checkOutTime');

const tbody = document.querySelector('.tbody');

//**************************

///////////////////////////////////////////////////////////////////


const checkInTimeBtn=$('#checkInTimeBtn');




$('#checkInTimeBtn').on('click', test);
function test() {
        if (!confirm("출근하시겠습니까?")) {

        } else {
        checkInTimeWrite();
        }
    }



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
alert(`출근완료`);


},    //서버에서 응답
error:function(){
alert("이미 출근하셨습니다.");
}
});

}



///////////////////////////////////////////////////////////////////

function checkOutTimeBtnFn(id) {

 if (!confirm("퇴근하시겠습니까?")) {
 } else {

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
}


//**************************
