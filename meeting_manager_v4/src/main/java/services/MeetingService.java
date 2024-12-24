package services;

import models.meeting;
import models.meeting_participants;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface MeetingService {
    Integer createMeeting(meeting newMeeting);
    // 获取当前进行中的会议列表
    List<meeting> getCurrentMeetings();

    boolean addParticipant(meeting_participants participant);

    List<meeting> getMeetingsByStatus(int user_id, String status);

    // 更新参会人员状态
    boolean updateParticipantStatus(meeting_participants participant);
    List<meeting> getMeetingsByCreator(int creator_id);

    meeting getMeetingById(int meeting_id);
   
    void updateMeeting(meeting meeting);

    public List<meeting_participants> getMeetingParticipants(int meeting_id);
    // 删除会议所有参会人员
    public void deleteAllParticipants(int meeting_id);
    public void updateMeetingParticipants(int meeting_id, List<Integer> userIds) ;
}
