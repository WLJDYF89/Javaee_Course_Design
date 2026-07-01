package guat.lxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {

    // 访问根路径，跳转外层主iframe框架页面
    @GetMapping("/")
    public String index() {
        return "index";
    }

    // 内嵌iframe的欢迎首页
    @GetMapping("/welcome")
    public String welcome() {
        return "welcome";
    }

    // 操作成功提示页
    @GetMapping("/success")
    public String success(@RequestParam(defaultValue = "/welcome") String returnUrl, Model model) {
        model.addAttribute("returnUrl", returnUrl);
        return "success";
    }
}
