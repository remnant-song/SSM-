package mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import java.util.List;
import models.user;  // 假设 User 类已定义

public interface UserMapper {

    @Select("SELECT * FROM user")
    List<user> getAllUsers();
    @Select("SELECT * FROM user WHERE username = #{username}")
    user getUserByUsername(@Param("username") String username);

    @Update("UPDATE user SET username = #{username}, email = #{email}, password = #{password} WHERE user_id = #{user_id}")
    boolean updateUser(user user);
}
