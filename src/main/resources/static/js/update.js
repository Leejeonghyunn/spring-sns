// (1) 회원정보 수정
function update(userId,event) {

    event.preventDefault() //폼 태그 액션을 막기 - form태그의 action경로를 비활성화 시킨다

    let data = $("#profileUpdate").serialize();
// . #
    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}` ,
        data: data,
        contextType: "application/x-www-form-urlencoded;charset=utf-8",
        dataType: "json"
    }).done(res=> {         //HttpStatus 상태코드 200번대
        console.log("update 성공",res);
        alert("회원정보가 성공적으로 수정되었습니다.");
        location.href = `/user/${userId}`;
    }).fail(error=>{        //HttpStatus 상태코드 200번대가 아닐때
        if(error.data == null){
            alert(error.responseJSON.message);
        } else {
            alert(JSON.stringify(error.responseJSON.data));
        }
    });

 }
