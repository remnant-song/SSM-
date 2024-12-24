<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>编辑会议</title>
    <link rel="stylesheet" type="text/css" href="CSS/creat_meeting.css">
</head>
<body>
    <!-- 引入header导航栏 -->
    <jsp:include page="/jsp/header.jsp"/>
    
    <main class="edit-meeting-container">
        <h1>编辑会议信息</h1>
        
        <form action="update_meeting" method="post" class="meeting-form">
            <input type="hidden" name="meeting_id" value="${meeting.meeting_id}">
            
            <div class="form-group">
                <label for="title">会议标题</label>
                <input type="text" id="title" name="title" value="${meeting.title}" required>
            </div>
            
            <div class="form-group">
                <label for="location">地点</label>
                <input type="text" id="location" name="location" value="${meeting.location}" required>
            </div>
            
            <div class="form-group">
                <label for="start_time">开始时间</label>
                <input type="datetime-local" id="start_time" name="start_time"
                       value="<fmt:formatDate value="${meeting.start_time}" pattern="yyyy-MM-dd'T'HH:mm"/>" required>
            </div>
            
            <div class="form-group">
                <label for="end_time">结束时间</label>
                <input type="datetime-local" id="end_time" name="end_time"
                       value="<fmt:formatDate value="${meeting.end_time}" pattern="yyyy-MM-dd'T'HH:mm"/>" required>
            </div>
            <div>
                <label>请选择参会人员</label>
                <!-- 添加参会人员选择区域 -->
                <section class="select-attendees">
                    <div class="checkbox-list">
                        <c:forEach var="user" items="${userList}">
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input"
                                       name="userIds"
                                       value="${user.user_id}"
                                       id="user${user.user_id}"
                                       ${participantMap[user.user_id] ? 'checked' : ''}>
                                <label class="form-check-label" for="user${user.user_id}">${user.username}</label>
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </div>
            <div class="form-actions">
                <button type="submit" class="submit-btn">保存修改</button>
                <button type="button" class="cancel-btn" onclick="window.location.href='to_meeting_list'">取消</button>
            </div>
        </form>
    </main>
</body>
</html> 