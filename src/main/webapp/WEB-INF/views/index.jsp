<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마켓 관리 페이지</title>
    <%@include file = "/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/index.css">
    <script>
        $(function(){
            $(".main_menu a:nth-child(1)").addClass("active")
        })
    </script>
</head>
<body>
        <main>
        <h1> <i class="fas fa-store"></i>   마켓관리 (Market Management)</h1>
        <div class="content_area">
            <div class="customer">
                <h2><i class="fas fa-user-friends"></i>회원 정보</h2>
                <p>총 가입 회원 : <span>${cnt.customer[0]}명</span></p>
                <p>일반 상태 회원 : <span>${cnt.customer[1]}명</span></p>
                <p>로켓 와우 회원 : <span>${cnt.customer[2]}명</span></p>
                <p>정상 상태 회원 : <span>${cnt.customer[3]}명</span></p>
                <p>탈퇴 대기 회원 : <span>${cnt.customer[4]}명</span></p>
                <p>탈퇴 완료 회원 : <span>${cnt.customer[5]}명</span></p>
                <p><i class="far fa-clock"></i>업데이트 날짜 : <span><fmt:formatDate value="${update.member}" pattern="yyyy년 MM월 dd일 (EE) HH:mm:ss"/></span></p>
            </div>

            <div class="product_info">
                <h2><i class="fab fa-product-hunt"></i>제품 정보</h2>
                <p>총 제품 개수 : <span>${cnt.product[0]}개</span></p>
                <p>판매 중 제품 : <span>${cnt.product[1]}개</span></p>
                <p> 상태 제품 : <span>${cnt.product[2]}개</span></p>
                <p>삭제 상태 제품 : <span>${cnt.product[3]}개</span></p>
                <p><i class="far fa-clock"></i>업데이트 날짜 : <span><fmt:formatDate value="${update.product}" pattern="yyyy년 MM월 dd일 (EE) HH:mm:ss"/></span></p>
            </div>
            <div class="rocket_product_info">
                <h2><i class="fas fa-rocket"></i>로켓배송 제품 정보</h2>
                <p>총 로켓제품 개수 : <span>${cnt.rocket_product[0]}개</span></p>
                <p>판매 중 제품 : <span>${cnt.rocket_product[1]}개</span></p>
                <p>품절 상태 제품 : <span>${cnt.rocket_product[2]}개</span></p>
                <p>삭제 상태 제품 : <span>${cnt.rocket_product[3]}개</span></p>
                <p><i class="far fa-clock"></i>업데이트 날짜 : <span><fmt:formatDate value="${update.r_product}" pattern="yyyy년 MM월 dd일 (EE) HH:mm:ss"/></span></p>
            </div>
            

            <div class="order_info">
                <h2><i class="fas fa-funnel-dollar"></i>주문 정보</h2>
                <p>총 제품 판매 수 : <span>${cnt.order[0]}개</span></p>
                <p>제품 반품 대기 수 : <span>${cnt.order[1]}개</span></p>
                <p>제품 교환 대기 수 : <span>${cnt.order[2]}개</span></p>
                <p>제품 구매 확정 수 : <span>${cnt.order[3]}개</span></p>
                <p><i class="far fa-clock"></i>업데이트 날짜 : <span>2021-12-09 12:00:00</span></p>
            </div>
            <div class="rocket_order_info">
                <h2><i class="fas fa-rocket"></i>로켓제품 주문 정보</h2>
                <p>총 제품 판매 수 : <span>${cnt.rocket_order[0]}개</span></p>
                <p>제품 반품 대기 수 : <span>${cnt.rocket_order[1]}개</span></p>
                <p>제품 교환 대기 수 : <span>${cnt.rocket_order[2]}개</span></p>
                <p>제품 구매 확정 수 : <span>${cnt.rocket_order[3]}개</span></p>
                <p><i class="far fa-clock"></i>업데이트 날짜 : <span>2021-12-09 12:00:00</span></p>
            </div>

            <div class="event">
                <h2><i class="fas fa-calendar-day"></i>이벤트 정보</h2>
                <p>총 이벤트 수 : <span>${cnt.event[0]}개</span></p>
                <p>진행 중 이벤트 : <span>${cnt.event[1]}개</span></p>
                <p>만료 된 이벤트 : <span>${cnt.event[2]}개</span></p>
                <p><i class="far fa-clock"></i>업데이트 날짜 : <span>2021-12-09 12:00:00</span></p>
            </div>
        </div>
        </main>
</body>
</html>