package com.ahnu.domain;

public class MyFourInfo {
    private int user_id;
    private int user_like_sum;
    private int like_user_sum;
    private int like_bar_sum;
    private int user_posts_sum;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_like_sum() {
        return user_like_sum;
    }

    public void setUser_like_sum(int user_like_sum) {
        this.user_like_sum = user_like_sum;
    }

    public int getLike_user_sum() {
        return like_user_sum;
    }

    public void setLike_user_sum(int like_user_sum) {
        this.like_user_sum = like_user_sum;
    }

    public int getLike_bar_sum() {
        return like_bar_sum;
    }

    public void setLike_bar_sum(int like_bar_sum) {
        this.like_bar_sum = like_bar_sum;
    }

    public int getUser_posts_sum() {
        return user_posts_sum;
    }

    public void setUser_posts_sum(int user_posts_sum) {
        this.user_posts_sum = user_posts_sum;
    }

    @Override
    public String toString() {
        return "MyFourInfo{" +
                "user_id=" + user_id +
                ", user_like_sum=" + user_like_sum +
                ", like_user_sum=" + like_user_sum +
                ", like_bar_sum=" + like_bar_sum +
                ", user_posts_sum=" + user_posts_sum +
                '}';
    }
}
