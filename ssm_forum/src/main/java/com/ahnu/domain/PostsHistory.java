package com.ahnu.domain;

public class PostsHistory {
    private int history_id;
    private int posts_id;
    private int user_id;
    private String posts_title;
    private String posts_bar_name;
    private String history_time;

    public int getHistory_id() {
        return history_id;
    }

    public void setHistory_id(int history_id) {
        this.history_id = history_id;
    }

    public int getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(int posts_id) {
        this.posts_id = posts_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPosts_title() {
        return posts_title;
    }

    public void setPosts_title(String posts_title) {
        this.posts_title = posts_title;
    }

    public String getPosts_bar_name() {
        return posts_bar_name;
    }

    public void setPosts_bar_name(String posts_bar_name) {
        this.posts_bar_name = posts_bar_name;
    }

    public String getHistory_time() {
        return history_time;
    }

    public void setHistory_time(String history_time) {
        this.history_time = history_time;
    }

    @Override
    public String toString() {
        return "PostsHistory{" +
                "history_id=" + history_id +
                ", posts_id=" + posts_id +
                ", user_id=" + user_id +
                ", posts_title='" + posts_title + '\'' +
                ", posts_bar_name='" + posts_bar_name + '\'' +
                ", history_time='" + history_time + '\'' +
                '}';
    }
}
