$(function() {

    /*
    $("#btMyBoard").click(function(){
        $(".userInfo").css("display", "none");
        $(".userBoard").css("display", "block");
    });


    $("#btMyInfo").click(function(){
        $(".userInfo").css("display", "block");
        $(".userBoard").css("display", "none");
    });

    $("#btUserDelete").click(function(){
        $(".userInfo").css("display", "none");
        $(".userBoard").css("display", "none");
    });
    */

    $(".mypageMenu ul li").click(function(){

        for(let i=0; i<3 ;i++){
            $(".mypageData > div").eq(i).css("display", "none");
        }
        $(".mypageData > div").eq($(this).index()).css("display", "block");


        console.log($(this).index());
    });

})
