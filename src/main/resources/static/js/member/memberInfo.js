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

function memberMDFn(){
  var id = $('#id').val();
  var department= $('#department').val()
  var mRnak= $('#mRnak').val()
  $.ajax({
    type: 'POST',
    url: '/member/memberMD',
    data: { id: id,
    department : department,
    mRank: mRank
    },
    success: function () {
    location.href=`/member/memberInfo/${id}`;
    },
    error: function () {
      alert('실패')
    }
  });
}





