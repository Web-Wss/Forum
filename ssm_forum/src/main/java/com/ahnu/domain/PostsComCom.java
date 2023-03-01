package com.ahnu.domain;

public class PostsComCom {
    private int posts_com_com_id;
    private int posts_com_id;
    private int user_id;
    private String posts_com_com_time;

    private String posts_com_com_content;
    private String nickname;

    public int getPosts_com_com_id() {
        return posts_com_com_id;
    }

    public void setPosts_com_com_id(int posts_com_com_id) {
        this.posts_com_com_id = posts_com_com_id;
    }

    public int getPosts_com_id() {
        return posts_com_id;
    }

    public void setPosts_com_id(int posts_com_id) {
        this.posts_com_id = posts_com_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPosts_com_com_time() {
        return posts_com_com_time;
    }

    public void setPosts_com_com_time(String posts_com_com_time) {
        this.posts_com_com_time = posts_com_com_time;
    }

    public String getPosts_com_com_content() {
        return posts_com_com_content;
    }

    public void setPosts_com_com_content(String posts_com_com_content) {
        this.posts_com_com_content = posts_com_com_content;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "PostsComCom{" +
                "posts_com_com_id=" + posts_com_com_id +
                ", posts_com_id=" + posts_com_id +
                ", user_id=" + user_id +
                ", posts_com_com_time='" + posts_com_com_time + '\'' +
                ", posts_com_com_content='" + posts_com_com_content + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
