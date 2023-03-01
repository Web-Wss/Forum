package com.ahnu.domain;

public class PostsLike {
    private int user_id;
    private int posts_id;


    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(int posts_id) {
        this.posts_id = posts_id;
    }

    @Override
    public String toString() {
        return "PostsLikeSum{" +
                "user_id=" + user_id +
                ", posts_id=" + posts_id +
                '}';
    }
}
