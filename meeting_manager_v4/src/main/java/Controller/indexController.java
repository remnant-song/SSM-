package Controller;

import models.meeting;
import models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import services.MeetingService;
import services.UserService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class indexController {
//处理首页请求
    @Autowired
    private UserService userService;  // 用户服务，获取当前登录用户信息

    @Autowired
    private MeetingService meetingService;  // 会议服务，获取当前会议列表和通知
    // 首页控制器方法
    @GetMapping("/to_home")
    public String showDashboard(HttpServletRequest request, Model model) {
        user currentUser = (user) request.getSession().getAttribute("USER_SESSION");
        model.addAttribute("username", currentUser.getUsername());
        model.addAttribute("isAdmin", currentUser.getRole());
        
        int user_id = currentUser.getUser_id();
        
        // 获取不同状态的会议列表
        List<meeting> pendingMeetings = meetingService.getMeetingsByStatus(user_id, "pending");
        List<meeting> confirmedMeetings = meetingService.getMeetingsByStatus(user_id, "confirmed");
        List<meeting> cancelledMeetings = meetingService.getMeetingsByStatus(user_id, "cancelled");
        
        model.addAttribute("pendingMeetings", pendingMeetings);
        model.addAttribute("confirmedMeetings", confirmedMeetings);
        model.addAttribute("cancelledMeetings", cancelledMeetings);
        
        return "home";
    }
    @GetMapping("/to_creat_meeting")
    public String showCreateMeetingPage(HttpServletRequest request, Model model) {
        user currentUser = (user) request.getSession().getAttribute("USER_SESSION");
        // 获取当前用户信息
//        user currentUser = userService.getUser();
        model.addAttribute("username", currentUser.getUsername());  // 将用户名字传到 JSP
//        根据角色显示不同的功能入口
        model.addAttribute("isAdmin", currentUser.getRole());  // 判断是否是管理员
        List<user> userList=userService.getAllUsers();
        if(userList==null)
        {
            System.out.println("userList is empty");
        }
        if (userList != null) {
            for (user user : userList) {
                System.out.println("已登录用户:"+user.getUsername());
            }
        }
        model.addAttribute("userList",userList);
        return "creat_meeting";  // 返回视图名，SpringMVC 会自动找到 "/WEB-INF/jsp/create_meeting.jsp"
    }
}
