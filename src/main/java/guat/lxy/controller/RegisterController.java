package guat.lxy.controller;

import guat.lxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String toRegister() {
        return "register";
    }

    @PostMapping("/register")
    public String doRegister(@RequestParam String username,
                             @RequestParam String password,
                             @RequestParam String confirmPassword,
                             Model model) {
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            model.addAttribute("error", "账号和密码不能为空");
            return "register";
        }
        if (!password.equals(confirmPassword)) {
            model.addAttribute("error", "两次密码输入不一致");
            return "register";
        }
        boolean success = userService.register(username.trim(), password);
        if (!success) {
            model.addAttribute("error", "该账号已被注册");
            return "register";
        }
        model.addAttribute("success", true);
        return "register";
    }
}

