package guat.lxy.service;

import guat.lxy.entity.User;
import guat.lxy.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Transactional
    public boolean register(String username, String password) {
        User exist = userMapper.selectUserByUsername(username);
        if (exist != null) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(passwordEncoder.encode(password));
        userMapper.insertUser(user);
        userMapper.insertUserRole(user.getId());
        return true;
    }

    @Transactional
    public boolean resetPassword(String username, String newPassword) {
        User exist = userMapper.selectUserByUsername(username);
        if (exist == null) {
            return false;
        }
        userMapper.updatePassword(exist.getId(), passwordEncoder.encode(newPassword));
        return true;
    }
}

