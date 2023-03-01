package com.ahnu.mapper;

import com.ahnu.domain.*;

import java.util.List;

public interface UserMapper {

    //    登录验证
    public List<User> Login(User user);

    //    用户注册（手机号验证）
    public int RegisterPhone(String phone);

    //    用户注册（用户名验证）
    public int RegisterUsername(String username);

    //    用户注册
    public int RegisterRegister(User user);

    //    获取用户信息
    public List<User> GetUserInfo(User user);

    //    根据用户id获取头像地址
    public String PathFromUserId(int user_id);

    //    修改用户头像地址
    public int UpdateUserAvatar(User user);

    //    修改用户信息
    public int EditUserInfo(User user);

    //    修改用户昵称
    public int EditUserNickName(User user);

//    获取用户关注的用户
    public List<UsersLike> GetUserLikeUser(int user_id);

    //    获取用户粉丝
    public List<UsersLike> GetLikeUser(int user_id);

//    用户关注的吧
    public List<UserLikeBar> UserLikeBar(int user_id);

//    用户收藏的帖子查询
    public List<UserSavePosts> GetUserSave(int save_user_id);

//    获取My页面的四个信息
    public List<MyFourInfo> SelectMyFourInfo(int user_id);

//    查询是否有关注当前用户
    public int SelectUserLikeUser(UsersLike usersLike);

//    添加用户关注用户
    public int InsertUserLike(UsersLike usersLike);

//    取消当前用户关注的用户
    public int CancelUserLikeUser(UsersLike usersLike);

}
