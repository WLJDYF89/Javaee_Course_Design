package guat.lxy.mapper;

import guat.lxy.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {
    // 查询用户+拼接角色字符串
    @Select("""
        SELECT u.*, GROUP_CONCAT(r.role) roles
        FROM t_user u
        LEFT JOIN t_user_role ur ON u.id = ur.user_id
        LEFT JOIN t_role r ON ur.role_id = r.id
        WHERE u.username = #{username} AND u.active = 1
        GROUP BY u.id
        """)
    User selectUserByUsername(@Param("username") String username);

    @Insert("INSERT INTO t_user(username, password, active) VALUES(#{username}, #{password}, 1)")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertUser(User user);

    @Insert("INSERT INTO t_user_role(user_id, role_id) VALUES(#{userId}, (SELECT id FROM t_role WHERE role = 'ROLE_normal'))")
    int insertUserRole(@Param("userId") Integer userId);
}
