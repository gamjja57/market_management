<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마켓 관리 시스템 - 제품관리</title>
    <%@include file="/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/member.css">
    <link rel="stylesheet" href="/assets/css/product.css">
    <script src="/assets/js/product.js"></script>  
</head>
<body>
    <main>
        <h1><i class="fab fa-product-hunt"></i> 제품 관리</h1>
        <button id="add_product"><i class="fas fa-plus-circle"></i> 제품 추가 </button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">

                    <select id="search_type">
                        <option value="category">카테고리</option>
                        <option value="name"
                            <c:if test="${data.type == 'name'}">selected</c:if>
                        >제품명</option>
                    </select>

                    
                    <input type="text" id="keyword" placeholder="검색어 입력" value="${data.keyword}">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                
                </div>
                <button id="reset_btn">초기화</button>
            </div>
            <div class="table_area">
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>카테고리</th>
                                <th>제품 명</th>
                                <th>제품 가격</th>
                                <th>제품 소개</th>
                                <th>재고</th>
                                <th>상태</th>
                                <th>등록일</th>
                                <th>수정일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <c:if test="${data.list.size() == 0}">
                                <td id="nodata" colspan="9">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        
                        <c:forEach items="${data.list}" var="p">
                            <tr> 
                                <td>${p.pi_seq}</td>
                                <td>${p.category_name}</td>
                                <td>${p.pi_name}</td>
                                <td>${p.pi_price}</td>
                                <td>${p.pi_introduce}</td>
                                <td>${p.pi_stock}</td>
                                <td class = "product_status">
                                    <c:if test="${p.pi_status == 1}">
                                        <span style="background-color: rgb(80, 228, 88);">판매</span>
                                    </c:if>
                                    <c:if test="${p.pi_status == 2}">
                                        <span style="background-color: rgb(250, 237, 52);">품절</span>
                                    </c:if>
                                    <c:if test="${p.pi_status == 3}">
                                        <span style="background-color: rgb(251, 64, 64);">삭제</span>
                                    </c:if>
                                </td>
                                <td>${p.pi_reg_dt}</td>
                                <td>${p.pi_mod_dt}</td>
                                <!-- <td>${p.pi_status}</td> -->
                                
                                
                                <td>
                                    <button class="modify_btn" data-seq="${p.pi_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${p.pi_seq}"><i class="fas fa-minus-circle"></i></button>
                                </td>
                            </tr>
                        </c:forEach>

                        </tbody>
                    </table>
            </div>
            <div class="pager_area">
                <button id="prev"><i class="fas fa-chevron-left"></i></button>
                <div class="pagers">
                    <c:forEach begin="1" end="${data.pageCnt}" var="i">
                        <a href="/product?offset=${(i-1)*10}&keyword=${keyword}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>

    <div class="popup_wrap">
    <div class="popup" id="product_add">
        <div class="top_area">
            <div class="ico"><i class="fab fa-product-hunt"></i></div>
            <h2>제품 추가</h2>
            <p>제품 정보를 입력해주세요</p>
        </div>

        <div class="content_area">
            <input type="text" id="pi_name" placeholder="제품 명">
            <input type="number" id="pi_price" placeholder="제품 가격">
            <input type="text" id="pi_introduce" placeholder="제품 소개">
            <input type="text" id="pi_stock" placeholder="재고">
            <input type="text" id="pi_ci_seq" placeholder="카테고리" disabled>
            <button id="search_pro">검색</button>
            <br>
                <!-- <option value="1">과일.채소</option>
                <option value="2">해산물</option>
                <option value="3">정육.계란</option>
                <option value="4">음료</option>
                <option value="5">스낵</option>
                <option value="6">베이커리</option>
                <option value="7">건강식품</option>
                <option value="8">생활용품</option>
                <option value="9">가전제품</option> -->
            </select>
            <select id="pi_status">
                <option value="1">판매 중</option>
                <option value="2">품절 상태</option>
                <option value="3">삭제 상태</option>
            </select>
        </div>
        <div class="btn_area">
            <button id="add_pro">등록하기</button>
            <button id="modify_pro">수정하기</button>
            <button id="cancel_pro">취소하기</button>
        </div>
    </div>
</div>

    <div class="product_search">
        <div class="pro_search_box">
            <input type="text" id="pro_keyword" placeholder="예) 과일, 해산물, ...">
            <button id="pro_search_btn"><i class="fas fa-search"></i></button>
        </div>
        <div class="search_result"> 
            <ul>
                <!-- <li>
                    <a href="#" data-dep-seq="1">과일.채소</a>
                    <span class="status2">통합예정</span>
                </li>
                <li>
                    <a href="#" data-dep-seq="2">수산.해산물</a>
                </li>
                <li>
                    <a href="#" data-dep-seq="3">정육.계란</a>
                </li>
                <li>
                    <a href="#" data-dep-seq="4">음료</a>
                </li>
                <li>
                    <a href="#" data-dep-seq="5">스낵</a>
                </li>
                <li>
                    <a href="#" data-dep-seq="6">베이커리</a>
                </li>
                <li>
                    <a href="#" data-dep-seq="7">건강식품</a>
                </li>
                <li>
                    <a href="#" data-dep-seq="8">생활용품</a>
                </li>
                <li>
                    <a href="#" data-dep-seq="9">가전제품</a>
                </li> -->
            </ul>
        </div>
        <div class="pro_search_buttons">
            <button id="pro_search_close">닫기</button>
        </div>
    </div>
</body>
</html>
