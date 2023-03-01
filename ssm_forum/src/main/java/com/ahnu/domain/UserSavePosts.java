package com.ahnu.domain;

import java.util.List;

//用户收藏的帖子
public class UserSavePosts {
    private int posts_save_id;
    private int posts_id;
    private int save_user_id;

    private int user_like_sum;

    List<User> user;
    List<Posts> posts;
    List<PostsImg> postsImgList;

    public int getPosts_save_id() {
        return posts_save_id;
    }

    public void setPosts_save_id(int posts_save_id) {
        this.posts_save_id = posts_save_id;
    }

    public int getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(int posts_id) {
        this.posts_id = posts_id;
    }

    public int getSave_user_id() {
        return save_user_id;
    }

    public void setSave_user_id(int save_user_id) {
        this.save_user_id = save_user_id;
    }

    public int getUser_like_sum() {
        return user_like_sum;
    }

    public void setUser_like_sum(int user_like_sum) {
        this.user_like_sum = user_like_sum;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }

    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    public List<PostsImg> getPostsImgList() {
        return postsImgList;
    }

    public void setPostsImgList(List<PostsImg> postsImgList) {
        this.postsImgList = postsImgList;
    }

    @Override
    public String toString() {
        return "UserSavePosts{" +
                "posts_save_id=" + posts_save_id +
                ", posts_id=" + posts_id +
                ", save_user_id=" + save_user_id +
                ", user_like_sum=" + user_like_sum +
                ", user=" + user +
                ", posts=" + posts +
                ", postsImgList=" + postsImgList +
                '}';
    }
}
