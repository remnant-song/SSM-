
package Controller;
import models.user;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import services.UserService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@Controller
public class LoginController {
    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;
    // 跳转到主页面
    @RequestMapping("/toMainPage")
    public String toMainPage() {
        return "home";
    }
    @RequestMapping("/toLogin")
    //redirect:login或者redirect:toLogin就会访问没有样式的jsp
    public String toLogin() {
        return "forward:jsp/login.jsp";
    }
    // 用户登录
    @RequestMapping("/login")
    public String login(user user, HttpServletRequest request) {
        try {
            user u = loginService.login(user);
            if (u != null) {
                if(loginService.updateStatus(u))//更新用户状态
                {
                    request.getSession().setAttribute("USER_SESSION", u);//将刚刚登录的用户放到会话里
                    return "redirect:to_home";
                }
                else{
                    request.setAttribute("msg", "登录失败，请重试");
                    return "forward:/jsp/login.jsp";
                }
            }
            request.setAttribute("msg", "用户名或密码错误");
            return "forward:/jsp/login.jsp";
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "未知系统错误");
            return "forward:/jsp/login.jsp";
        }
    }

    // 用户注册
    @RequestMapping("/register")
    public String register(user user, HttpServletRequest request) {
        System.out.println("register user= "+user.toString());
        try {
            // 检查用户名是否已存在
            if (loginService.checkUserExists(user.getUsername())) {
                request.setAttribute("msg", "用户名已存在");
                return "forward:/jsp/login.jsp";
            }

            // 注册用户
            boolean success = loginService.register(user);
            if (success) {
                request.setAttribute("msg", "注册成功，请登录");
                return "forward:/jsp/login.jsp";
            } else {
                request.setAttribute("msg", "注册失败，请稍后再试");
                return "forward:/jsp/login.jsp";
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("msg", "系统错误");
            return "forward:/jsp/login.jsp";
        }
    }
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        // 从 session 中获取登录的用户对象
        user user = (user) request.getSession().getAttribute("USER_SESSION");
        System.out.println("logout");

        if (user != null) {
            System.out.println("need logout user id:"+user.getUsername());
            // 调用 service 层的 logout 方法更新用户状态为 inactive
            if(loginService.logout(user.getUser_id()))
            {

                // 清除 session 中的用户对象
                request.getSession().removeAttribute("USER_SESSION");
                // 跳转到登录页面或首页
                //return "redirect:/login";
                return "forward:jsp/login.jsp";
            }
            else {
                System.out.println("登出失败");
                request.setAttribute("msg", "登出失败");
                return "redirect:to_home";
            }
        } else {
            System.out.println("用户未登录，跳转到登录页面");
            // 用户未登录，跳转到登录页面
            return "redirect:/login";
        }
    }
}