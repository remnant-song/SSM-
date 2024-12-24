package models;

import com.alibaba.druid.sql.visitor.functions.Substring;

public class user {
    private int user_id;
    private String username;
    private String password;
    private String email;
    private String role;
    private String status;
    private String created_at;
    private String updated_at;
    public  user(){}
    public user(int user_id, String username, String password, String email, String role, String status, String created_at, String updated_at) {
        this.user_id = user_id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @Override
    public String toString() {
        return "user{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                ", status='" + status + '\'' +
                ", created_at='" + created_at + '\'' +
                ", updated_at='" + updated_at + '\'' +
                '}';
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(String updated_at) {
        this.updated_at = updated_at;
    }
}
