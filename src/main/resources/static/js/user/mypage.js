$(function() {

    /* 페이지 변경 시 주소창에 page= 가 존재할 경우 그 창 고정 */
    if($(location).attr('href').indexOf('?page=') >= 0) {
        for(let i=0; i<2 ;i++){
            $(".mypageData > div").eq(i).css("display", "none");
        }
        $(".mypageData > div").eq(1).css("display", "block");
    };

    /* 메뉴 버튼 누를 때 마다 none , block 값 설정 */
    $(".mypageMenu ul li").click(function(){

        if($(this).index() < 2){

        for(let i=0; i<2 ;i++){
            $(".mypageData > div").eq(i).css("display", "none");
        }
        $(".mypageData > div").eq($(this).index()).css("display", "block");

        }

    });

    var pw = document.getElementById("userPassword");
    var pwc = document.getElementById("userPasswordCheck");
    $("#btUpdateUser").click(function(){
        if(pw.value != pwc.value) {
            Swal.fire({
               icon:'error',
               html: '비밀번호가 일치하지 않습니다.' ,
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            });

            return false;
        } else {
                    Swal.fire({
                        icon:'success',
                        html:'내 정보가 변경되었습니다.',
                        showConfirmButton: true,
                        confirmButtonColor: '#3085d6',
                        confirmButtonText : '확인'
                    })
            return true;
        }
    });





})

function deleteUser(){

    Swal.fire({
        icon:'warning',
        html:'회원탈퇴를 하시겠습니까?',
        showConfirmButton: true,
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        confirmButtonText : '탈퇴',
        cancelButtonColor: '#F63543',
        cancelButtonText : '취소'
    }).then(function(result){
        if(result.value) {
            $.ajax({
                type: "GET",
                url: "/user/mypage/delete",
                success : function(){
                    location.href = '/';
                }
            });
        }
    })

}


function deleteBoard(idx, userId) {
    event.currentTarget.parentNode.parentNode.style.display="none";
    $.ajax({
        type : "POST",
        url : "/board/userDelete",
        data : {idx: idx,
            userId: userId},
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