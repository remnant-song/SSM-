<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css" href="CSS/profile.css">
</head>
<body>
    <div class="top">
        <jsp:include page="/jsp/header.jsp"/>
    </div>

    <main class="profile-container">
        <div class="profile-card">
            <h1>个人资料</h1>
            
            <c:if test="${not empty message}">
                <div class="alert ${messageType}">
                    ${message}
                </div>
            </c:if>

            <form action="update_profile" method="POST" class="profile-form">
                <div class="form-group">
                    <label for="username">用户名</label>
                    <input type="text" id="username" name="username" value="${user.username}" required>
                </div>

                <div class="form-group">
                    <label for="email">邮箱</label>
                    <input type="email" id="email" name="email" value="${user.email}" required>
                </div>

                <div class="form-group">
                    <label for="newPassword">新密码</label>
                    <input type="password" id="newPassword" name="newPassword" placeholder="留空则保持不变">
                </div>

                <div class="form-group">
                    <label for="confirmPassword">确认新密码</label>
                    <input type="password" id="confirmPassword" name="confirmPassword" placeholder="留空则保持不变">
                </div>

                <button type="submit" class="submit-btn">保存修改</button>
            </form>
        </div>
    </main>
</body>
</html> 