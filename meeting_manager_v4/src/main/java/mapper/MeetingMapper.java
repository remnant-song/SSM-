package mapper;

import models.meeting;
import models.meeting_participants;
import models.user;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface MeetingMapper {
    @Select("SELECT * FROM meeting")
    List<meeting> getCurrentMeetings();


    @Insert("INSERT INTO meeting (title, location, start_time, end_time, creator_id, status) " +
            "VALUES (#{title}, #{location}, #{start_time}, #{end_time}, #{creator_id}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "meeting_id", keyColumn = "meeting_id")
    Integer createMeeting(meeting newMeeting);

    @Insert("INSERT INTO meeting_participants (meeting_id, user_id, status) " +
            "VALUES (#{meeting_id}, #{user_id}, #{status})")
    boolean addParticipant(meeting_participants participant);

    //根据状态不同查询参会者与会议关系表
    @Select("SELECT m.* FROM meeting m " +
            "JOIN meeting_participants mp ON m.meeting_id = mp.meeting_id " +
            "WHERE mp.user_id = #{user_id} AND mp.status = #{status} " +
            "ORDER BY m.start_time ASC")
    List<meeting> getMeetingsByStatus(@Param("user_id") int user_id, @Param("status") String status);
    //修改会议
    @Update("UPDATE meeting_participants SET status = #{status} " +
            "WHERE meeting_id = #{meeting_id} AND user_id = #{user_id}")
    int updateParticipantStatus(meeting_participants participant);

    @Select("SELECT * FROM meeting WHERE creator_id = #{creator_id}")
    List<meeting> getMeetingsByCreator(int creator_id);

    @Select("SELECT * FROM meeting WHERE meeting_id = #{meeting_id}")
    meeting getMeetingById(int meeting_id);


    @Update("UPDATE meeting SET title = #{title}, location = #{location}, start_time = #{start_time}, end_time = #{end_time} " +
            "WHERE meeting_id = #{meeting_id}")

    void updateMeeting(meeting meeting);

    // 获取会议参会人员
    @Select("SELECT * FROM meeting_participants WHERE meeting_id = #{meeting_id}")
    List<meeting_participants> getMeetingParticipants(int meeting_id);

    // 删除会议所有参会人员
    @Delete("DELETE FROM meeting_participants WHERE meeting_id = #{meeting_id}")
    void deleteAllParticipants(int meeting_id);


}
