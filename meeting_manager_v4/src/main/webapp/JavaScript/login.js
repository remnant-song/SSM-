document.addEventListener('DOMContentLoaded', function() {
    // 获取表单元素
    const loginForm = document.getElementById('loginform');
    const inputs = document.querySelectorAll('input');
    const submitBtn = document.querySelector('.sui-btn');

    // 表单提交验证
    loginForm.addEventListener('submit', function(event) {
        event.preventDefault();
        
        const username = document.getElementsByName('username')[0].value;
        const password = document.getElementsByName('password')[0].value;
        let isValid = true;
        let errorMessage = '';

        // 验证用户名
        if (!username.trim()) {
            isValid = false;
            errorMessage = '请输入用户名';
        }
        // 验证密码
        else if (!password.trim()) {
            isValid = false;
            errorMessage = '请输入密码';
        }

        if (!isValid) {
            showError(errorMessage);
        } else {
            // 添加提交动画
            submitBtn.style.transform = 'scale(0.95)';
            submitBtn.style.opacity = '0.8';
            setTimeout(() => {
                submitBtn.style.transform = 'scale(1)';
                submitBtn.style.opacity = '1';
                this.submit();
            }, 200);
        }
    });

    // 显示错误信息
    function showError(message) {
        let errorDiv = document.querySelector('.error-message');
        if (!errorDiv) {
            errorDiv = document.createElement('div');
            errorDiv.className = 'error-message';
            loginForm.insertBefore(errorDiv, loginForm.firstChild);
        }
        errorDiv.textContent = message;
        errorDiv.classList.add('show');

        // 震动动画
        loginForm.style.animation = 'shake 0.5s ease-in-out';
        setTimeout(() => {
            loginForm.style.animation = '';
            errorDiv.classList.remove('show');
        }, 3000);
    }

    // 添加震动动画
    const style = document.createElement('style');
    style.textContent = `
        @keyframes shake {
            0%, 100% { transform: translateX(0); }
            10%, 30%, 50%, 70%, 90% { transform: translateX(-5px); }
            20%, 40%, 60%, 80% { transform: translateX(5px); }
        }
    `;
    document.head.appendChild(style);
});
