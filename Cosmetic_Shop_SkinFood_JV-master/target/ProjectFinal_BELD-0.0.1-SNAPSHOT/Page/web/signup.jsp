<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="php"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta2/css/all.min.css" integrity="sha512-YWzhKL2whUzgiheMoBFwW8CKV4qpHQAEuvilg9FAn5VJUDwKZZxkJNuGM4XkWuk94WCrrwslk8yWNGmY1EduTA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="Page/css/web.css">
    <link rel="stylesheet" href="Page/css/login.css">
    <link rel="stylesheet" href="Page/css/cartMainCss.css">
    <title>Signup Dangu</title>
</head>
<body>
                   
    <%@include file="header.jsp" %>
    <div class="body-login">
        <div class="container">
            <div class="main">
                <form action="signup" method="post" class="form" id="form-signup">
                    <h3 class="login-title">
                        Đăng Kí Thành Viên
                    </h3>
                    <div class="form-group">
                        <input type="text" id="name" name="fullname" placeholder="Tên đầy đủ">
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                        <input type="text" id="surname" name="username" placeholder="Username">
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                        <input type="text" id="email" name="email" placeholder="Email">
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                        <input type="password" id="password" name="password" placeholder="Mât khẩu">
                        <span class="form-message"></span>
                    </div>
                    <div class="form-group">
                        <input type="password" id="repassword" name="repassword" placeholder="Nhập lại mật khẩu">
                        <span class="form-message"></span>
                    </div>
                    <php:if test="${!mess.isEmpty() }">
                    	<p>${mess }</p>
                    </php:if>         
                    <input type="submit" class="btn btn-success" value="Đăng kí" />
                    <a href="home" class="login-not btn ">Quay Về</a>
                </form>
            </div>
        </div>
    </div>
    <%@include file="footer.jsp" %>
</body>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
<script src="Page/js/web.js"></script>
<script>
    Validator({
        form: '#form-signup',
        errorSelector: '.form-message',
        rules: [
            Validator.isRequired('#surname'),
            Validator.isRequired('#name'),
            Validator.isRequired('#email'),
            Validator.isEmail('#email'),
            Validator.minLength('#password', 6),
            Validator.isRequired('#repassword'),
            Validator.isConfirmed('#repassword', function() { 
                return document.querySelector('#form-signup #password').value;
            }, 'Mật khẩu nhập lại phải chính xác')
        ],
        onSubmit: function(data) {
            console.log(data);
        }
    });
</script>
</html>