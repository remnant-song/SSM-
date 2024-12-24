<%--
  Created by IntelliJ IDEA.
  User: ASUS
  Date: 2024/12/9
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<!-- Top Navigation Bar -->
<head>
    <link rel="stylesheet" type="text/css" href="CSS/header.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title></title>
</head>
<script type="text/javascript" src="${pageContext.request.contextPath}/JavaScript/header.js"></script>
<!-- 顶部导航栏 -->
<header class="navbar">
    <div class="header_container" style=" display: flex;
    justify-content: space-between;
    align-items: center;
    max-width: 1200px;
    margin: 0 auto;
    padding: 0 20px;">

    <!-- Logo -->
        <div class="logo">
            <img src="./picture/rikka.jpg" alt="系统Logo">
        </div>

        <!-- 页面入口 -->
        <nav class="nav-menu">
            <ul>
                <li><a href="to_home">首页</a></li>
                <li><a href="to_meeting_list">会议列表</a></li>
                <li><a href="to_creat_meeting">创建会议</a></li>
                <li><a href="to_profile">个人中心</a></li>
            </ul>
        </nav>

        <!-- 登录 / 用户菜单 -->
        <div class="auth">
            <div class="user-dropdown">
                <a href="#" id="authLink" class="username-btn">${username}</a>
                <div class="dropdown-content">
                    <a href="logout">登出</a>
                </div>
            </div>
        </div>
    </div>
</header>