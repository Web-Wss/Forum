package com.ahnu.service.impl;

import com.ahnu.domain.*;
import com.ahnu.mapper.UserMapper;
import com.ahnu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> Login(User user) {
        return userMapper.Login(user);
    }

    @Override
    public int RegisterPhone(String phone) {
        return userMapper.RegisterPhone(phone);
    }

    @Override
    public int RegisterUsername(String username) {
        return userMapper.RegisterUsername(username);
    }

    @Override
    public int RegisterRegister(User user) {
        return userMapper.RegisterRegister(user);
    }

    @Override
    public List<User> GetUserInfo(User user) {
        return userMapper.GetUserInfo(user);
    }

    @Override
    public String PathFromUserId(int user_id) {
        return userMapper.PathFromUserId(user_id);
    }

    @Override
    public int UpdateUserAvatar(User user) {
        return userMapper.UpdateUserAvatar(user);
    }

    @Override
    public int EditUserInfo(User user) {
        return userMapper.EditUserInfo(user);
    }

    @Override
    public int EditUserNickName(User user) {
        return userMapper.EditUserNickName(user);
    }

    @Override
    public List<UsersLike> GetUserLikeUser(int user_id) {
        return userMapper.GetUserLikeUser(user_id);
    }

    @Override
    public List<UsersLike> GetLikeUser(int user_id) {
        return userMapper.GetLikeUser(user_id);
    }

    @Override
    public List<UserLikeBar> UserLikeBar(int user_id) {
        return userMapper.UserLikeBar(user_id);
    }

    @Override
    public List<UserSavePosts> GetUserSave(int save_user_id) {
        return userMapper.GetUserSave(save_user_id);
    }

    @Override
    public List<MyFourInfo> SelectMyFourInfo(int user_id) {
        return userMapper.SelectMyFourInfo(user_id);
    }

    @Override
    public int SelectUserLikeUser(UsersLike usersLike) {
        return userMapper.SelectUserLikeUser(usersLike);
    }

    @Override
    public int InsertUserLike(UsersLike usersLike) {
        return userMapper.InsertUserLike(usersLike);
    }

    @Override
    public int CancelUserLikeUser(UsersLike usersLike) {
        return userMapper.CancelUserLikeUser(usersLike);
    }
}
