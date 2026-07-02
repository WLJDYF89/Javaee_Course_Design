package guat.lxy.controller;

import guat.lxy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String toLogin(){
        return "login";
    }

    @GetMapping("/forgot-password")
    public String toForgotPassword() {
        return "forgot-password";
    }

    @PostMapping("/forgot-password")
    public String doForgotPassword(@RequestParam String username,
                                   @RequestParam String newPassword,
                                   @RequestParam String confirmPassword,
                                   Model model) {
        if (username == null || username.trim().isEmpty()
                || newPassword == null || newPassword.trim().isEmpty()) {
            model.addAttribute("error", "账号和新密码不能为空");
            return "forgot-password";
        }
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "两次密码输入不一致");
            return "forgot-password";
        }
        boolean success = userService.resetPassword(username.trim(), newPassword);
        if (!success) {
            model.addAttribute("error", "该账号不存在");
            return "forgot-password";
        }
        model.addAttribute("success", true);
        return "forgot-password";
    }
}
