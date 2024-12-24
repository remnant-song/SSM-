package Controller;

import models.meeting;
import models.meeting_participants;
import models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.datetime.DateFormatter;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import services.MeetingService;
import services.UserService;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/creat_meeting")
public class creatMeetingController {

    @Autowired
    private UserService userService;  // 获取用户列表

    @Autowired
    private MeetingService meetingService;  // 处理会议相关操作
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.addCustomFormatter(new DateFormatter("yyyy-MM-dd'T'HH:mm"));
    }

    // 处理创建会议表单提交
    @PostMapping("/create")
    public String createMeeting(HttpServletRequest request,
                                @RequestParam("meetingTitle") String meetingTitle,
                                @RequestParam("start_time") Date start_time,
                                @RequestParam("end_time") Date end_time,
                                @RequestParam("meetingLocation") String meetingLocation,
                                @RequestParam(value = "userIds", required = false) List<Integer> userIds) {
        user currentUser = (user) request.getSession().getAttribute("USER_SESSION");
        
        // 创建会议对象
        meeting newMeeting = new meeting(meetingTitle, meetingLocation, start_time, end_time, 
                                       currentUser.getUser_id(), "scheduled");
        
        // 创建会议并获取返回值
        Integer result = meetingService.createMeeting(newMeeting);
        
        // 打印日志，检查meeting_id是否正确设置
        System.out.println("Created meeting with ID: " + newMeeting.getMeeting_id());
        
        if (result > 0 && newMeeting.getMeeting_id() > 0) {
            // 如果有选择参会人员
            if (userIds != null && !userIds.isEmpty()) {
                for (Integer user_id : userIds) {
                    meeting_participants participant = new meeting_participants(
                        newMeeting.getMeeting_id(),  // 使用新生成的meeting_id
                        user_id,
                        "pending"
                    );
                    if (!meetingService.addParticipant(participant)) {
                        request.setAttribute("msg", "Failed to add participant: " + user_id);
                        return "forward:/jsp/creat_meeting.jsp";
                    }
                }
            }
            return "redirect:/to_home";
        } else {
            request.setAttribute("msg", "Failed to create meeting");
            return "forward:/jsp/creat_meeting.jsp";
        }
    }
}
