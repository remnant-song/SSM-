// script.js

// 模拟参会人员数据
let attendees = [
    { name: '张三', confirmStatus: '已确认', responseStatus: '主动报名' },
    { name: '李四', confirmStatus: '待确认', responseStatus: '邀请中' },
    { name: '王五', confirmStatus: '已拒绝', responseStatus: '已拒绝' },
];

// 更新参会人员列表
function updateAttendeesList() {
    const tbody = document.querySelector('.attendees-list tbody');
    tbody.innerHTML = ''; // 清空现有的列表

    attendees.forEach((attendee) => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${attendee.name}</td>
            <td>${attendee.confirmStatus}</td>
            <td>${attendee.responseStatus}</td>
            <td>
                <button class="btn" onclick="confirmAttendance('${attendee.name}')">确认参会</button>
                <button class="btn" onclick="declineAttendance('${attendee.name}')">拒绝参会</button>
                <button class="btn" onclick="reInvite('${attendee.name}')">重新邀请</button>
            </td>
        `;
        tbody.appendChild(row);
    });
}

// 确认参会
function confirmAttendance(name) {
    const attendee = attendees.find((a) => a.name === name);
    attendee.confirmStatus = '已确认';
    attendee.responseStatus = '主动报名';
    updateAttendeesList();
}

// 拒绝参会
function declineAttendance(name) {
    const attendee = attendees.find((a) => a.name === name);
    attendee.confirmStatus = '已拒绝';
    attendee.responseStatus = '已拒绝';
    updateAttendeesList();
}

// 重新邀请
function reInvite(name) {
    const attendee = attendees.find((a) => a.name === name);
    attendee.confirmStatus = '待确认';
    attendee.responseStatus = '邀请中';
    updateAttendeesList();
}

// 添加参会人员
document.getElementById('addAttendeeForm').addEventListener('submit', function (e) {
    e.preventDefault();
    const attendeeName = document.getElementById('attendeeName').value.trim();
    if (attendeeName) {
        attendees.push({ name: attendeeName, confirmStatus: '待确认', responseStatus: '邀请中' });
        document.getElementById('attendeeName').value = ''; // 清空输入框
        updateAttendeesList(); // 更新参会人员列表
    }
});

// 初始加载参会人员列表
updateAttendeesList();
