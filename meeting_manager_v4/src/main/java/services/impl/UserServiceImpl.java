package services.impl;

import mapper.UserMapper;
import models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;  // 自动装配 UserMapper
    @Override
    public user getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }
    @Override
    public List<user> getAllUsers() {
        return userMapper.getAllUsers();
    }

    @Override
    public boolean updateUser(user user) {
        try {
            return userMapper.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
