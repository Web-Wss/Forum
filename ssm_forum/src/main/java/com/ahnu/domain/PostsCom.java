package com.ahnu.domain;

import java.util.List;

public class PostsCom {
    private int posts_com_id;
    private int posts_id;
    private String posts_com_content;
    private int user_id;

    private String avatar;
    private String nickname;
    private String posts_com_time;

    List<PostsComCom> postsComComList;

    public List<PostsComCom> getPostsComComList() {
        return postsComComList;
    }

    public void setPostsComComList(List<PostsComCom> postsComComList) {
        this.postsComComList = postsComComList;
    }

    public int getPosts_com_id() {
        return posts_com_id;
    }

    public void setPosts_com_id(int posts_com_id) {
        this.posts_com_id = posts_com_id;
    }

    public int getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(int posts_id) {
        this.posts_id = posts_id;
    }

    public String getPosts_com_content() {
        return posts_com_content;
    }

    public void setPosts_com_content(String posts_com_content) {
        this.posts_com_content = posts_com_content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPosts_com_time() {
        return posts_com_time;
    }

    public void setPosts_com_time(String posts_com_time) {
        this.posts_com_time = posts_com_time;
    }

    @Override
    public String toString() {
        return "PostsCom{" +
                "posts_com_id=" + posts_com_id +
                ", posts_id=" + posts_id +
                ", posts_com_content='" + posts_com_content + '\'' +
                ", user_id=" + user_id +
                ", avatar='" + avatar + '\'' +
                ", nickname='" + nickname + '\'' +
                ", posts_com_time='" + posts_com_time + '\'' +
                ", postsComComList=" + postsComComList +
                '}';
    }
}
