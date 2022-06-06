$(function() {

    /* 페이지 변경 시 주소창에 page= 가 존재할 경우 그 창 고정 */
    if($(location).attr('href').indexOf('?page=') >= 0) {
        for(let i=0; i<3 ;i++){
            $(".adminData > div").eq(i).css("display", "none");
        }
        $(".adminData > div").eq(1).css("display", "block");
    };

    $(".adminMenu ul li").click(function(){
        for(let i=0; i<3 ;i++){
            $(".adminData > div").eq(i).css("display", "none");
        }
        $(".adminData > div").eq($(this).index()).css("display", "block");

    });
})

function deleteSlide(idx) {
    event.currentTarget.parentNode.parentNode.style.display="none";
    $.ajax({
        type : "POST",
        url : "/user/admin/slideDelete",
        data : {idx: idx},
        success : function(data){
            if(data){
                Swal.fire({
                    icon:'success',
                    html:'슬라이드가 삭제되었습니다.',
                    showConfirmButton: true,
                    confirmButtonColor: '#3085d6',
                    confirmButtonText : '확인'
                })
            } else {
                Swal.fire({
                    icon: 'error',
                    html: '슬라이드를 삭제 도중 오류가 발생하였습니다.',
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
                html: '슬라이드를 삭제 도중 오류가 발생하였습니다.',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            })
        }
    });

};

function slideAddForm(bool) {
    if(bool){
        $("#slideShowForm > td:nth-child(2)").css("display", "none");
        $("#slideShowForm > td:nth-child(3)").css("display", "block");
        $("#slideAdd").css("display", "block");
    } else {
        $("#slideShowForm > td:nth-child(2)").css("display", "block");
        $("#slideShowForm > td:nth-child(3)").css("display", "none");
        $("#slideAdd").css("display", "none");
    }
}

function deleteBoard(idx, userId) {
    event.currentTarget.parentNode.parentNode.style.display="none";
    $.ajax({
        type : "POST",
        url : "/user/mypage/userBoardDelete",
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
