package com.ahnu.domain;

public class PostsLikeUser {
    private int posts_like_user;

    public int getPosts_like_user() {
        return posts_like_user;
    }

    public void setPosts_like_user(int posts_like_user) {
        this.posts_like_user = posts_like_user;
    }

    @Override
    public String toString() {
        return "PostsLikeUser{" +
                "posts_like_user=" + posts_like_user +
                '}';
    }
}
