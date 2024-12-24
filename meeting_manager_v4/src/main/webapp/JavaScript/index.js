// script.js

document.addEventListener('DOMContentLoaded', function() {
    // 设置角色相关功能
    function setRoleBasedActions(role) {
        const userActions = document.querySelector('.user-actions');
        const adminActions = document.querySelector('.admin-actions');

        if (role === '普通用户') {
            if (adminActions) adminActions.style.display = 'none';
            if (userActions) userActions.style.display = 'block';
        } else if (role === '管理员') {
            if (adminActions) adminActions.style.display = 'block';
            if (userActions) userActions.style.display = 'block';
        }
    }

    // 添加平滑滚动效果
    function addSmoothScroll() {
        document.querySelectorAll('a[href^="#"]').forEach(anchor => {
            anchor.addEventListener('click', function (e) {
                e.preventDefault();
                document.querySelector(this.getAttribute('href')).scrollIntoView({
                    behavior: 'smooth'
                });
            });
        });
    }

    // 初始化
    const userRole = sessionStorage.getItem('userRole') || '普通用户';
    setRoleBasedActions(userRole);
    addSmoothScroll();

    // 添加表单提交前的确认
    document.querySelectorAll('form').forEach(form => {
        form.addEventListener('submit', function(e) {
            const isConfirmForm = this.action.includes('/meeting/confirm');
            const isCancelForm = this.action.includes('/meeting/cancel');
            
            if (isConfirmForm && !confirm('确定要参加这个会议吗？')) {
                e.preventDefault();
            } else if (isCancelForm && !confirm('确定要取消参加这个会议吗？')) {
                e.preventDefault();
            }
        });
    });

    // 如果有消息提示，3秒后自动隐藏
    const alert = document.querySelector('.alert');
    if (alert) {
        setTimeout(() => {
            alert.style.opacity = '0';
            setTimeout(() => alert.style.display = 'none', 500);
        }, 3000);
    }
});