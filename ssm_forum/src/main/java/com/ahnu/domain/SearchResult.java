package com.ahnu.domain;

import java.util.List;

public class SearchResult {
    private int posts_bar_id;
    private String posts_bar_name;
    private String posts_bar_logo_path;
    private int user_id;

    private int SearchBarLikeSum;
    private int SearchPostsSum;
    private int UserLike;

    public int getUserLike() {
        return UserLike;
    }

    public void setUserLike(int userLike) {
        UserLike = userLike;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getPosts_bar_id() {
        return posts_bar_id;
    }

    public void setPosts_bar_id(int posts_bar_id) {
        this.posts_bar_id = posts_bar_id;
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

    public int getSearchBarLikeSum() {
        return SearchBarLikeSum;
    }

    public void setSearchBarLikeSum(int searchBarLikeSum) {
        SearchBarLikeSum = searchBarLikeSum;
    }

    public int getSearchPostsSum() {
        return SearchPostsSum;
    }

    public void setSearchPostsSum(int searchPostsSum) {
        SearchPostsSum = searchPostsSum;
    }

    @Override
    public String toString() {
        return "SearchResult{" +
                "posts_bar_id=" + posts_bar_id +
                ", posts_bar_name='" + posts_bar_name + '\'' +
                ", posts_bar_logo_path='" + posts_bar_logo_path + '\'' +
                ", SearchBarLikeSum=" + SearchBarLikeSum +
                ", SearchPostsSum=" + SearchPostsSum +
                '}';
    }
}
