package com.ahnu.domain;

public class UserLikeBar {
    private int posts_bar_like_id;
    private int posts_bar_id;
    private int user_id;
    private String posts_bar_name;
    private String posts_bar_logo_path;

    public int getPosts_bar_like_id() {
        return posts_bar_like_id;
    }

    public void setPosts_bar_like_id(int posts_bar_like_id) {
        this.posts_bar_like_id = posts_bar_like_id;
    }

    public int getPosts_bar_id() {
        return posts_bar_id;
    }

    public void setPosts_bar_id(int posts_bar_id) {
        this.posts_bar_id = posts_bar_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getPosts_bar_name() {
        return posts_bar_name;
    }

    public void setPosts_bar_name(String posts_bar_name) {
        this.posts_bar_name = posts_bar_name;
    }

    public String getPosts_bar_logo_path() {
        return posts_bar_logo_path;
    }

    public void setPosts_bar_logo_path(String posts_bar_logo_path) {
        this.posts_bar_logo_path = posts_bar_logo_path;
    }

    @Override
    public String toString() {
        return "UserLikeBar{" +
                "posts_bar_like_id=" + posts_bar_like_id +
                ", posts_bar_id=" + posts_bar_id +
                ", user_id=" + user_id +
                ", posts_bar_name='" + posts_bar_name + '\'' +
                ", posts_bar_logo_path='" + posts_bar_logo_path + '\'' +
                '}';
    }
}
