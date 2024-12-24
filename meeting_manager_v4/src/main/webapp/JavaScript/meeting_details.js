// script.js

// 假设当前用户的角色是从后台获取的，可以通过 sessionStorage 或 cookie 实现
let userRole = '普通用户'; // '普通用户' / '管理员' / '会议创建者'
let isRegistered = false; // 判断用户是否已报名参会

// 假设当前会议状态和参会人员数据从后台获取
let attendees = [
    { name: '张三', status: '已确认' },
    { name: '李四', status: '待确认' },
    { name: '王五', status: '已拒绝' },
];

// 设置用户角色相关功能
function setRoleBasedActions(role) {
    const userActions = document.getElementById('user-actions');
    const adminActions = document.getElementById('admin-actions');
    const registerBtn = document.getElementById('registerBtn');
    const cancelBtn = document.getElementById('cancelBtn');
    const manageAttendeesBtn = document.getElementById('manageAttendeesBtn');

    if (role === '普通用户') {
        // 显示普通用户操作按钮
        userActions.style.display = 'block';
        adminActions.style.display = 'none';

        // 根据是否已报名来显示“报名”或“取消报名”按钮
        if (isRegistered) {
            registerBtn.style.display = 'none';
            cancelBtn.style.display = 'block';
        } else {
            registerBtn.style.display = 'block';
            cancelBtn.style.display = 'none';
        }
    } else if (role === '管理员' || role === '会议创建者') {
        // 显示管理员/会议创建者操作按钮
        userActions.style.display = 'none';
        adminActions.style.display = 'block';
    }
}

// 报名参会
function registerForMeeting() {
    isRegistered = true;
    setRoleBasedActions(userRole); // 更新按钮显示状态
}

// 取消报名
function cancelRegistration() {
    isRegistered = false;
    setRoleBasedActions(userRole); // 更新按钮显示状态
}

// 管理参会人员
function manageAttendees() {
    // 跳转到参会人员管理页面
    window.location.href = "manage-attendees.html";
}

// 为按钮绑定事件
document.getElementById('registerBtn').addEventListener('click', registerForMeeting);
document.getElementById('cancelBtn').addEventListener('click', cancelRegistration);
document.getElementById('manageAttendeesBtn').addEventListener('click', manageAttendees);

// 设置当前用户角色功能
setRoleBasedActions(userRole);
