<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<%@page isELIgnored="false"%>
<head>
    <meta charset="UTF-8"> <!-- 设置字符编码为UTF-8 -->
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会议管理系统</title>
</head>
<body>
<!-- 引入header导航栏 -->
<div class="top">
    <jsp:include page="/jsp/header.jsp"/>
</div>
<link rel="stylesheet" type="text/css" href="CSS/index.css">
<script type="text/javascript" src="JavaScript/index.js"></script>
<main class="dashboard">
    <div class="home_container">
        <!-- 添加消息显示 -->
        <c:if test="${not empty message}">
            <div class="alert">
                ${message}
            </div>
        </c:if>
        
        <section class="welcome-message">
            <h1>欢迎回来，<span id="userName">${username}</span>！</h1>
        </section>

        <section class="role-actions">
            <div id="roleActions">
                <c:if test="${isAdmin=='admin'}">
                    <div class="admin-actions">
                        <h3>管理员功能</h3>
                        <a href="meeting-management.html" class="btn">会议管理</a>
                        <a href="to_creat_meeting" class="btn">创建会议</a>
                    </div>
                </c:if>

                <div class="user-actions">
                    <h3>我的功能</h3>
                    <a href="to_meeting_list" class="btn">查看会议</a>
                    <a href="to_creat_meeting" class="btn">创建会议</a>
                </div>
            </div>
        </section>

        <section class="meetings-section">
            <!-- 待确认会议 -->
            <div class="meeting-category">
                <h3>待确认会议</h3>
                <div class="meeting-table-container">
                    <c:if test="${not empty pendingMeetings}">
                        <table class="meeting-table">
                            <thead>
                                <tr>
                                    <th>会议标题</th>
                                    <th>地点</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${pendingMeetings}" var="meeting">
                                    <tr>
                                        <td>${meeting.title}</td>
                                        <td>${meeting.location}</td>
                                        <td><fmt:formatDate value="${meeting.start_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td><fmt:formatDate value="${meeting.end_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td>
                                            <!-- <button class="btn confirm" onclick="confirmMeeting('${meeting.meeting_id}')">确认参加</button>
                                            <button class="btn cancel" onclick="cancelMeeting('${meeting.meeting_id}')">取消参加</button> -->
                                            <form action="${pageContext.request.contextPath}/meeting/confirm" method="POST" style="display: inline;">
                                                <input type="hidden" name="meetingId" value="${meeting.meeting_id}">
                                                <button type="submit" class="btn confirm">确认参加</button>
                                            </form>
                                            <form action="${pageContext.request.contextPath}/meeting/cancel" method="POST" style="display: inline;">
                                                <input type="hidden" name="meetingId" value="${meeting.meeting_id}">
                                                <button type="submit" class="btn cancel">取消参加</button>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty pendingMeetings}">
                        <p class="no-meetings">暂无待确认的会议</p>
                    </c:if>
                </div>
            </div>

            <!-- 已确认待参加会议 -->
            <div class="meeting-category">
                <h3>已确认待参加会议</h3>
                <div class="meeting-table-container">
                    <c:if test="${not empty confirmedMeetings}">
                        <table class="meeting-table">
                            <thead>
                                <tr>
                                    <th>会议标题</th>
                                    <th>地点</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${confirmedMeetings}" var="meeting">
                                    <tr>
                                        <td>${meeting.title}</td>
                                        <td>${meeting.location}</td>
                                        <td><fmt:formatDate value="${meeting.start_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td><fmt:formatDate value="${meeting.end_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty confirmedMeetings}">
                        <p class="no-meetings">暂无已确认的会议</p>
                    </c:if>
                </div>
            </div>

            <!-- 已取消会议 -->
            <div class="meeting-category">
                <h3>已取消会议</h3>
                <div class="meeting-table-container">
                    <c:if test="${not empty cancelledMeetings}">
                        <table class="meeting-table">
                            <thead>
                                <tr>
                                    <th>会议标题</th>
                                    <th>地点</th>
                                    <th>开始时间</th>
                                    <th>结束时间</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach items="${cancelledMeetings}" var="meeting">
                                    <tr>
                                        <td>${meeting.title}</td>
                                        <td>${meeting.location}</td>
                                        <td><fmt:formatDate value="${meeting.start_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                                        <td><fmt:formatDate value="${meeting.end_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <c:if test="${empty cancelledMeetings}">
                        <p class="no-meetings">暂无已取消的会议</p>
                    </c:if>
                </div>
            </div>
        </section>
    </div>
</main>
</body>
</html>
