// member.js
$(function() {
    $(".main_menu a:nth-child(2)").addClass("active");

    /* 추가 기능 */
    $("#add_member").click(function(){
        $(".popup_wrap").addClass("open");
        $("#add_mem").css("display", "inline-block");
        $("#modify_mem").css("display", "none");

        $(".poppup .top_area h2").html("회원 추가")
        $(".poppup .top_area p").html("회원 정보를 입력해 주세요.")
    })

    $("#add_mem").click(function(){
        if(confirm("회원을 등록하시겠습니까?")==false) return;
        let mem_name = $("#c_name").val();
        let mem_id = $("#c_id").val();
        let mem_pwd = $("#c_pwd").val();
        let mem_email = $("#c_email").val();
        let mem_birth = $("#c_birth").val();
        let mem_status = $("#c_status option:selected").val();
        let mem_level = $("#c_level option:selected").val();
        let mem_gen = $("#c_gen option:selected").val();
        
        let data = {
            c_name:mem_name,
            c_id:mem_id,
            c_pwd:mem_pwd,
            c_email:mem_email,
            c_birth:mem_birth,
            c_status:mem_status,
            c_level:mem_level,
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
    


    /* 취소 기능 */
    $("#cancel_mem").click(function(){
        if(confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)")==false) return;
        $("#c_name").val("");
        $("#c_id").val("");
        $("#c_email").val("");
        $("#c_pwd").val("");
        $("#c_birth").val("");
        $("#c_status").val("1").prop("selected", true);
        $("#c_level").val("1").prop("selected", true);
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
    });



    /* 수정기능 */ 
    let modify_btn_seq = 0;


    $(".modify_btn").click(function() {
        // alert($(this).attr("data-seq"))
        modify_btn_seq = $(this).attr("data-seq");
        $(".popup_wrap").addClass("open");
        $("#add_mem").css("display", "none");
        $("#modify_mem").css("display", "inline-block");

        $(".poppup .top_area h2").html("회원 수정")
        $(".poppup .top_area p").html("수정할 내용을 입력해 주세요.")

        $.ajax({
            type:"get",
            url:"/member/get?seq="+$(this).attr("data-seq"),
            success:function(r) {
                $("#c_name").val(r.data.c_name);
                $("#c_id").val(r.data.c_id);
                $("#c_email").val(r.data.c_email);
                $("#c_pwd").val(r.data.c_pwd);
                $("#c_birth").val(r.data.c_birth);
                $("#c_gen").val(r.data.c_gen).prop("selected", true);
                $("#c_level").val(r.data.c_level).prop("selected", true);
                $("#c_status").val(r.data.c_status).prop("selected", true);
            }
    })
        })

        /* 수정 버튼 기능 넣어주기 */
        $("#modify_mem").click(function(){
            // alert(modify_btn_seq)
            if(confirm("수정하시겠습니까?")==false) return;
        
            let mem_name = $("#c_name").val();
            let mem_id = $("#c_id").val();
            let mem_pwd = $("#c_pwd").val();
            let mem_email = $("#c_email").val();
            let mem_birth = $("#c_birth").val();
            let mem_status = $("#c_status option:selected").val();
            let mem_level = $("#c_level option:selected").val();
            let mem_gen = $("#c_gen option:selected").val();
            
            let data = {
                c_seq:modify_btn_seq,
                c_name:mem_name,
                c_id:mem_id,
                c_pwd:mem_pwd,
                c_email:mem_email,
                c_birth:mem_birth,
                c_status:mem_status,
                c_level:mem_level,
                c_gen:mem_gen
            }

            $.ajax({
                type:"patch",
                url:"/member/update",
                data:JSON.stringify(data),
                contentType:"application/json",
                success:function(r) {
                    alert(r.message);
                    location.reload();
                }
            })
        })
        
        $("#search_btn").click(function() {
            location.href="/member?keyword="+$("#keyword").val();
        })
        $("#keyword").keydown(function(e){
            console.log(e.keyCode)
            if(e.keyCode == 13) {
                $("#search_btn").trigger("click");
            }
        })

/* 초기화 버튼 */
$("#reset_btn").click(function(){
    let type= $("#search_type option:selected").val("1").prop("selected", true);
    let keyword = $("#keyword").val("");

    location.href = "/member";
})

})
