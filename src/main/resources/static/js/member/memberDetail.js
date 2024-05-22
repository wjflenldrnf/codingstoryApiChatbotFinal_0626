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

function memberAddressUpdateFn(){
  var address = $('#address').val();
  var memberId = $('#memberId2').val();
  $.ajax({
    type: 'POST',
    url: '/member/addressUpdate',
    data: { id: memberId, address: address },
    success: function () {
    location.href=`/member/myDetail/${memberId}`;
    },
    error: function () {
      alert('실패')
    }
  });
}


function memberPhoneNumberUpdateFn(){
  var phoneNumber = $('#phoneNumber').val();
  var memberId = $('#memberId3').val();
  $.ajax({
    type: 'POST',
    url: '/member/phoneNumberUpdate',
    data: { id: memberId, phoneNumber: phoneNumber },
    success: function () {
    location.href=`/member/myDetail/${memberId}`;
    },
    error: function () {
      alert('실패')
    }
  });
}


function memberPasswordUpdateFn(){
  var userPw = $('#userPw').val();
  var memberId = $('#memberId4').val();
  $.ajax({
    type: 'POST',
    url: '/member/passwordUpdate',
    data: { id: memberId, userPw: userPw },
    success: function () {
    location.href=`/member/myDetail/${memberId}`;
    },
    error: function () {
      alert('실패')
    }
  });
}

function passwordCheck(){
    const passwordGo=document.getElementById('.passwordGo')
    if($('#userPw').val() == $('#userPw2').val()){
        $('#passwordCheck').text('')
        document.getElementById('passwordGo').disabled =false;
        $('#passwordGo').css('cursor','pointer')
    }else{
        $('#passwordCheck').text('비밀번호가 다릅니다').css('color', 'red')
        document.getElementById('passwordGo').disabled =true;
        $('#passwordGo').css('cursor','no-drop')
    }
}









