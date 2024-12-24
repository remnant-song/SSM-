package Controller;

import models.meeting;
import models.meeting_participants;
import models.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import services.MeetingService;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/meeting")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/confirm")
    public String confirmMeeting(@RequestParam("meetingId") Integer meetingId,
                                 HttpSession session,
                                 RedirectAttributes redirectAttributes) {
        System.out.println("Received confirm meeting request for meetingId: " + meetingId);
        try {
            // 获取当前登录用户
            user currentUser = (user) session.getAttribute("USER_SESSION");
            if (currentUser == null) {
                redirectAttributes.addFlashAttribute("message", "请先登录");
                return "redirect:/home";
            }

            // 创建参会记录
            meeting_participants participant = new meeting_participants(
                    meetingId,
                    currentUser.getUser_id(),
                    "confirmed"
            );

            // 更新参会状态
            boolean success = meetingService.updateParticipantStatus(participant);

            if (success) {
                redirectAttributes.addFlashAttribute("message", "已确认参加会议！");
            } else {
                redirectAttributes.addFlashAttribute("message", "操作失败，请重试");
            }
        } catch (Exception e) {
            System.out.println("Error in confirmMeeting: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "系统错误，请稍后重试");
        }
        return "redirect:/to_home";
    }

    @PostMapping("/cancel")
    public String cancelMeeting(@RequestParam("meetingId") Integer meetingId,
                                HttpSession session,
                                RedirectAttributes redirectAttributes) {
        System.out.println("Received cancel meeting request for meetingId: " + meetingId);
        try {
            // 获取当前登录用户
            user currentUser = (user) session.getAttribute("USER_SESSION");
            if (currentUser == null) {
                redirectAttributes.addFlashAttribute("message", "请先登录");
                return "redirect:/home";
            }

            // 创建参会记录
            meeting_participants participant = new meeting_participants(
                    meetingId,
                    currentUser.getUser_id(),
                    "cancelled"
            );

            // 更新参会状态
            boolean success = meetingService.updateParticipantStatus(participant);

            if (success) {
                redirectAttributes.addFlashAttribute("message", "已取消参加会议！");
            } else {
                redirectAttributes.addFlashAttribute("message", "操作失败，请重试");
            }
        } catch (Exception e) {
            System.out.println("Error in cancelMeeting: " + e.getMessage());
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "系统错误，请稍后重试");
        }
        return "redirect:/to_home";
    }
}