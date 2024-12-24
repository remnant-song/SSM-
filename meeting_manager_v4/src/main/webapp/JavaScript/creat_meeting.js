document.addEventListener('DOMContentLoaded', function() {
    document.querySelector("form").addEventListener("submit", function(event) {
        let title = document.getElementById("meetingTitle").value;
        if (!title.trim()) {
            alert("会议标题不能为空！");
            event.preventDefault(); // 阻止表单提交
        }
    });

    // 添加输入框焦点特效
    const inputs = document.querySelectorAll('input[type="text"], input[type="datetime-local"]');
    inputs.forEach(input => {
        const label = input.previousElementSibling;
        
        input.addEventListener('focus', function() {
            label.style.transform = 'translateY(-5px)';
            label.style.color = '#fff';
            label.style.textShadow = '0 0 10px rgba(255, 255, 255, 0.5)';
            this.style.boxShadow = '0 0 20px rgba(255, 255, 255, 0.3)';
        });
        
        input.addEventListener('blur', function() {
            if (!this.value) {
                label.style.transform = 'translateY(0)';
            }
            label.style.color = '#fff';
            label.style.textShadow = '0 0 5px rgba(255, 255, 255, 0.3)';
            this.style.boxShadow = 'none';
        });
    });

    // 添加复选框动画效果
    const checkboxes = document.querySelectorAll('.form-check-input');
    checkboxes.forEach(checkbox => {
        checkbox.addEventListener('change', function() {
            const parentDiv = this.closest('.form-check');
            if (this.checked) {
                parentDiv.style.transform = 'scale(1.05)';
                parentDiv.style.background = 'rgba(255, 255, 255, 0.25)';
                parentDiv.style.borderColor = '#fff';
            } else {
                parentDiv.style.transform = 'scale(1)';
                parentDiv.style.background = 'rgba(255, 255, 255, 0.1)';
                parentDiv.style.borderColor = 'rgba(255, 255, 255, 0.2)';
            }
        });
    });


    window.addEventListener('scroll', revealOnScroll);
    revealOnScroll(); // 初始检查


    
    function typeWriter() {
        if (i < text.length) {
            title.textContent += text.charAt(i);
            i++;
            setTimeout(typeWriter, 100);
        }
    }
    
    setTimeout(typeWriter, 500);
});