

$(document).ready(function ($) {
    /* ********************* 시작 영역 시작 ************************ */
    /* 시작할 때 필요한 부분 */
    /* 지도 시작 값 지정 */
    var localData = 22;
    /* 17:부산 / 18:충북 / 19:충남 / 20:대구 / 21:대전 / 22:강원 / 23:경북 */
    /* 24:경기 / 25:경남 / 26:인천 / 27:제주 / 28:전북 / 29:전남 / 30:서울 */
    /* 31:울산 / 32:광주 */


    festivalDateAjax();
    festivalLocationClick(localData);
    festivalAjax(localData);
    /* ********************* 시작 영역 끝 ************************ */ 
    /* **************************************** 이미지 슬라이드 *****************************************/
    var $slider = $('.slider'),
        $firstSlide = $slider.find('li').first()
            .stop(true).animate({'opacity':1},200);

    function PrevSlide(){
        stopSlide();startSlide();
        var $lastSlide = $slider.find('li').last()
            .prependTo($slider);
        $secondSlide = $slider.find('li').eq(1)
            .stop(true).animate({'opacity':0},400);
        $firstSlide = $slider.find('li').first()
            .stop(true).animate({'opacity':1},400);
    }

    function NextSlide(){
        stopSlide();startSlide();
        $firstSlide = $slider.find('li').first()
            .appendTo($slider);
        var $lastSlide = $slider.find('li').last()
            .stop(true).animate({'opacity':0},400);
        $firstSlide = $slider.find('li').first()
            .stop(true).animate({'opacity':1},400);
    }

    $('#next').on('click', function(){
        NextSlide();
    });
    $('#prev').on('click', function(){
        PrevSlide();
    });

    startSlide();

    var theInterval;

    function startSlide() {
        theInterval = setInterval(NextSlide, 4500);
    }

    function stopSlide() {
        clearInterval(theInterval);
    }

    $('.slider').hover(function(){
        stopSlide();
    }, function (){
        startSlide();
    });


    /* **************************** 밑 지도 부분 시작 ********************** */
    var index;

    $(".location span").click(function () {
        index = $(this).index();
        festivalLocationClick(index);
        festivalAjax(index);
    });

    /* 사진은 16부터 / 글씨는 17부터 ~ +15 */
    function festivalLocationClick(index) {

        for (let i = 1; i <= 16; i++) {
            $(".location img").eq(i).css("display", "none");
            $(".location span").eq(i - 1).css("font-weight", "normal");
        }

        $(".location img").eq(index - 16).css("display", "block");
        $(".location span").eq(index - 17).css("font-weight", "bold");
    }

   
    


});
/* ******************************* 자동 실행 밖 영역 입니다.********************************** */




/* **************************** 밑 지도 부분 시작 ********************** */

/* 지도에 따른 지역에 사용 될 데이터를 가져오는 Ajax */
function festivalAjax(index) {
    $.ajax({
        url : "/index/festival",
        type : 'post',
        data : {Local : $(".location span").eq(index - 17).text()},
        success : function(data) {
            festivalData(data);
        },
    });
}
/* 지도 핫플영역 월 가져오는 Ajax */
function festivalDateAjax() {
    $.ajax({
        url : "/index/viewdate",
        type : 'get',
        success : function(data) {
            festivalDate(data);
        },
    });
}

/* 지도 핫플영역 월 적용 함수 */
function festivalDate(data) {
    var Festivaltitle = `<div>`+ data.slice(6,7) +`월 핫플 축제! </div>`
    $(".festivalMonthTitle").html(Festivaltitle);
}


/* 지도에 따른 지역에 사용될 데이터 innerhtml 하는 함수 */
function festivalData(data) {


    var html = ``;

    for(let i=0;i<data.length; i++){
        html += `<div class="festivalData" style="cursor : pointer;" onclick="location.href='` + data[i].homepage + `'">`;
    html += `<div style="font-weight: bold;">` + data[i].name + `</div>`
    html += `<div>` + data[i].roadAddress + `</div>`
    html += `<div>` + data[i].startDate.slice(0,10) + ` ~ ` + data[i].endDate.slice(0,10)+ `</div>`
        html += `</div>`
    }


    $(".festival").html(html);
}

