<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html lang="zh">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>创建会议</title>
    <link rel="stylesheet" type="text/css" href="CSS/creat_meeting.css">  <!-- 引入你的CSS样式 -->
    <script type="text/javascript" src="JavaScript/creat_meeting.js"></script>
</head>
<body>

<div class="top">
    <jsp:include page="/jsp/header.jsp"/>
</div>
<h1>创建会议</h1>
<!-- 显示消息 -->
<c:if test="${not empty msg}">
    <div class="alert">
        <c:choose>
            <c:when test="${msg == '创建完成'}">
                <span class="success">${msg}</span>
            </c:when>
            <c:otherwise>
                <span class="error">${msg}</span>
            </c:otherwise>
        </c:choose>
    </div>
</c:if>

<!-- 创建会议表单 -->
<form action="${pageContext.request.contextPath}/creat_meeting/create" method="POST">
    <div class="form-group">
        <label for="meetingTitle">会议标题：</label>
        <input type="text" id="meetingTitle" name="meetingTitle" required placeholder="请输入会议标题" class="form-control">
    </div>

    <div class="form-group">
        <label for="start_time">开始时间：</label>
        <input type="datetime-local" id="start_time" name="start_time" required class="form-control">
    </div>

    <div class="form-group">
        <label for="end_time">结束时间：</label>
        <input type="datetime-local" id="end_time" name="end_time" required class="form-control">
    </div>

    <div class="form-group">
        <label for="meetingLocation">会议地点：</label>
        <input type="text" id="meetingLocation" name="meetingLocation" required placeholder="请输入会议地点" class="form-control">
    </div>
    <div>
        <label>请选择参会人员</label>
        <section class="select-attendees">
            <div class="checkbox-list">
                <c:forEach var="user" items="${userList}">
                    <div class="form-check">
                        <input type="checkbox" class="form-check-input" name="userIds" value="${user.user_id}" id="user${user.user_id}">
                        <label class="form-check-label" for="user${user.user_id}">${user.username}</label>
                    </div>
                </c:forEach>
            </div>
        </section>
    </div>
    <div class="form-group">
        <button type="submit" class="btn btn-primary">创建会议</button>
    </div>
</form>

<footer>
    <!-- 页脚内容 -->
</footer>
</body>
</html>
