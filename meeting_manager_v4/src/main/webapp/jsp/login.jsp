<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录/注册</title>
    <link rel="stylesheet" type="text/css" href="CSS/login.css">
    <script type="text/javascript" src="JavaScript/login.js"></script>
</head>
<body>
<div class="loginmanage">
    <div class="py-container">
        <h4 class="manage-title">会议管理系统</h4>
        <div class="loginform">
            <ul class="sui-nav nav-tabs tab-wraped">
            </ul>
            <div class="tab-content tab-wraped">
                <%-- 登录提示信息 --%>
                <span style="color: red">${msg}</span>
                <div id="profile" class="tab-pane active">
                    <form id="loginform" class="sui-form" action="${pageContext.request.contextPath}/login"
                          method="post">
                        <div class="input-prepend"><span class="add-on loginname"></span>
                            <input type="text" placeholder="用户名" class="span2 input-xfat" name="username">
                        </div>
                        <div class="input-prepend"><span class="add-on loginpwd"></span>
                            <input type="password" placeholder="请输入密码" class="span2 input-xfat" name="password">
                        </div>
                        <div class="logined">
                            <a class="sui-btn btn-block btn-xlarge btn-danger"
                               href='javascript:document.getElementById("loginform").submit();' target="_self">登&nbsp;&nbsp;录</a>
                        </div>
                    </form>
                    <%-- 注册按钮 --%>
                    <div class="register-link">
                        <a href="javascript:void(0)" onclick="showRegisterForm()">注册</a>
                    </div>
                </div>

                <!-- 注册表单（隐藏） -->
                <div id="registerForm" class="tab-pane" style="display: none;">
                    <form id="registerFormSubmit" class="sui-form" action="${pageContext.request.contextPath}/register" method="post">
                        <div class="input-prepend"><span class="add-on loginname"></span>
                            <input type="text" placeholder="请输入用户名" class="span2 input-xfat" name="username">
                        </div>
                        <div class="input-prepend"><span class="add-on loginpwd"></span>
                            <input type="password" placeholder="请输入密码" class="span2 input-xfat" name="password">
                        </div>
                        <div class="input-prepend"><span class="add-on loginemail"></span>
                            <input type="email" placeholder="请输入邮箱" class="span2 input-xfat" name="email">
                        </div>
                        <div class="logined">
                            <a class="sui-btn btn-block btn-xlarge btn-success"
                               href='javascript:document.getElementById("registerFormSubmit").submit();' target="_self">注&nbsp;&nbsp;册</a>
                        </div>
                    </form>
                    <div class="register-link">
                        <a href="javascript:void(0)" onclick="showLoginForm()">已有账号？点击登录</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script type="text/javascript">
    // 切换到注册表单
    function showRegisterForm() {
        document.getElementById('registerForm').style.display = 'block';
        document.getElementById('profile').style.display = 'none';
    }

    // 切换到登录表单
    function showLoginForm() {
        document.getElementById('registerForm').style.display = 'none';
        document.getElementById('profile').style.display = 'block';
    }

    /**
     * 登录超时 展示区跳出iframe
     */
    var _topWin = window;
    while (_topWin != _topWin.parent.window) {
        _topWin = _topWin.parent.window;
    }
    if (window != _topWin)
        _topWin.document.location.href = '${pageContext.request.contextPath}/admin/login.jsp';
</script>
</html>
