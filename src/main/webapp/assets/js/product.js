// product.js

$(function () {
    $(".main_menu a:nth-child(3)").addClass("active");
    /*검색 버튼 누르면 함수 실행*/



    $("#search_pro").click(function () {
        $(".product_search").css("display", "block");

        $("#pro_search_close").click(function () {
            $(".product_search").css("display", "");
        })
    })
    $("#pro_keyword").keydown(function (e) {
        if (e.keyCode == 13) $("#pro_search_btn").trigger("click");
    })
    
    /* 추가 기능 */
    $("#add_product").click(function () {
        $(".popup_wrap").addClass("open");
        $("#add_pro").css("display", "inline-block");
        $("#modify_pro").css("display", "none");
        $("#cancel_pro").css("display", "inline-block");

        $(".poppup .top_area h2").html("제품 추가")
        $(".poppup .top_area p").html("제품 정보를 입력해 주세요.")
    })

    $("#add_pro").click(function () {
        if (confirm("제품을 등록하시겠습니까?") == false) return;
        let pi_ci_seq = $("#pi_ci_seq").attr("data-dep-seq");
        let pi_name = $("#pi_name").val();
        let pi_price = $("#pi_price").val();
        let pi_introduce = $("#pi_introduce").val();
        let pi_stock = $("#pi_stock").val();
        // let pi_ci_seq = $("#pi_ci_seq option:selected").val();
        let pi_status = $("#pi_status option:selected").val();


        if (pi_ci_seq == "") {
            alert("카테고리를 입력해주세요.");
            return;
        }

        if (pi_name == "") {
            alert("제품명을 입력해주세요.");
            return;
        }
        if (pi_price == "") {
            alert("제품 가격을 입력해주세요.");
            return;
        }
        if (pi_introduce == "") {
            alert("제품 설명을 입력해주세요.");
            return;
        }
        if (pi_stock == "") {
            alert("재고를 입력해주세요.");
            return;
        }
        if (pi_status == "") {
            alert("제품 상태를 입력해주세요.");
            return;
        }


        let data = {
            pi_ci_seq:pi_ci_seq,
            pi_name: pi_name,
            pi_price: pi_price,
            pi_introduce: pi_introduce,
            pi_stock: pi_stock,
            pi_ci_seq: pi_ci_seq,
            pi_status: pi_status
        }

        console.log(JSON.stringify(data))

        $.ajax({
            type: "post",
            url: "/product/add",
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
        $("#pi_name").val("");
        $("#pi_price").val("");
        $("#pi_introduce").val("");
        $("#pi_stock").val("");
        $("#pi_ci_seq").val("");
        $("#pi_status").val("1").prop("selected", true);

        $(".popup_wrap").removeClass("open");
    })

    $(".delete_btn").click(function () {
        if (confirm("제품을 삭제하시겠습니까?\n(이 동작은 되돌릴 수 없습니다.)") == false) return;
        let seq = $(this).attr("data-seq");

        $.ajax({
            type: "delete",
            url: "/product/delete?seq=" + seq,
            success: function (r) {
                alert(r.message);
                location.reload();
            }
        })
    });




    /* 검색 버튼 누르고 나서 닫기 버튼 클릭시 실행 */

    $("#pro_search_btn").click(function () {
        $.ajax({
            url: "/product/keyword?keyword=" + $("#pro_keyword").val(),
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
                    $("#pi_ci_seq").attr("data-dep-seq", seq);
                    $("#pi_ci_seq").val(name);

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

        location.href = "/product?type=" + type + "&keyword=" + keyword;
    })

    $("#keyword").keydown(function (e) {
        if (e.keyCode == 13) $("#search_btn").trigger("click");
    })





    /* 초기화 버튼 */
    $("#reset_btn").click(function () {
        let type = $("#search_type option:selected").val("1").prop("selected", true);
        let keyword = $("#keyword").val("");

        location.href = "/product";
    })



    $(".delete_btn").click(function () {
        // if (!confirm("삭제하시겠습니까?")) return
        let seq = $(this).attr("data-seq");

        $.ajax({
            url: "/product/delete?seq=" + seq,
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
            url: "/product/get?seq=" + seq,
            type: "get",
            success: function (r) {
                console.log(r)
                $(".popup_wrap").css("display", "block");
                $("#add_pro").css("display", "none");
                $("#modify_pro").css("display", "inline-block");
                $("#cancel_pro").css("display", "inline-block");
                $(".popup .top_area h2").html("제품 수정");
                $(".popup .top_area p").html("수정할 정보를 입력하세요");

                $("#pi_ci_seq").attr("data-dep-seq", r.pi_ci_seq);
                $("#pi_ci_seq").val(r.pi_ci_seq);
                $("#pi_name").val(r.pi_name);
                $("#pi_price").val(r.pi_price);
                $("#pi_introduce").val(r.pi_introduce);
                $("#pi_stock").val(r.pi_stock);
                // $("#pi_ci_seq").val(r.pi_ci_seq);
                $("#pi_status").val(r.pi_status).prop("selected", true);
            }
        })
    })
    $("#modify_pro").click(function () {
        if (confirm("수정하시겠습니까?") == false) return;
        let data = {
            pi_seq: modify_seq,
            pi_ci_seq: $("#pi_ci_seq").attr("data-dep-seq"),
            pi_name: $("#pi_name").val(),
            pi_price: $("#pi_price").val(),
            pi_introduce: $("#pi_introduce").val(),
            pi_stock: $("#pi_stock").val(),
            pi_status: $("#pi_status option:selected").val()
        }
        $.ajax({
            url: "/product/modify",
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