package Controller;

import models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.UserService;

import javax.servlet.http.HttpSession;

@Controller
public class ProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/to_profile")
    public String showProfile(HttpSession session, Model model) {
        user currentUser = (user) session.getAttribute("USER_SESSION");
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", currentUser.getUsername());  // 将用户名字传到 JSP
        model.addAttribute("user", currentUser);
        return "profile";
    }

    @PostMapping("/update_profile")
    public String updateProfile(
            @RequestParam String username,
            @RequestParam String email,
            @RequestParam(required = false) String newPassword,
            @RequestParam(required = false) String confirmPassword,
            HttpSession session,
            RedirectAttributes redirectAttributes
    ) {
        user currentUser = (user) session.getAttribute("USER_SESSION");
        if (currentUser == null) {
            return "redirect:/login";
        }

        // 验证新密码
        if (newPassword != null && !newPassword.isEmpty()) {
            if (!newPassword.equals(confirmPassword)) {
                redirectAttributes.addFlashAttribute("message", "两次输入的密码不一致");
                redirectAttributes.addFlashAttribute("messageType", "error");
                return "redirect:/to_profile";
            }
        }

        // 更新用户信息
        currentUser.setUsername(username);
        currentUser.setEmail(email);
        if (newPassword != null && !newPassword.isEmpty()) {
            currentUser.setPassword(newPassword);
        }

        // 保存到数据库
        boolean updated = userService.updateUser(currentUser);
        if (updated) {
            // 更新session中的用户信息
            session.setAttribute("USER_SESSION", currentUser);
            redirectAttributes.addFlashAttribute("message", "个人资料更新成功");
            redirectAttributes.addFlashAttribute("messageType", "success");
        } else {
            redirectAttributes.addFlashAttribute("message", "更新失败，请稍后重试");
            redirectAttributes.addFlashAttribute("messageType", "error");
        }

        return "redirect:/to_profile";
    }
} 