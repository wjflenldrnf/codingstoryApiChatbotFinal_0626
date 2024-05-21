function memberDeleteFn(){
const id=$('#name').text()
 $.ajax({
    type: 'POST',
    url: `/apv/write`,
    success: function () {

      },
    error: function () {
    alert('실패')
    }
  });
}
