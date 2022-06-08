// 유효성 검사 만들어서 넣기(빈칸 & 유형에 맞게?)
$(function() {

    $('.select-menu li').eq(0).css("border","1px solid gray");
    $('.select-menu li').eq(1).css("border","1px solid lightgray");

    $('.select-menu li').click(function(){

        for(let i=0; i<2 ;i++){
            $('.select-menu li').eq(i).css("border","1px solid lightgray");

        }
        if($(this).index()==1) {
            $("#find-id-tel").css("display","block");
            $("#find-id-email").css("display","none");
        } else {
            $("#find-id-tel").css("display","none");
            $("#find-id-email").css("display","block");
        }

        $('.select-menu li').eq($(this).index()).css("border","1px solid gray");
    });


});