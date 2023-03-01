package com.ahnu.domain;

public class PostsBarLike {
    private int posts_bar_like_id;
    private int posts_bar_id;
    private int user_id;

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

    @Override
    public String toString() {
        return "PostsBarLike{" +
                "posts_bar_like_id=" + posts_bar_like_id +
                ", posts_bar_id=" + posts_bar_id +
                ", user_id=" + user_id +
                '}';
    }
}
