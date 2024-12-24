package Controller;

import models.meeting;
import models.meeting_participants;
import models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.MeetingService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class updateMeetingController {

    @Autowired
    private MeetingService meetingService;
    @Autowired
    private UserService userService;
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd'T'HH:mm"));
    }

    // 获取用户创建的会议列表
    @RequestMapping("/to_meeting_list")
    public String getMeetingList(HttpSession session, Model model) {
        user currentUser = (user) session.getAttribute("USER_SESSION");
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", currentUser.getUsername());  // 将用户名字传到 JSP
        System.out.println("to_meeting_list:");
        List<meeting> meetingList = meetingService.getMeetingsByCreator(currentUser.getUser_id());
        for(meeting meeting : meetingList)
        {
            System.out.println(meeting);
        }
        model.addAttribute("meetingList", meetingList);
        return "meeting_list";
    }

    // 跳转到编辑会议页面
    @RequestMapping("/edit_meeting")
    public String editMeeting(@RequestParam("meeting_id") int meeting_id,
                              HttpSession session,
                              Model model) {
        user currentUser = (user) session.getAttribute("USER_SESSION");
        if (currentUser == null) {
            return "redirect:/login";
        }
        model.addAttribute("username", currentUser.getUsername());  // 将用户名字传到 JSP
        meeting meeting = meetingService.getMeetingById(meeting_id);
        // 验证是否是会议创建者
        if (meeting == null || meeting.getCreator_id() != currentUser.getUser_id()) {
            return "redirect:/meeting_list";
        }

        // 获取所有用户列表
        List<user> userList = userService.getAllUsers();
        
        // 获取当前会议的参会人员
        List<meeting_participants> participants = meetingService.getMeetingParticipants(meeting_id);
        
        // 创建参会人员Map，用于在页面上显示选中状态
        Map<Integer, Boolean> participantMap = new HashMap<>();
        for (meeting_participants participant : participants) {
            participantMap.put(participant.getUser_id(), true);
        }

        model.addAttribute("meeting", meeting);
        model.addAttribute("userList", userList);
        model.addAttribute("participantMap", participantMap);
        
        return "edit_meeting";
    }

    // 更新会议信息
    @PostMapping("/update_meeting")
    public String updateMeeting(HttpServletRequest request,
                               @RequestParam("meeting_id") Integer meeting_id,
                               @RequestParam("title") String title,
                               @RequestParam("start_time") Date start_time,
                               @RequestParam("end_time") Date end_time,
                               @RequestParam("location") String location,
                               @RequestParam(value = "userIds", required = false) List<Integer> userIds) {
        // 获取当前登录用户
        user currentUser = (user) request.getSession().getAttribute("USER_SESSION");
        if (currentUser == null) {
            return "redirect:/login";
        }

        // 检查是否是会议创建者
        meeting existingMeeting = meetingService.getMeetingById(meeting_id);
        if (existingMeeting == null || existingMeeting.getCreator_id() != currentUser.getUser_id()) {
            return "redirect:/to_meeting_list";
        }

        // 创建更新后的会议对象
        meeting updatedMeeting = new meeting(
                title,
                location,
                start_time,
                end_time,
                currentUser.getUser_id(),
                existingMeeting.getStatus()
        );
        updatedMeeting.setMeeting_id(meeting_id);

        // 更新会议基本信息
        meetingService.updateMeeting(updatedMeeting);

        // 更新参会人员
        meetingService.updateMeetingParticipants(meeting_id, userIds);

        return "redirect:/to_meeting_list";
    }
}