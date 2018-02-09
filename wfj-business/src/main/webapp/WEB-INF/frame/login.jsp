<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="${ctx }/assets/img/sd/fav.ico" type="image/x-icon">
    <link rel="shortcut icon" href="${ctx }/assets/img/sd/fav.ico" type="image/x-icon">
    <title>登录</title>
    <%@ include file="../pages/common/meta.jsp" %>
</head>
<body class="gray-bg">
<div class="middle-box text-center loginscreen  animated fadeInDown">
    <div>
        <div>
            <!-- <h1 class="logo-name">100</h1> -->
        </div>
        <h3>欢迎使用亿百分管理系统</h3>

        <form class="ec" role="form" action="http://localhost:8080/bpm/frame/index.html">
            <div class="form-group">
                <input type="text" id="userName" value="sunpanhu" class="form-control" placeholder="用户名">
            </div>
            <div class="form-group">
                <input type="password" id="password" value="123321" class="form-control" placeholder="密码">
            </div>
            <button type="button" id="login" class="btn btn-primary block full-width m-b">登 录</button>
            <!--
            <p class="text-muted text-center"> <a href="login.html#"><small>忘记密码了？</small></a> | <a href="register.html">注册一个新账号</a>
             -->
            </p>
        </form>
    </div>
</div>

<script type="text/javascript">
    if (window.top !== window.self) {
        window.top.location = window.location;
    }
    $(function () {
        $("#login").on("click", function () {
            var userName = $("#userName").val();
            var password = $("#password").val();
            if (userName == "") {
                alert("请输入您的用户名!");
                return;
            }
            if (password == "") {
                alert("请输入您的密码!");
                return;
            }
            var data = {
                userName: userName,
                password: password
            };
            ajax.post("${ctx}/acl/login", {
                userName: userName,
                password: password
            }, function (data) {
                window.location.href = "${ctx}/acl";
            }, function (data) {
                alert(data.message);
            });
        });
    });
    document.onkeydown = function (event) {
        var e = event || window.event || null;
        if (e.keyCode == 13) {
            document.getElementById('login').click();
        }
    }
</script>
</body>
<!-- Mirrored from www.zi-han.net/theme/hplus/login.html by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:18:23 GMT -->
</html>
