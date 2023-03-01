package com.ahnu.domain;

import java.util.List;

public class Posts {
    private int posts_id;
    private int user_id;
    private int posts_bar_id;
    private String posts_title;
    private String posts_content;
    private String posts_time;

    List<User> user;

    public List<User> getUsers() {
        return user;
    }

    public void setUsers(List<User> user) {
        this.user = user;
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

    public int getPosts_bar_id() {
        return posts_bar_id;
    }

    public void setPosts_bar_id(int posts_bar_id) {
        this.posts_bar_id = posts_bar_id;
    }

    public String getPosts_title() {
        return posts_title;
    }

    public void setPosts_title(String posts_title) {
        this.posts_title = posts_title;
    }

    public String getPosts_content() {
        return posts_content;
    }

    public void setPosts_content(String posts_content) {
        this.posts_content = posts_content;
    }

    public String getPosts_time() {
        return posts_time;
    }

    public void setPosts_time(String posts_time) {
        this.posts_time = posts_time;
    }

    @Override
    public String toString() {
        return "Posts{" +
                "posts_id=" + posts_id +
                ", user_id=" + user_id +
                ", posts_bar_id=" + posts_bar_id +
                ", posts_title='" + posts_title + '\'' +
                ", posts_content='" + posts_content + '\'' +
                ", posts_time='" + posts_time + '\'' +
                '}';
    }
}
