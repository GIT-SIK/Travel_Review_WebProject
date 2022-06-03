

$(document).ready(function ($) {
    /* 시작할 때 필요한 부분 */
    var localData = 22;



    festivalDateAjax();
    festivalLocationClick(localData);
    festivalAjax(localData);

    /* **************************************** 이미지 슬라이드 *****************************************/
    var $slider = $('.slider'),
        $firstSlide = $slider.find('li').first() // 첫번째 슬라이드
            .stop(true).animate({'opacity':1},200); // 첫번째 슬라이드만 보이게 하기

    function PrevSlide(){ // 이전버튼 함수
        stopSlide();startSlide(); //타이머 초기화
        var $lastSlide = $slider.find('li').last() //마지막 슬라이드
            .prependTo($slider); //마지막 슬라이드를 맨 앞으로 보내기  
        $secondSlide = $slider.find('li').eq(1)//두 번째 슬라이드 구하기
            .stop(true).animate({'opacity':0},400); //밀려난 두 번째 슬라이드는 fadeOut 시키고
        $firstSlide = $slider.find('li').first() //맨 처음 슬라이드 다시 구하기
            .stop(true).animate({'opacity':1},400);//새로 들어온 첫 번째 슬라이드는 fadeIn 시키기
    }

    function NextSlide(){ // 다음 버튼 함수
        stopSlide();startSlide(); //타이머 초기화
        $firstSlide = $slider.find('li').first() // 첫 번째 슬라이드
            .appendTo($slider); // 맨 마지막으로 보내기
        var $lastSlide = $slider.find('li').last() // 맨 마지막으로 보낸 슬라이드
            .stop(true).animate({'opacity':0},400); // fadeOut시키기
        $firstSlide = $slider.find('li').first()// 맨 처음 슬라이드
            .stop(true).animate({'opacity':1},400);// fadeIn 시키기
    }

    $('#next').on('click', function(){ //다음버튼 클릭
        NextSlide();
    });
    $('#prev').on('click', function(){ //이전 버튼 클릭
        PrevSlide();
    });

    startSlide(); // 자동 슬라이드 시작

    var theInterval;

    function startSlide() {
        theInterval = setInterval(NextSlide, 5000); //자동 슬라이드 설정
    }

    function stopSlide() { //자동 멈추기
        clearInterval(theInterval);
    }

    $('.slider').hover(function(){ //마우스 오버시 슬라이드 멈춤
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

function festivalDateAjax() {
    $.ajax({
        url : "/index/viewdate",
        type : 'get',
        success : function(data) {
            festivalDate(data);
        },
    });
}


function festivalDate(data) {
    var Festivaltitle = `<div>`+ data.slice(6,7) +`월 핫플 축제! </div>`
    $(".festivalMonthTitle").html(Festivaltitle);
}


function festivalData(data) {


    var html = ``;

    for(let i=0;i<data.length; i++){
        html += `<div class="festivalData">`
    html += `<div>` + data[i].name + `</div>`
    html += `<div>` + data[i].roadAddress + `</div>`
    html += `<div>` + data[i].startDate.slice(0,10) + ` ~ ` + data[i].endDate.slice(0,10)+ `</div>`
        html += `</div>`
    }


    $(".festival").html(html);
}

