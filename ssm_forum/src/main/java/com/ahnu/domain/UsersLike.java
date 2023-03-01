package com.ahnu.domain;

import java.util.List;

public class UsersLike {
    private int users_like_id;
    private int user_id;
    private int user_b_id;
    private int IsMutual;
    private int IsMutuals;

    public int getIsMutuals() {
        return IsMutuals;
    }

    public void setIsMutuals(int isMutuals) {
        IsMutuals = isMutuals;
    }

    public int getIsMutual() {
        return IsMutual;
    }

    public void setIsMutual(int isMutual) {
        IsMutual = isMutual;
    }

    List<User> userList;


    public int getUsers_like_id() {
        return users_like_id;
    }

    public void setUsers_like_id(int users_like_id) {
        this.users_like_id = users_like_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_b_id() {
        return user_b_id;
    }

    public void setUser_b_id(int user_b_id) {
        this.user_b_id = user_b_id;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "UsersLike{" +
                "users_like_id=" + users_like_id +
                ", user_id=" + user_id +
                ", user_b_id=" + user_b_id +
                ", userList=" + userList +
                '}';
    }
}
