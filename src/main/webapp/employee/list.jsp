
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Danh sách nhân viên</title>
</head>
<body>
    <%@ include file="..//common/header.jsp"%>
    <div class="container">
        <h1>Màn hình danh sách</h1>
    </div>
    <div class="container">
        <div class="row d-plex justify-content-end">
            <div class="col-2">
                <span class="text-primary">${sessionScope.userDetail.username}</span>
                <a class="btn btn-success" href="logout"><i class="fa-solid fa-arrow-right-from-bracket"></i> Đăng xuất</a>
            </div>
        </div>
    </div>
<%-- Phần Tìm kiếm --%>
    <div class="container">
        <form action="employees" method="get">
            <div class="row">
                <div class="col-4">
                    <div class="mb-3">
                        <label for="name" class="form-label">Tên (tì kiếm gần đúng)</label>
                        <input type="text" class="form-control" id="name" name="name" value="${employeeSearchDTO.name}">
                        <p class="text-danger"></p>
                    </div>

                    <div class="mb-3">
                        <label for="gender" class="form-label">Giới tính</label>
                        <select class="form-select" id="gender" name="gender">
                            <option value="">Tất cả</option>
                            <option value="true" ${employeeSearchDTO.gender == 'true' ? 'selected' : ''}>Nam</option>
                            <option value="false" ${employeeSearchDTO.gender == 'false' ? 'selected' : ''}>Nữ</option>
                        </select>
                        <p class="text-danger"></p>
                    </div>
                    <div class="mb-3">
                        <label for="departmentId" class="form-label">Bộ phận</label>
                        <select class="form-select" id="departmentId" name="departmentId">
                            <option value="">Tất cả</option>
                            <c:forEach var="department" items="${departmentList}">
                                <option value="${department.id}" ${employeeSearchDTO.departmentId == department.id ? 'selected' : ''}>${department.name}</option>
                            </c:forEach>
                        </select>
                        <p class="text-danger"></p>
                    </div>
                </div>
                <div class="col-4">
                    <div class="mb-3">
                        <label for="fromBirthDate" class="form-label">Ngày sinh từ</label>
                        <input type="date" class="form-control" id="fromBirthDate" name="fromBirthDate" value="${employeeSearchDTO.fromBirthDate}">
                        <p class="text-danger"></p>
                    </div>
                    <div class="mb-3">
                        <label for="salary" class="form-label">Mưc lương</label>
                        <select class="form-select" id="salary" name="salary">
                            <option value="">Tất cả</option>
                            <option value="lt5" ${employeeSearchDTO.salary == 'lt5' ? 'selected' : ''}>Dưới 5 triệu</option>
                            <option value="5-10" ${employeeSearchDTO.salary == '5-10' ? 'selected' : ''}>Từ 5 đến 10 triệu</option>
                            <option value="10-15" ${employeeSearchDTO.salary == '10-15' ? 'selected' : ''}>Từ 10 đến 15 triệu</option>
                            <option value="gt15" ${employeeSearchDTO.salary == 'gt15' ? 'selected' : ''}>Trên 15 triệu</option>
                        </select>
                        <p class="text-danger"></p>
                    </div>
                </div>
                <div class="col-4">
                    <div class="mb-3">
                        <label for="toBirthDate" class="form-label">Ngày sinh đến</label>
                        <input type="date" class="form-control" id="toBirthDate" name="toBirthDate" value="${employeeSearchDTO.toBirthDate}" >
                        <p class="text-danger"></p>
                    </div>
                    <div class="mb-3">
                        <label for="phoneNumber" class="form-label">Số điện thoại (tìm kiếm gần đúng)</label>
                        <input type="text" class="form-control" id="phoneNumber" name="phoneNumber" value="${employeeSearchDTO.phoneNumber}">
                        <p class="text-danger"></p>
                    </div>
                    <div class="mb-3 d-flex justify-content-end">
                        <button type="reset" class="btn btn-info mt-5"><i class="fa-solid fa-rotate-left"></i> Đặt lại</button>
                        <button type="submit" class="btn btn-primary mt-5"><i class="fa-solid fa-magnifying-glass"></i> Tìm kiếm</button>
                    </div>

                </div>
            </div>
        </form>
    </div>
<%-- Hết tìm kiếm --%>

    <div class="container">
        <h1>Danh sách nhân viên</h1>
        <p class="text-danger">${param.message}</p>
    </div>

    <div class="container text-end mt-2">
        <c:if test="${sessionScope.userDetail.roles.contains('admin')}">
            <a href="?action=create" class="btn btn-success"><i class="fa-solid fa-user-plus"></i> Thêm mới</a>
        </c:if>
    </div>

    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">STT</th>
                    <th scope="col">Tên</th>
                    <th scope="col">Ngày sinh</th>
                    <th scope="col">Giới tính</th>
                    <th scope="col">Lương</th>
                    <th scope="col">Số điện thoại</th>
                    <th scope="col">Bộ phận</th>
                    <th scope="col">Thao tác</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="employee" items="${employeeList}" varStatus="varStatus" >
                    <tr>
                        <th scope="row">${varStatus.count}</th>
                        <td>${employee.name}</td>
                        <td>
                            <fmt:parseDate value="${employee.birthDate}" pattern="yyyy-MM-dd" var="parsedDate"/>
                            <fmt:formatDate pattern="dd/MM/yyyy" value="${parsedDate}"/>
                        </td>
                        <td>${employee.gender ? 'Nam' : 'Nữ'}</td>
                        <td>
                            <fmt:setLocale value="vi_VN"/>
                            <fmt:formatNumber value="${employee.salary}" pattern="#,##0.00₫"/>
                        </td>

                       <td>${employee.phoneNumber}</td>
                       <td>${employee.departmentName}</td>
                        <td>
                            <c:if test="${sessionScope.userDetail.roles.contains('admin')}">
                                <a href="?action=edit&id=${employee.id}" class="btn btn-primary"><i class="fa-solid fa-pencil"></i> Cập nhật</a>
                                <button class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#exampleModal"
                                    onclick="deleteEmployee(${employee.id}, '${employee.name}')">
                                    <i class="fa-solid fa-trash-can"></i> Xóa
                                </button>
                            </c:if>
                            <a href="?action=detail&id=${employee.id}" class="btn btn-info"><i class="fa-solid fa-eye"></i> Chi tiết</a>
                        </td>
                    </tr>
                </c:forEach>

            </tbody>
        </table>
    </div>
    <%-- Modal xóa --%>
    <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h1 class="modal-title fs-5" id="exampleModalLabel">Xác nhận muốn xóa</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body">
                    Bạn có muốn xóa: <span id="nameEmlpoyee" class="text-danger"></span>
                </div>
                <div class="modal-footer">
                    <form action="/employees">
                        <input type="hidden" id="employeeIdDelete"  name="id">
                        <input type="hidden"  value="delete" name="action">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Hủy</button>
                        <button type="submit" class="btn btn-primary">Xóa</button>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <%@ include file="..//common/footer.jsp"%>
    <%-- Bootstrap và jquery  --%>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script>
        function deleteEmployee(id, name) {
            $("#nameEmlpoyee").text(name);
            $("#employeeIdDelete").val(id);
        }
    </script>
</body>
</html>
