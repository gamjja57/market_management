// r_product.js

$(function(){
    $(".main_menu a:nth-child(3)").addClass("active")

    $("#search_pro").click(function () {
        $(".product_search").css("display", "block");

        $("#pro_search_close").click(function () {
            $(".product_search").css("display", "");
        })
    })
    $("#pro_keyword").keydown(function (e) {
        if (e.keyCode == 13) $("#pro_search_btn").trigger("click");
    })
    
    $("#add_product").click(function () {
        $(".popup_wrap").addClass("open");
        $("#add_pro").css("display", "inline-block");
        $("#modify_pro").css("display", "none");
        $("#cancel_pro").css("display", "inline-block");

        $(".poppup .top_area h2").html("로켓제품 추가")
        $(".poppup .top_area p").html("제품 정보를 입력해 주세요.")
    })

    /* 추가 기능 */
    $("#add_pro").click(function () {
        if (confirm("제품을 등록하시겠습니까?") == false) return;
        let rpi_ci_seq = $("#rpi_ci_seq").attr("data-dep-seq");
        let rpi_name = $("#rpi_name").val();
        let rpi_price = $("#rpi_price").val();
        let rpi_introduce = $("#rpi_introduce").val();
        let rpi_stock = $("#rpi_stock").val();
        // let pi_ci_seq = $("#pi_ci_seq option:selected").val();
        let rpi_status = $("#rpi_status option:selected").val();


        if (rpi_ci_seq == "") {
            alert("카테고리를 입력해주세요.");
            return;
        }

        if (rpi_name == "") {
            alert("제품명을 입력해주세요.");
            return;
        }
        if (rpi_price == "") {
            alert("제품 가격을 입력해주세요.");
            return;
        }
        if (rpi_introduce == "") {
            alert("제품 설명을 입력해주세요.");
            return;
        }
        if (rpi_stock == "") {
            alert("재고를 입력해주세요.");
            return;
        }
        if (rpi_status == "") {
            alert("제품 상태를 입력해주세요.");
            return;
        }


        let data = {
            rpi_ci_seq:rpi_ci_seq,
            rpi_name: rpi_name,
            rpi_price: rpi_price,
            rpi_introduce: rpi_introduce,
            rpi_stock: rpi_stock,
            rpi_ci_seq: rpi_ci_seq,
            rpi_status: rpi_status
        }

        console.log(JSON.stringify(data))

        $.ajax({
            type: "post",
            url: "/rocket/product/add",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (r) {
                alert(r.message);
                if (r.status)
                    location.reload();
            }
        })

    })

       /* 취소 기능 */
        $("#cancel_pro").click(function () {
        if (confirm("취소하시겠습니까?\n(입력된 정보는 저장되지 않습니다.)") == false) return;
        $("#rpi_name").val("");
        $("#rpi_price").val("");
        $("#rpi_introduce").val("");
        $("#rpi_stock").val("");
        $("#rpi_ci_seq").val("");
        $("#rpi_status").val("1").prop("selected", true);

        $(".popup_wrap").removeClass("open");
    })



    $(".delete_btn").click(function () {
        if (confirm("제품을 삭제하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)") == false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type: "delete",
            url: "/rocket/product/delete?seq=" + seq,
            success: function (r) {
                alert(r.message);
                location.reload();
            }
        })
    });




    /* 검색 버튼 누르고 나서 닫기 버튼 클릭시 실행 */

    $("#pro_search_btn").click(function () {
        $.ajax({
            url: "/rocket/product/keyword?keyword=" + $("#pro_keyword").val(),
            type: "get",
            success: function (r) {
                console.log(r);

                $(".search_result ul").html("");
                for (let i = 0; i < r.list.length; i++) {
                    let str_status = "";
                    if (r.list[i].ci_status == 1) str_status = "운영중"
                    if (r.list[i].ci_status == 2) str_status = "수리"
                    if (r.list[i].ci_status == 3) str_status = "폐지예정"
                    if (r.list[i].ci_status == 4) str_status = "폐지"

                    let tag =
                        '<li>' +
                        '<a href="#" data-dep-seq="' + r.list[i].ci_seq + '">' + r.list[i].ci_name + '</a>' +
                        '<span class="status' + r.list[i].ci_status + '">' + str_status + '</sapn>' +
                        '</li>';

                    $(".search_result ul").append(tag);
                }

                $(".search_result ul a").click(function (e) {
                    e.preventDefault(); // a 태그의 링크 기능 제거
                    let seq = $(this).attr("data-dep-seq");
                    let name = $(this).html();
                    // 이쪽이 잘못됐었네요.
                    $("#rpi_ci_seq").attr("data-dep-seq", seq);
                    $("#rpi_ci_seq").val(name);

                    $(".search_result ul").html("");
                    $("#pro_keyword").val("");
                    $(".product_search").css("display", "");
                })
            }
        })
    })



    $("#search_btn").click(function () {
        let type = $("#search_type option:selected").val();
        let keyword = $("#keyword").val();

        location.href = "/rocket/product?type=" + type + "&keyword=" + keyword;
    })

    $("#keyword").keydown(function (e) {
        if (e.keyCode == 13) $("#search_btn").trigger("click");
    })





    /* 초기화 버튼 */
    $("#reset_btn").click(function () {
        let type = $("#search_type option:selected").val("1").prop("selected", true);
        let keyword = $("#keyword").val("");

        location.href = "/rocket/product";
    })



    $(".delete_btn").click(function () {
        // if (!confirm("삭제하시겠습니까?")) return
        let seq = $(this).attr("data-seq");

        $.ajax({
            url: "/rocket/product/delete?seq=" + seq,
            type: "delete",
            success: function (r) {
                //alert(r.message)
                if (r.status) // 삭제 성공시 (r.status 의 값이 true 일 때)
                    location.reload(); // 페이지 새로고침
            }
        })
    })



    let modify_seq = 0;
    $(".modify_btn").click(function () {
        let seq = $(this).attr("data-seq");
        modify_seq = seq;
        $.ajax({
            url: "/rocket/product/get?seq=" + seq,
            type: "get",
            success: function (r) {
                console.log(r)
                $(".popup_wrap").css("display", "block");
                $("#add_pro").css("display", "none");
                $("#modify_pro").css("display", "inline-block");
                $("#cancel_pro").css("display", "inline-block");
                $(".popup .top_area h2").html("제품 수정");
                $(".popup .top_area p").html("수정할 정보를 입력하세요");

                $("#rpi_ci_seq").attr("data-dep-seq", r.rpi_ci_seq);
                $("#rpi_ci_seq").val(r.rpi_ci_seq);
                $("#rpi_name").val(r.rpi_name);
                $("#rpi_price").val(r.rpi_price);
                $("#rpi_introduce").val(r.rpi_introduce);
                $("#rpi_stock").val(r.rpi_stock);
                // $("#pi_ci_seq").val(r.pi_ci_seq);
                $("#rpi_status").val(r.rpi_status).prop("selected", true);
            }
        })
    })
    $("#modify_pro").click(function () {
        if (confirm("수정하시겠습니까?") == false) return;
        let data = {
            rpi_seq: modify_seq,
            rpi_ci_seq: $("#rpi_ci_seq").attr("data-dep-seq"),
            rpi_name: $("#rpi_name").val(),
            rpi_price: $("#rpi_price").val(),
            rpi_introduce: $("#rpi_introduce").val(),
            rpi_stock: $("#rpi_stock").val(),
            rpi_status: $("#rpi_status option:selected").val()
        }
        $.ajax({
            url: "/rocket/product/modify",
            type: "patch",
            data: JSON.stringify(data),
            contentType: "application/json",
            success: function (r) {
                alert(r.message);
                if (r.status)
                    location.reload();
            }
        })
    })
})


