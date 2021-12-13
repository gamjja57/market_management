// member.js
$(function() {
    $(".main_menu a:nth-child(2)").addClass("active");
    $("#add_member").click(function(){
        $(".popup_wrap").addClass("open");
    })
    $("#add_mem").click(function(){
        if(confirm("회원을 등록하시겠습니까?")==false) return;
        let mem_name = $("#c_name").val();
        let mem_id = $("#c_id").val();
        let mem_pwd = $("#c_pwd").val();
        let mem_email = $("#c_email").val();
        let mem_birth = $("#c_birth").val();
        let mem_status = $("#c_status option:selected").val();
        let mem_gen = $("#c_gen option:selected").val();
        
        let data = {
            c_name:mem_name,
            c_id:mem_id,
            c_pwd:mem_pwd,
            c_email:mem_email,
            c_birth:mem_birth,
            c_status:mem_status,
            c_gen:mem_gen
        }

        $.ajax({
            type:"post",
            url:"/member/add",
            data:JSON.stringify(data),
            contentType:"application/json",
            success:function(r) {
                alert(r.message);
                if(r.status)
                    location.reload();
            }
        })
        
    })

    $("#cancel_mem").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)")==false) return;
        $("#c_name").val("");
        $("#c_id").val("");
        $("#c_email").val("");
        $("#c_pwd").val("");
        $("#c_birth").val("");
        $("#c_status").val("1").prop("selected", true);
        $("#c_gen").val("1").prop("selected", true);
        
        $(".popup_wrap").removeClass("open");    
    })
    $(".delete_btn").click(function() {
        if(confirm("회원을 삭제하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)") == false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type:"delete",
            url:"/member/delete?seq="+seq,
            success:function(r) {
                alert(r.message);
                location.reload();
            }
        })
    })
    
})
    