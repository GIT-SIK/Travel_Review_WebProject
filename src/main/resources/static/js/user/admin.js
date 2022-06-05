$(function() {

    /* 페이지 변경 시 주소창에 page= 가 존재할 경우 그 창 고정 */
    if($(location).attr('href').indexOf('?page=') >= 0) {
        for(let i=0; i<4 ;i++){
            $(".adminData > div").eq(i).css("display", "none");
        }
        $(".adminData > div").eq(2).css("display", "block");
    };

    $(".adminMenu ul li").click(function(){
        for(let i=0; i<4 ;i++){
            $(".adminData > div").eq(i).css("display", "none");
        }
        $(".adminData > div").eq($(this).index()).css("display", "block");

    });
})



function deleteBoard(idx, userId) {
    event.currentTarget.parentNode.parentNode.style.display="none";
    $.ajax({
        type : "POST",
        url : "/board/userDelete",
        data : {idx: idx, userId: userId},
        success : function(data){
            if(data){
                Swal.fire({
                    icon:'success',
                    html:'게시물이 삭제되었습니다.',
                    showConfirmButton: true,
                    confirmButtonColor: '#3085d6',
                    confirmButtonText : '확인'
                })
            } else {
                Swal.fire({
                    icon: 'error',
                    html: '게시물이 삭제도중 오류가 발생하였습니다.',
                    showConfirmButton: false,
                    showCancelButton: true,
                    cancelButtonColor: '#F63543',
                    cancelButtonText : '확인'
                })
            }
        },
        error : function(error) {
            Swal.fire({
                icon: 'error',
                html: '게시물이 삭제도중 오류가 발생하였습니다.',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            })
        }
    });

};
