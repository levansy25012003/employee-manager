
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" integrity="sha512-z3gLpd7yknf1YoNbCzqRKc4qyor8gaKU1qmn+CShxbuBusANI9QpRohGBreCFkKxLhei6S9CQXFEbbKuqLg0DA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <title>Login</title>
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6">
                <div class="row mt-3">
                    <div class="col-6">
<%--                        <img src="https://banner2.cleanpng.com/20180807/ctb/kisspng-computer-icons-login-vector-graphics-illustration-login-svg-png-icon-free-download-458466-onlin-5b69f8794a6d12.5628700615336715453049.jpg" alt="loi" width="100%">--%>
                        <img src="https://bloghomestay.vn/wp-content/uploads/2023/01/top-99-anh-gai-cute-de-thuong-dang-yeu-gay-thuong-nho_4.jpg" alt="loi" width="100%" height="80%">
                    </div>
                    <div class="col-6 p-4">
                        <form action="login" method="post">
                            <div class="mb-4 mt-4">
                                <p class="text-danger">${param.message}</p>
                                <h2>Đăng nhập</h2>
                                <p class="text-danger">${message}</p>
                            </div>
                            <div class="mb-3">
                                <input type="text" value="${username}" class="form-control" id="username" placeholder="Tên tài khoản" name="username">
                                <p class="text-danger"></p>
                            </div>
                            <div class="mb-4">
                                <input type="password" class="form-control" id="password" placeholder="Mật khẩu" name="password">
                                <p class="text-danger"></p>
                            </div>
                            <div class="mb-2 form-check">
                                <input type="checkbox" class="form-check-input" id="remember-me" value="on" name="remember-me">
                                <label class="form-check-label" for="remember-me">Lưu đăng nhập</label>
                            </div>
                            <button type="submit" class="btn btn-primary w-100">Đăng nhập</button>
                        </form>
                    </div>
                </div>

            </div>
            <div class="col-3"></div>
        </div>
    </div>
</body>
</html>
