document.addEventListener('DOMContentLoaded', function() {
    const userBtn = document.querySelector('.username-btn');
    const dropdownContent = document.querySelector('.dropdown-content');
    let isDropdownOpen = false;

    if (!userBtn || !dropdownContent) {
        console.error('Required elements not found');
        return;
    }

    // 点击用户名显示/隐藏下拉菜单
    userBtn.addEventListener('click', function(e) {
        e.preventDefault();
        e.stopPropagation();
        isDropdownOpen = !isDropdownOpen;
        
        if (isDropdownOpen) {
            dropdownContent.style.display = 'block';
            setTimeout(() => {
                dropdownContent.classList.add('show');
                userBtn.classList.add('active');
            }, 10);
        } else {
            dropdownContent.classList.remove('show');
            userBtn.classList.remove('active');
            setTimeout(() => {
                if (!isDropdownOpen) {
                    dropdownContent.style.display = 'none';
                }
            }, 200);
        }
    });

    // 点击页面其他地方关闭下拉菜单
    document.addEventListener('click', function(e) {
        if (!e.target.closest('.user-dropdown') && isDropdownOpen) {
            isDropdownOpen = false;
            dropdownContent.classList.remove('show');
            userBtn.classList.remove('active');
            setTimeout(() => {
                if (!isDropdownOpen) {
                    dropdownContent.style.display = 'none';
                }
            }, 200);
        }
    });
});
