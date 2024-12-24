package models;

import java.util.Date;
import java.util.StringTokenizer;

public class meeting {
    private int meeting_id;
    private String title;
    private String location;
    private Date start_time;
    private Date end_time;
    private int creator_id;

    private String status;
    private Date created_at;
    private Date updated_at;

    public meeting() {

    }


    public String Test()
    {
        return "meeting:"+title;
    }

    public meeting(int meeting_id, String title, String location, Date start_time, Date end_time, int creator_id, String status, Date created_at, Date updated_at) {
        this.meeting_id = meeting_id;
        this.title = title;
        this.location = location;
        this.start_time = start_time;
        this.end_time = end_time;
        this.creator_id = creator_id;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public meeting(String title, String location, Date start_time, Date end_time) {
        this.title = title;
        this.location = location;
        this.start_time = start_time;
        this.end_time = end_time;
    }

    public meeting(String title, String location, Date start_time, Date end_time, int creator_id, String status) {
        this.title = title;
        this.location = location;
        this.start_time = start_time;
        this.end_time = end_time;
        this.creator_id = creator_id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "meeting{" +
                "meeting_id=" + meeting_id +
                ", start_time=" + start_time +
                ", end_time=" + end_time +
                ", creator_id=" + creator_id +
                ", created_at=" + created_at +
                ", updated_at=" + updated_at +
                ", title='" + title + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public int getMeeting_id() {
        return meeting_id;
    }

    public void setMeeting_id(int meeting_id) {
        this.meeting_id = meeting_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getStart_time() {
        return start_time;
    }

    public void setStart_time(Date start_time) {
        this.start_time = start_time;
    }

    public Date getEnd_time() {
        return end_time;
    }

    public void setEnd_time(Date end_time) {
        this.end_time = end_time;
    }

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }
}
