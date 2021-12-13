<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>학과시스템 - 회원관리</title>
    <%@include file = "/WEB-INF/includes/header.jsp"%>
    <link rel="stylesheet" href="/assets/css/member.css">
    <script>
        $(function() {
            $(".main_menu a:nth-child(2)").addClass("active")
        })
    </script>
    <script src="/assets/js/member.js"></script>

</head>
<body>
    <main>
        <h1><i class="fas fa-user-friends"></i> 회원 관리</h1>
        <button id="add_member"><i class="fas fa-plus-circle"></i> 회원 추가</button>
        <div class="content_area">
            <div class="menu_area">
                <div class="search_box">
                    <input type="text" id="keyword" placeholder="검색어 입력">
                    <button id="search_btn"><i class="fas fa-search"></i></button>
                    </div>
                    <button id="reset_btn">초기화</button>
                </div>
            
            <div class="table_area">
                    <table>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>회원이름</th>
                                <th>아이디</th>
                                <th>비밀번호</th>
                                <th>이메일</th>
                                <th>생년월일</th>
                                <th>회원상태</th>
                                <th>성별</th>
                                <th>회원 등록일</th>
                                <th>회원 수정일</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:if test="${data.total == 0}">
                            <tr>
                                <td id="nodata" colspan="9">데이터가 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach items="${data.list}" var="c">
                            <tr>
                                <td>${c.c_seq}</td>
                                <td>${c.c_name}</td>
                                <td>${c.c_id}</td>
                                <td>****</td>
                                <td>${c.c_email}</td>
                                <td>${c.c_birth}</td>
                                <td>${c.c_status}</td>
                                <td>${c.c_gen}</td>
                                <td>${c.c_reg_dt}</td>
                                <td>${c.c_mod_dt}</td>
                                <td>
                                    <button class="modify_btn" data-seq="${c.c_seq}"><i class="fas fa-pencil-alt"></i></button>
                                    <button class="delete_btn" data-seq="${c.c_seq}"><i class="fas fa-minus-circle"></i></button>
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
                        <a href="/member?offset=${(i-1)*10}">${i}</a>
                    </c:forEach>
                </div>
                <button id="next"><i class="fas fa-chevron-right"></i></button>
            </div>
        </div>
    </main>

    <div class="popup_wrap">
    <div class="popup" id="member_add">
        <div class="top_area">
            <div class="ico"><i class="fas fa-user-friends"></i> </div>
            <h2>회원 추가</h2>
            <p>회원 정보를 입력해주세요</p>
        </div>

        <div class="content_area">
            <input type="text" id="c_name" placeholder="회원 이름">
            <input type="text" id="c_id" placeholder="회원 아이디">
            <input type="password" id="c_pwd" placeholder="비밀번호">
            <input type="text" id="c_email" placeholder="회원 이메일">
            <input type="text" id="c_birth" placeholder="회원 생년월일">
            <select id="c_status">
                <option value="1">정상</option>
                <option value="2">탈퇴 대기</option>
                <option value="3">탈퇴 완료</option>
            </select>
            <select id="c_gen">
                <option value="1">남</option>
                <option value="2">여</option>
            </select>
        </div>
        <div class="btn_area">
            <button id="add_mem">등록하기</button>
            <button id="cancel_mem">취소하기</button>
        </div>
    </div>
</div>
</body>
</html>