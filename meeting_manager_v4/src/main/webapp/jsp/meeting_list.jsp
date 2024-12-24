<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>会议列表</title>
    <link rel="stylesheet" type="text/css" href="CSS/meeting_list.css">
</head>
<body>
<div class="top">
    <jsp:include page="/jsp/header.jsp"/>
</div>
    
    <main class="meeting-list-container">
        <h1>我创建的会议</h1>
        
        <c:if test="${empty meetingList}">
            <div class="empty-state">
                <p>您还没有创建任何会议</p>
                <a href="to_creat_meeting" class="create-btn">创建新会议</a>
            </div>
        </c:if>
        
        <c:if test="${not empty meetingList}">
            <div class="meeting-list">
                <table>
                    <thead>
                        <tr>
                            <th>会议主题</th>
                            <th>地点</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>操作</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${meetingList}" var="meeting">
                            <tr>
                                <td>${meeting.title}</td>
                                <td>${meeting.location}</td>
                                <td><fmt:formatDate value="${meeting.start_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <td><fmt:formatDate value="${meeting.end_time}" pattern="yyyy-MM-dd HH:mm"/></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/edit_meeting?meeting_id=${meeting.meeting_id}" class="edit-btn">编辑</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:if>
    </main>
</body>
</html> 