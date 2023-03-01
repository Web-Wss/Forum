package com.ahnu.domain;

import java.util.List;

public class HomePosts {
    private int posts_id;
    private int user_id;
    private int yz_user_id;
    private int posts_bar_id;
    private String posts_title;
    private String posts_content;
    private String posts_time;
    private String username;
    private String nickname;
    private String user_sex;
    private String phone;
    private String avatar;
    private String user_autograph;
    private String user_time;
    private String posts_bar_name;
    private String posts_bar_logo_path;


    List<PostsImg> postsImgList;    //    帖子图片列表
    List<PostsCom> postsComList; // 帖子评论数
    List<PostsLike> postsLikeList; // 帖子喜欢数
    List<PostsLikeUser> postsLikeUserList; //当前用户是否喜欢当前帖子

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

    public int getYz_user_id() {
        return yz_user_id;
    }

    public void setYz_user_id(int yz_user_id) {
        this.yz_user_id = yz_user_id;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUser_sex() {
        return user_sex;
    }

    public void setUser_sex(String user_sex) {
        this.user_sex = user_sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getUser_autograph() {
        return user_autograph;
    }

    public void setUser_autograph(String user_autograph) {
        this.user_autograph = user_autograph;
    }

    public String getUser_time() {
        return user_time;
    }

    public void setUser_time(String user_time) {
        this.user_time = user_time;
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

    public List<PostsImg> getPostsImgList() {
        return postsImgList;
    }

    public void setPostsImgList(List<PostsImg> postsImgList) {
        this.postsImgList = postsImgList;
    }

    public List<PostsCom> getPostsComList() {
        return postsComList;
    }

    public void setPostsComList(List<PostsCom> postsComList) {
        this.postsComList = postsComList;
    }

    public List<PostsLike> getPostsLikeList() {
        return postsLikeList;
    }

    public void setPostsLikeList(List<PostsLike> postsLikeList) {
        this.postsLikeList = postsLikeList;
    }

    public List<PostsLikeUser> getPostsLikeUserList() {
        return postsLikeUserList;
    }

    public void setPostsLikeUserList(List<PostsLikeUser> postsLikeUserList) {
        this.postsLikeUserList = postsLikeUserList;
    }

    @Override
    public String toString() {
        return "HomePosts{" +
                "posts_id=" + posts_id +
                ", user_id=" + user_id +
                ", yz_user_id=" + yz_user_id +
                ", posts_bar_id=" + posts_bar_id +
                ", posts_title='" + posts_title + '\'' +
                ", posts_content='" + posts_content + '\'' +
                ", posts_time='" + posts_time + '\'' +
                ", username='" + username + '\'' +
                ", nickname='" + nickname + '\'' +
                ", user_sex='" + user_sex + '\'' +
                ", phone='" + phone + '\'' +
                ", avatar='" + avatar + '\'' +
                ", user_autograph='" + user_autograph + '\'' +
                ", user_time='" + user_time + '\'' +
                ", posts_bar_name='" + posts_bar_name + '\'' +
                ", posts_bar_logo_path='" + posts_bar_logo_path + '\'' +
                ", postsImgList=" + postsImgList +
                ", postsComList=" + postsComList +
                ", postsLikeList=" + postsLikeList +
                ", postsLikeUserList=" + postsLikeUserList +
                '}';
    }
}
