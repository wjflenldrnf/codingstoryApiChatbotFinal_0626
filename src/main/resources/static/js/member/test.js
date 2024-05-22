// 모달 버튼과 모달창 가져오기
var modalBtn = document.getElementById("modalBtn");
var modal = document.getElementById("myModal");

// 모달 버튼을 클릭하면 모달창을 열도록 이벤트 리스너 등록
modalBtn.onclick = function() {
  modal.style.display = "block";
};

// 모달 닫기 버튼 클릭 시 모달창 닫도록 이벤트 리스너 등록
var closeBtn = modal.querySelector(".close");
closeBtn.onclick = function() {
  modal.style.display = "none";
};


var closeBtn2 = modal.querySelector(".close2");
closeBtn.onclick = function() {
  modal.style.display = "none";
};
