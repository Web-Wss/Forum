package com.ahnu.domain;

public class PostsImg {
    private int posts_img_id;
    private int posts_id;
    private String posts_img_path;

    public int getPosts_img_id() {
        return posts_img_id;
    }

    public void setPosts_img_id(int posts_img_id) {
        this.posts_img_id = posts_img_id;
    }

    public int getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(int posts_id) {
        this.posts_id = posts_id;
    }

    public String getPosts_img_path() {
        return posts_img_path;
    }

    public void setPosts_img_path(String posts_img_path) {
        this.posts_img_path = posts_img_path;
    }

    @Override
    public String toString() {
        return "PostsImg{" +
                "posts_img_id=" + posts_img_id +
                ", posts_id=" + posts_id +
                ", posts_img_path='" + posts_img_path + '\'' +
                '}';
    }
}
