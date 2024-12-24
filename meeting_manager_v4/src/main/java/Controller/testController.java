package Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class testController {
    @GetMapping("/test")
    public String showDashboard(Model model) {
        System.out.println("testController");
        return "home";  // 返回首页的 JSP 页面
    }
}
