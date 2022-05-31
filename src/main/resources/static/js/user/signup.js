
function Check(){
    let id = $("#userId").val()
    if(id.search(/\s/) != -1) {
        Swal.fire("아이디에 공백이 포함되어 있습니다.");
    } else {
        if(id.trim().length!=0) {
            $.ajax({
                async : true,
                type : 'POST',
                data: id,
                url : "/user/idCheck",
                dataType: "json",
                contentType: "application/json; charset=UTF-8",

                success : function(count) {
                    if(count > 0) {
                        Swal.fire({
                            icon: 'error',
                            text: '아이디가 이미 존재합니다.'
                        }).then((result) => {
                        });
                    } else {
                        Swal.fire({
                            icon:'success',
                            text:"사용가능한 아이디입니다."});
                        $("input[name=checked_id]").val('y');
                    }
                },
                error : function(erropr) {
                    Swal.fire({
                       icon: 'error',
                       text: '아이디를 입력해주세요.'
                    });
                }


            });

        } else {
            Swal.fire({
               icon:'error',
               text: '중복확인할 아이디를 입력해주세요.'
            });
        }
    }
}

$(function(){
    $("#btSignup").click(function(){
        if($("input[name='checked_id']").val()==''){
            Swal.fire({
                icon:'error',
                text:'아이디 중복확인을 해주세요.'
            });
            $("input[name='checked_id']").eq(0).focus();
            return false;
        }
    var userid = document.getElementById("userId");
        if(userid.value === "") {
            Swal.fire({
                icon:'error',
                text:'아이디 입력해주세요'
            });
            userid.focus();
            return false;
        }
        var pwd = document.getElementById("password");
        if (pwd.value == "") {
            Swal.fire({
                icon:'error',
                text:'비밀번호를 입력해주세요.'
            });
            pwd.focus();
            return false;
        }

        var pwd2 = document.getElementById("passwordCheck");
        if (pwd2.value != pwd.value) {
            Swal.fire({
                icon:'error',
                text:'비밀번호가 동일하지 않습니다.'
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
                text:'이메일 형식에 맞게 입력해주세요.'
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
                text:'휴대전화 형식에 맞게 입력해주세요.'
            });
            tel.focus();
            return false;
        }

        return true;

    });



});
