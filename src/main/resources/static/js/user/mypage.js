$(function() {

    $(".mypageMenu ul li").click(function(){

        for(let i=0; i<3 ;i++){
            $(".mypageData > div").eq(i).css("display", "none");
        }
        $(".mypageData > div").eq($(this).index()).css("display", "block");


        console.log($(this).index());
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