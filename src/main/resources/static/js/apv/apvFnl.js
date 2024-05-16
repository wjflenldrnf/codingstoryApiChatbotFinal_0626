const btn = document.getElementById("popupBtn"); // 모달을 띄우는 버튼 요소 가져오기
const modal = document.getElementById("modalWrap"); // 모달 창 요소 가져오기
const closeBtn = document.getElementById("closeBtn"); // 모달을 닫는 버튼(X) 요소 가져오기

btn.onclick = function () {
  modal.style.display = "block"; // 버튼을 클릭하면 모달을 보이게 함
};

closeBtn.onclick = function () {
  modal.style.display = "none"; // 모달을 닫는 버튼(X)을 클릭하면 모달을 숨김
};

window.onclick = function (event) {
  if (event.target == modal) {
    modal.style.display = "none"; // 모달 외부를 클릭하면 모달을 숨김
  }
};


