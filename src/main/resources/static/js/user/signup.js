$(function(){

    $( '#id' ).change( function() {
        $("input[name=checked_id]").val('');
    });

    $("#btSignup").click(function(){
        if($("input[name='checked_id']").val()==''){
            Swal.fire({
                icon:'error',
                text:'아이디 중복확인을 해주세요.',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            });
            $("input[name='checked_id']").eq(0).focus();
            return false;
        }
    var id = document.getElementById("id");
        if(id.value === "") {
            Swal.fire({
                icon:'error',
                text:'아이디 입력해주세요',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            });
            id.focus();
            return false;
        }
        var pwd = document.getElementById("password");
        if (pwd.value == "") {
            Swal.fire({
                icon:'error',
                text:'비밀번호를 입력해주세요.',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            });
            pwd.focus();
            return false;
        }

        var pwd2 = document.getElementById("passwordCheck");
        if (pwd2.value != pwd.value) {
            Swal.fire({
                icon:'error',
                text:'비밀번호가 동일하지 않습니다.',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            });
            pwd2.focus();
            return false;
        }

        var email = document.getElementById("email");
        var emailValue = email.value;
        var reg = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
        if(!reg.test(emailValue) || email==="" ){
            Swal.fire({
                icon:'error',
                html:'이메일 형식에 맞게 입력해주세요. <br><br>ex) oooo@oooo.ooo',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            });
            email.focus();
            return false;
        }

        var tel = document.getElementById("tel");
        var telValue = tel.value;
        var patternPhone = /01[016789]-[^0][0-9]{2,3}-[0-9]{3,4}/;
        if(!patternPhone.test(telValue) || tel==="")
        {
            Swal.fire({
                icon:'error',
                html:'휴대전화 형식에 맞게 입력해주세요.<br><br>ex) 010-oooo-oooo',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            });
            tel.focus();
            return false;
        }
        return true;
    });

});


function checkId(){
    let id = $("#id").val()
    if(id.search(/\s/) != -1) {
        Swal.fire("아이디에 공백이 포함되어 있습니다.");
    } else {
        if(id.trim().length!=0) {
            $.ajax({
                async : true,
                type : 'POST',
                data: id,
                url : "/user/isId",
                /* json 형태가 아니면 "="가 들어가서 json으로 처리할 것.*/
                dataType: "json",
                contentType: "application/json; charset=UTF-8",

                success : function(check) {
                    if(check) {
                        Swal.fire({
                            icon: 'error',
                            text: '아이디가 이미 존재합니다.',
                            showConfirmButton: false,
                            showCancelButton: true,
                            cancelButtonColor: '#F63543',
                            cancelButtonText : '확인'

                        })
                        $("input[name=checked_id]").val('');
                    } else {
                        Swal.fire({
                            icon:'success',
                            html:'사용가능한 아이디입니다.<br><br>아이디 변경시 다시 중복 확인 해주세요.',
                            showConfirmButton: true,
                            confirmButtonColor: '#3085d6',
                            confirmButtonText : '확인'
                        });

                        $("input[name=checked_id]").val('true');
                    }
                },
                error : function(erropr) {
                    Swal.fire({
                        icon: 'error',
                        text: '아이디를 입력해주세요.',
                        showConfirmButton: false,
                        showCancelButton: true,
                        cancelButtonColor: '#F63543',
                        cancelButtonText : '확인'
                    });
                }
            });

        } else {
            Swal.fire({
                icon:'error',
                text: '아이디를 입력해주세요.',
                showConfirmButton: false,
                showCancelButton: true,
                cancelButtonColor: '#F63543',
                cancelButtonText : '확인'
            });
        }
    }
}
