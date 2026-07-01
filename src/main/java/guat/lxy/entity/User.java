package guat.lxy.entity;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Integer active;
    // 拼接角色字符串，用于权限封装
    private String roles;
}
