$(document).ready(function ($) {

    var index;

    $(".location span").click(function () {
        index = $(this).index();
        locationClick(index);
        locationAjax(index);
    });

    /* 사진은 16부터 / 글씨는 17부터 ~ +15 */
    function locationClick(index) {

        for (let i = 1; i <= 16; i++) {
            $(".location img").eq(i).css("display", "none");
            $(".location span").eq(i - 1).css("font-weight", "normal");
        }

        $(".location img").eq(index - 16).css("display", "block");
        $(".location span").eq(index - 17).css("font-weight", "bold");
    }

    function locationAjax(index) {
        console.log($(".location span").eq(index - 17).text());
    }

});