package services;

import models.user;

public interface LoginService {
    user login(user user);
    boolean updateStatus(user user);
    // 检查用户名是否存在
    boolean checkUserExists(String username);

    // 注册用户
    boolean register(user user);
    boolean logout(Integer user_id);
}
