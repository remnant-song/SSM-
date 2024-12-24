package models;

public class meeting_participants {
    private int meeting_id;
    private int user_id;
    private String status;  // 参会状态

    @Override
    public String toString() {
        return "meeting_participants{" +
                "meeting_id=" + meeting_id +
                ", user_id=" + user_id +
                ", status='" + status + '\'' +
                '}';
    }

    public meeting_participants(int user_id, String status) {
        this.user_id = user_id;
        this.status = status;
    }

    public meeting_participants(int meeting_id, int user_id, String status) {
        this.meeting_id = meeting_id;
        this.user_id = user_id;
        this.status = status;
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}