package services.impl;

import mapper.MeetingMapper;
import models.meeting;
import models.meeting_participants;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import services.MeetingService;

import java.util.*;
@Service
public class MeetingServiceImpl implements MeetingService {
    @Autowired
    private MeetingMapper meetingMapper;

    @Override
    public Integer createMeeting(meeting newMeeting) {
        return meetingMapper.createMeeting(newMeeting);
    }

    @Override
    public List<meeting> getCurrentMeetings() {
        return meetingMapper.getCurrentMeetings();
    }

    @Override
    public boolean addParticipant(meeting_participants participant) {
        return meetingMapper.addParticipant(participant);
    }

    @Override
    public List<meeting> getMeetingsByStatus(int user_id, String status) {
        return meetingMapper.getMeetingsByStatus(user_id, status);
    }

    @Override
    public boolean updateParticipantStatus(meeting_participants participant) {
        System.out.println("MeetingService: Updating participant status: " + participant);
        try {
            int result = meetingMapper.updateParticipantStatus(participant);
            System.out.println("MeetingService: Update result: " + result);
            return result > 0;
        } catch (Exception e) {
            System.out.println("MeetingService: Error updating status: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<meeting> getMeetingsByCreator(int creator_id) {

        System.out.println("getMeetingsByCreator");
        return meetingMapper.getMeetingsByCreator(creator_id);
    }

    @Override
    public meeting getMeetingById(int meeting_id) {

        System.out.println("getMeetingById");
        return meetingMapper.getMeetingById(meeting_id);
    }

    @Override
    public void updateMeeting(meeting meeting) {

        System.out.println("updateMeeting");
        meetingMapper.updateMeeting(meeting);
    }

    // 获取会议参会人员
    @Override
    public List<meeting_participants> getMeetingParticipants(int meeting_id) {
        return meetingMapper.getMeetingParticipants(meeting_id);
    }

    @Override
    // 删除会议所有参会人员
    public void deleteAllParticipants(int meeting_id) {
        meetingMapper.deleteAllParticipants(meeting_id);
    }

    public void updateMeetingParticipants(int meeting_id, List<Integer> userIds) {
        // 先删除所有原有参会人员
        meetingMapper.deleteAllParticipants(meeting_id);

        // 如果有新的参会人员，则添加
        if (userIds != null && !userIds.isEmpty()) {
            for (Integer userId : userIds) {
                meeting_participants participant = new meeting_participants(
                        meeting_id,
                        userId,
                        "pending"  // 新添加的参会人员状态为待定
                );
                meetingMapper.addParticipant(participant);
            }
        }
    }
}
