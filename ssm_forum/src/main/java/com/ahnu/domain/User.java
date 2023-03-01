package com.ahnu.domain;
import java.util.Date;

public class User {
    private int user_id;
    private String username;
    private String password;
    private String nickname;
    private String user_sex;
    private String phone;
    private String avatar;
    private String user_autograph;
    private String user_time;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUser_autograph() {
        return user_autograph;
    }

    public void setUser_autograph(String user_autograph) {
        this.user_autograph = user_autograph;
    }


    public String getUser_time() {
        return user_time;
    }

    public void setUser_time(String user_time) {
        this.user_time = user_time;
    }

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", user_autograph='" + user_autograph + '\'' +
                ", user_time='" + user_time + '\'' +
                '}';
    }
}
