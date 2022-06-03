$(document).ready(function ($) {

    festivalDateAjax();
    festivalLocationClick(17);
    festivalAjax(17);



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
        url : "/index/date",
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