window.replyDelete = function (event) {
  // 제목과 내용의 value를 담을 변수
  let btn = event.target;
  let rRid = $(btn).siblings("#rRid").val();
  let rBid = $(btn).siblings("#rBid").val();
  let text = $(btn).parent().prev().val();
  let obj = {"rRid": rRid, "rBid": rBid, "text": text}

  $.ajax({
    url: "/board/replyDelete",
    type: 'post',
    data: JSON.stringify(obj),
    contentType: 'application/json',
    success: function (data) {
      //화면 re-rendering 필요
      if (data == 'success') {
        location.reload();
      }
    }
  });
};
