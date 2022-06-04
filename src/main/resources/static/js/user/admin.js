$(function() {

    $(".adminMenu ul li").click(function(){

        for(let i=0; i<4 ;i++){
            $(".adminData > div").eq(i).css("display", "none");
        }
        $(".adminData > div").eq($(this).index()).css("display", "block");


        console.log($(this).index());
    });

})
