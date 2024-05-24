<script>
  // 부서 등록 폼 표시 함수
  function showForm() {
    document.getElementById("departmentForm").style.display = 'block';
  }

  //부서 인원 조회 함수
  function showDepartmentMembers(){
    window.location.href="/department/members"; //변경 필요
  }

  // 페이지 로드시 초기 부서 목록을 불러옵니다.
  $(document).ready(function() {
    loadDepartments();

    // 폼 제출 시
    $("#addDepartmentForm").submit(function(event) {
      // 폼의 기본 동작을 중지
      event.preventDefault();
      // 서버로 폼 데이터를 전송
      $.ajax({
        type: "POST",
        url: $(this).attr("action"),
        data: $(this).serialize(), // 폼 데이터를 시리얼라이즈하여 전송
        success: function(response) {
          // 성공적으로 등록된 경우, 부서 목록 다시 로드
          loadDepartments();
          // 등록 폼 숨김
          $("#departmentForm").hide();
          // 입력 필드 초기화
          $("#deptName").val("");
          $("#deptLocation").val("");
        },
        error: function(xhr, status, error) {
          // 에러 발생 시 처리
          console.error(error);
          alert("부서 등록에 실패했습니다.");
        }
      });
    });
  });

  // 부서 목록을 불러오는 함수
  function loadDepartments() {
    $.get("/department/list", function(data) {
    console.log(data); // 데이터 확인
      var tbody = $("#departmentTableBody");
      tbody.empty();
      // 데이터가 배열 형태인지 확인
      if (Array.isArray(data)) {
        // 배열 형태인 경우
        data.forEach(function(department) {
          var row = "<tr>" +
            "<td>" + department.id + "</td>" +
            "<td>" + department.dptName + "</td>" +
            "<td>" + department.location + "</td>" +
            "<td>" + department.memberCount + "</td>" +

            "</tr>";
          tbody.append(row);
        });
      } else {
        // 배열의 형태가 아닌 경우
        var row = "<tr>" +
          "<td>" + data.id + "</td>" +
          "<td>" + data.dptName + "</td>" +
          "<td>" + data.location + "</td>" +
          "<td>" + data.memberCount + "</td>" +
          "</tr>";
        tbody.append(row);
      }
    });
  }
  //부서 소속 인원 조회 함수
  function showDepartmentMembers(departmentId){
    window.location.href="/department/members?deptId="+departmentId; //변경필요
  }


</script>