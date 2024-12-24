package services.impl;

import mapper.LoginMapper;
import models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.LoginService;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginMapper loginMapper;

    // 登录
    @Override
    public user login(user user) {
         return loginMapper.login(user);

    }

    @Override
    public boolean updateStatus(user user) {
        return loginMapper.updateStatus(user);
    }

    // 检查用户名是否存在
    @Override
    public boolean checkUserExists(String username) {
        System.out.println("checkUserExists in impl");
        return loginMapper.checkUserExists(username) > 0;

    }

    // 注册用户
    @Override
    public boolean register(user user) {
         try {
             System.out.println("register in impl");
             return loginMapper.register(user) > 0;
         } catch (Exception e) {
             return false;
         }

    }
    //登出用户
    @Override
    public boolean logout(Integer user_id) {
        System.out.println("logout:"+user_id+"in impl");
        return loginMapper.logout(user_id);
    }
}
