package com.ahnu.domain;

//帖子详情赋值
public class PostsDetailFz {
    private int posts_id;
    private int yz_user_id;

    public int getPosts_id() {
        return posts_id;
    }

    public void setPosts_id(int posts_id) {
        this.posts_id = posts_id;
    }

    public int getYz_user_id() {
        return yz_user_id;
    }

    public void setYz_user_id(int yz_user_id) {
        this.yz_user_id = yz_user_id;
    }

    @Override
    public String toString() {
        return "PostsDetailFz{" +
                "posts_id=" + posts_id +
                ", yz_user_id=" + yz_user_id +
                '}';
    }
}
