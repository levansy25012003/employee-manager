
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Chi tiết nhân viên</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">

</head>
<body>
    <%@ include file="..//common/header.jsp"%>
    <div class="container">
        <h1 class="text-center">Chi Tiết nhân viên</h1>
        <div class="row">
            <div class="col-4"></div>
            <div class="col-4">
                <div class="row">
                    <div class="col">
                        <p>Mã nhân viên:</p>
                        <p>Họ tên:</p>
                        <p>Ngày sinh:</p>
                        <p>Giới tính</p>
                        <p>Lương:</p>
                        <p>Số điện thoại:</p>
                        <p>Bộ phận:</p>
                        <p></p>
                    </div>
                    <div class="col">
                        <p>${employee.id}</p>
                        <p>${employee.name}</p>
                        <p>${employee.birthDate}</p>
                        <p>${employee.gender ? "Nam" : "Nữ"}</p>
                        <p>
                            <fmt:setLocale value="vi_VN"/>
                            <fmt:formatNumber value="${employee.salary}" pattern="#,##0.00₫"/>
                        </p>
                        <p>${employee.phoneNumber}</p>
                        <p>
                            <c:forEach var="department" items="${departmentList}">
                                <c:if test="${department.id == employee.departmentId}">
                                    ${department.name}
                                </c:if>
                            </c:forEach>
                        </p>
                        <p> </p>
                        <a href="/employees" class="btn btn-danger">Trở về</a>
                    </div>
                </div>
            </div>
            <div class="col-4"></div>
        </div>
    </div>
    <%@ include file="..//common/footer.jsp"%>
</body>
</html>
