//package mapper;
//
//import models.user;
//import org.apache.ibatis.annotations.Insert;
//import org.apache.ibatis.annotations.Mapper;
//import org.apache.ibatis.annotations.Param;
//import org.apache.ibatis.annotations.Select;
//
//@Mapper
//public interface LoginMapper {
//
//    @Select("SELECT * FROM user WHERE username = #{username} and password = #{password}")
//    user checkUser(@Param("username") String username, @Param("password") String password);
//
//    @Insert("INSERT INTO user (username, password) VALUES (#{username}, #{password})")
//    void insert(user user);
//}
package mapper;
import models.user;
import org.apache.ibatis.annotations.*;

//public interface LoginMapper{
//    @Select("select * from user where username=#{username} AND password=#{password}")
//    @Results(id = "userMap", value = {
//            @Result(property = "user_id", column = "user_id"),
//            @Result(property = "username", column = "username"),
//            @Result(property = "password", column = "password"),
//            @Result(property = "email", column = "email"),
//            @Result(property = "role", column = "role"),
//            @Result(property = "status", column = "status"),
//            @Result(property = "created_at", column = "created_at"),
//            @Result(property = "updated_at", column = "updated_at")
//    })
//    user login(user user);
//}
public interface LoginMapper {
    @Select("select * from user where username=#{username} AND password=#{password}")
    user login(user user);

    // 检查用户名是否存在
    @Select("select count(*) from user where username=#{username}")
    int checkUserExists(String username);

    // 注册新用户
    @Insert("insert into user (username, password, email, role, status) values (#{username}, #{password}, #{email}, 'user', 'active')")
    int register(user user);
    @Update("UPDATE user SET status = 'active' WHERE user_id = #{user_id}")
    boolean updateStatus(user user);
    @Update("UPDATE user SET status = 'inactive' WHERE user_id = #{user_id}")
    boolean logout(@Param("user_id") Integer user_id);


}
