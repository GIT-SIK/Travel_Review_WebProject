// title, option 현재 달 선택
$(document).ready(function () {
    let today = new Date();
    let month = today.getMonth() + 1;
    $('#month option:eq(' + month +')').attr("selected", "selected");
    $('#month2').text(month);
});

// 값표시 함수
function makeList(result, i) {
    addRow =
        '<li class="festivalData">' +
        '<div class="festivalDetail">' +
        // '<span>' + '축제명 : ' + '</span>' +
        '<span id="festivalName">' + result[i].name + '</span>' + '<br>' +
        '<span>' + '기간 : ' + '</span>' +
        '<span>' + result[i].startDate.substring(0, 10) + '</span>' +
        '<span>' + ' ~ ' + '</span>' +
        '<span>' + result[i].endDate.substring(0, 10) + '</span>' + '<br>' +
        '<span>' + '주소 : ' + '</span>'
    if (result[i].roadAddress === "") {
        addRow +=
            '<span>' + result[i].lotNumAddress + '</span>' + '<br>'
    } else {
        addRow +=
            '<span>' + result[i].roadAddress + '</span>' + '<br>'
    }
    if (result[i].homepage != "") {
        addRow +=
            '<span>' + '홈페이지 : ' + '</span>' +
            '<a href="' + result[i].homepage + '">' +
            '<span>' + result[i].homepage + '</span>' + '</a>'
    }
    addRow += '</div>' + '</li>';
    $(".festivalList").append(addRow);
}

// 사용자가 달 변경시 표시 변경 & 데이터 변경
$('#month').change(function(){
    var change_month = $(this).val();
    $("#month option").removeAttr("selected", "selected");
    $('#month option:eq(' + change_month +')').attr("selected", "selected");
    $('#month2').text(change_month);
    // 지역 전체로 초기화
    $(".localList li").removeClass("selected");
    $('.localList li:eq(' + 0 + ')').addClass("selected");
    $('.festivalData').remove();
    $.ajax({
        url : "/festival/changeMonth",
        data : {month : change_month},
        type : 'post',
        success : function(result){
            for (var i = 0; i < result.length; i++) {
                makeList(result, i);
            }
        }
    });
});

// 지역 선택시
$(".localList li").on("click", function(){
    // 클릭하는 요소의 index 번호를 가져옴
    const num = $(".localList li").index($(this));
    // 기존에 적용되어 있는 selected class 삭제
    $(".localList li").removeClass("selected");
    // 클릭한 요소에 selected class 추가
    $('.localList li:eq(' + num + ')').addClass("selected");
    var change_month = $('#month').val();
    $('.festivalData').remove();
    // 지역별 눌렀을시 주소확인후 출력
    $.ajax({
        url : "/festival/changeMonth",
        data : {month : change_month},
        type : 'post',
        success : function(result){
            if(num == 0) {
                for (var i = 0; i < result.length; i++) {
                    makeList(result, i);
                }
            } else if (num == 1) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "서울" || result[i].lotNumAddress.substring(0, 2) === "서울") {
                        makeList(result, i);
                    }
                }
            } else if (num == 2) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "경기" || result[i].lotNumAddress.substring(0, 2) === "경기") {
                        makeList(result, i);
                    }
                }
            } else if (num == 3) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "인천" || result[i].lotNumAddress.substring(0, 2) === "인천") {
                        makeList(result, i);
                    }
                }
            } else if (num == 4) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "강원" || result[i].lotNumAddress.substring(0, 2) === "강원") {
                        makeList(result, i);
                    }
                }
            } else if (num == 5) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "세종" || result[i].lotNumAddress.substring(0, 2) === "세종") {
                        makeList(result, i);
                    }
                }
            } else if (num == 6) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 4) === "충청북도" || result[i].lotNumAddress.substring(0, 4) === "충청북도") {
                        makeList(result, i);
                    }
                }
            } else if (num == 7) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 4) === "충청남도" || result[i].lotNumAddress.substring(0, 4) === "충청남도") {
                        makeList(result, i);
                    }
                }
            } else if (num == 8) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "대구" || result[i].lotNumAddress.substring(0, 2) === "대구") {
                        makeList(result, i);
                    }
                }
            } else if (num == 9) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "부산" || result[i].lotNumAddress.substring(0, 2) === "부산") {
                        makeList(result, i);
                    }
                }
            } else if (num == 10) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "울산" || result[i].lotNumAddress.substring(0, 2) === "울산") {
                        makeList(result, i);
                    }
                }
            } else if (num == 11) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 4) === "경상북도" || result[i].lotNumAddress.substring(0, 4) === "경상북도") {
                        makeList(result, i);
                    }
                }
            } else if (num == 12) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 4) === "경상남도" || result[i].lotNumAddress.substring(0, 4) === "경상남도") {
                        makeList(result, i);
                    }
                }
            } else if (num == 13) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "광주" || result[i].lotNumAddress.substring(0, 2) === "광주") {
                        makeList(result, i);
                    }
                }
            } else if (num == 14) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 4) === "전라북도" || result[i].lotNumAddress.substring(0, 4) === "전라북도") {
                        makeList(result, i);
                    }
                }
            } else if (num == 15) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 4) === "전라남도" || result[i].lotNumAddress.substring(0, 4) === "전라남도") {
                        makeList(result, i);
                    }
                }
            } else if (num == 16) {
                for (var i = 0; i < result.length; i++) {
                    if (result[i].roadAddress.substring(0, 2) === "제주" || result[i].lotNumAddress.substring(0, 2) === "제주") {
                        makeList(result, i);
                    }
                }
            }
        }
    });
});