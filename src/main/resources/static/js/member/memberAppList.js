function memberAppOkFn(){
  var role = $('#role').val();
  var memberId = $('#id').val();
  $.ajax({
    type: 'POST',
    url: '/member/memberAppOk',
    data: { id: memberId, role: role },
    success: function (res) {
    if(res==1){
    alert('승인되었습니다')
    location.href=`/member/memberAppList`;
    }
    },
    error: function () {
      alert('실패')
    }
  });
}

function memberAppNoFn(){
  var memberId = $('#id').val();
  $.ajax({
    type: 'POST',
    url: `/member/memberDelete/${memberId}`,
    success: function (res) {
    if(res==1){
    alert('거절되었습니다')
    location.href=`/member/memberAppList`;
    }
    },
    error: function () {
      alert('실패')
    }
  });
}

function memberAppNoReady() {
        if (!confirm("거절하시겠습니까?")) {
        } else {
        memberAppNoFn()
        }
}

