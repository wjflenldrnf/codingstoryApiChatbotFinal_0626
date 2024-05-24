function memberDeleteFn(){
 const id=$('#id').text()
 $.ajax({
    type: 'POST',
    url: `/member/memberDelete/${id}`,
    success: function (res) {
      if(res==1){
         alert('해고완료')
       location.href=`/member/memberList`;
        }
      },
    error: function () {
    alert('실패')
    }
  });
}

function memberDeleteReady() {
        if (!confirm("해고하시겠습니까?")) {
        } else {
           memberDeleteFn();
        }
}


function memberNameUpdateFn(){
  var name = $('#name').val();
  var memberId = $('#memberId').val();
  $.ajax({
    type: 'POST',
    url: '/member/nameUpdate',
    data: { id: memberId, name: name },
    success: function () {
    location.href=`/member/myDetail/${memberId}`;

    },
    error: function () {
      alert('실패')
    }
  });
}


