package com.ahnu.domain;

public class SearchResultFz {
    private String posts_bar_name;
    private int user_id;

    public String getPosts_bar_name() {
        return posts_bar_name;
    }

    public void setPosts_bar_name(String posts_bar_name) {
        this.posts_bar_name = posts_bar_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "SearchResultFz{" +
                "posts_bar_name='" + posts_bar_name + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
