
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="en" >
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Thêm mới nhân viên</title>
</head>
<body>
<%@ include file="..//common/header.jsp"%>
<div class="container">
    <h1 class="text-center">Thêm mới nhân viên</h1>
    <h3 class="text-danger">${message}</h3>
    <form action="employees?action=create" method="post">
        <div class="mb-3">
            <label for="name" class="form-label">Họ tên:</label>
            <input type="text" class="form-control" id="name" name="name" value="${name}">
            <p class="text-danger">${errorMessage.name}</p>
        </div>
        <div class="mb-3">
            <label for="birthDate" class="form-label">Ngày sinh:</label>
            <input type="date" class="form-control" id="birthDate" name="birthDate" value="${birthDate}">
            <p class="text-danger">${errorMessage.birthDate}</p>
        </div>
        <div class="mb-3">
            <label class="form-check-label">Giới tính:</label>
            <select class="form-select" aria-label="Default select example" name="gender">
                <option value="true">Nam</option>
                <option value="flase">Nữ</option>
            </select>
        </div>
        <div class="mb-3">
            <label for="salary" class="form-label">Lương:</label>
            <input type="text" class="form-control" id="salary" name="salary"  value="${salary}">
            <p class="text-danger">${errorMessage.salary}</p>
        </div>
        <div class="mb-3">
            <label for="phoneNumber" class="form-label">Số điện thoại:</label>
            <input type="text" class="form-control" id="phoneNumber" name="phoneNumber"  value="${phoneNumber}">
            <p class="text-danger">${errorMessage.phoneNumber}</p>
        </div>
        <div class="mb-3">
            <label for="departmentId" class="form-label">Bộ phận:</label>
            <select class="form-select" id="departmentId" name="departmentId">
                <option value="">Chọn bộ phận</option>
                <c:forEach var="department" items="${departmentList}">
                    <option value="${department.id}">${department.name}</option>
                </c:forEach>

            </select>
            <p class="text-danger">${errorMessage.departmentId}</p>
        </div>

        <a href="/employees" class="btn btn-danger">Hủy</a>
        <button type="submit" class="btn btn-primary">Lưu</button>
    </form>
</div>

<%@ include file="..//common/footer.jsp"%>
</body>
</html>
