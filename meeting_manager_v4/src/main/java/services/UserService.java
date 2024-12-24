package services;

import com.alibaba.druid.sql.visitor.functions.Substring;
import models.user;
import java.util.List;

public interface UserService {

    // 获取所有用户
    List<user> getAllUsers();

    user getUserByUsername(String username);

    boolean updateUser(user user);
}
