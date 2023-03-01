package com.ahnu.controller;

import com.ahnu.domain.*;
import com.ahnu.mapper.UserMapper;
import com.ahnu.service.UserService;
import com.ahnu.utils.JWTUtils;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    User user = new User();

    @Autowired
    private UserService userService;

//    用户登录验证
    @RequestMapping("login")
    @ResponseBody
    public Result login(String phone, String password) {
        System.out.println(phone);
        System.out.println(password);
        Result result = new Result();
        user.setPhone(phone);
        user.setPassword(password);
        List users = userService.Login(user);
        System.out.println(users);
        System.out.println(users.size());
        if (users.size() != 0) {
            String token = JWTUtils.generateToken(phone);
            users.add(token);
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("获取个人信息成功");
            result.setData(users);
        }else {
            result.setSuccess(true);
            result.setCode(500);
            result.setMsg("获取个人信息失败");
        }
        return result;
    }

    //    用户注册（验证当前手机号是否被注册）
    @RequestMapping("/register/phone")
    @ResponseBody
    public Result registerphone(String phone) {
        Result result = new Result();
        int i = userService.RegisterPhone(phone);
        if (i != 0) {
            result.setCode(200);
            result.setMsg("手机号是否重复");
            result.setSuccess(true);
            result.setData("重复");
        }else {
            result.setCode(200);
            result.setMsg("手机号是否重复");
            result.setSuccess(true);
            result.setData("不重复");
        }
        return result;
    }

    //    用户注册（用户名验证）
    @RequestMapping("/register/username")
    @ResponseBody
    public Result registerusername(String username) {
        Result result = new Result();
        int i = userService.RegisterUsername(username);
        if (i != 0) {
            result.setCode(200);
            result.setMsg("用户名是否重复");
            result.setSuccess(true);
            result.setData("重复");
        }else {
            result.setCode(200);
            result.setMsg("用户名是否重复");
            result.setSuccess(true);
            result.setData("不重复");
        }
        return result;
    }

    //    用户注册
    @RequestMapping("/register/register")
    @ResponseBody
    public Result registerregister(String username,String password, String phone) {
        Result result = new Result();
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setPhone(phone);
//        设置用户头像
        user.setAvatar("github.jpg");
        int i = userService.RegisterRegister(user);
        if (i == 1) {
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("注册成功");
            result.setData(i);
        }else {
            result.setCode(500);
            result.setSuccess(false);
            result.setMsg("注册失败");
            result.setData(i);
        }
        return result;
    }

//    获取用户信息
    @RequestMapping("/info")
    @ResponseBody
    public Result info(int user_id, String phone) {
        Result result = new Result();
        user.setUser_id(user_id);
        user.setPhone(phone);
        List users = userService.GetUserInfo(user);
        System.out.println("获取个人信息"+users);
        if (users.size() != 0) {
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("获取个人信息");
            result.setData(users);
        }else {
            result.setSuccess(false);
            result.setCode(500);
            result.setMsg("获取个人信息");
            result.setData(null);
        }
        return result;
    }

//    修改用户头像
    @RequestMapping("/upload_avatar")
    @ResponseBody
    public Result upload_avatar(MultipartFile uploadFile,int user_id, HttpServletRequest request) throws IOException {
        Result result = new Result();
//        获取文件名
        String originalFilename = uploadFile.getOriginalFilename();
        System.out.println(originalFilename);
        //        保存文件
        String path = request.getSession().getServletContext().getRealPath("/statics/user_avatar");
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();//没有则创建
        }
        //        设置头像文件名（用户id_文件名.jpg/png）
        System.out.println(path);
        String avatar_path = user_id + "_" + originalFilename;
        uploadFile.transferTo(new File(path+"\\"+user_id+"_"+originalFilename));
        //        将用户头像地址存入数据库
//        获取用户原来的头像地址
        String delPath = userService.PathFromUserId(user_id);
        if (delPath == "github.jpg") {
            System.out.println("不需要删除");
        }else {
            File file1 = new File(path + "\\" + delPath);
            if (file1.delete()) {
                System.out.println("本地文件删除了");
            }else {
                System.out.println("本地文件删除失败了");
            }
//            修改用户头像记录
            user.setAvatar(avatar_path);
            user.setUser_id(user_id);
            int i = userService.UpdateUserAvatar(user);
            if (i == 1) {
                System.out.println("数据库存储成功");
                result.setCode(200);
                result.setMsg("修改成功");
                result.setSuccess(true);
                result.setData("修改成功");
            }else {
                System.out.println("数据库存储失败");
                result.setCode(500);
                result.setMsg("修改失败");
                result.setSuccess(true);
                result.setData("修改失败");
            }
        }
        return result;
    }

    //    修改用户信息
    @RequestMapping("/edituserinfo")
    @ResponseBody
    public Result editinformation(int user_id ,String user_sex,String user_autograph) {
        Result result = new Result();
        User user = new User();
        user.setUser_id(user_id);
        user.setUser_sex(user_sex);
        user.setUser_autograph(user_autograph);
        int i = userService.EditUserInfo(user);
        if (i == 1) {
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("修改结果");
            result.setData("成功");
        } else {
            result.setCode(500);
            result.setSuccess(false);
            result.setMsg("修改失败");
            result.setData("ok");
        }
        return result;
    }

//    修改用户昵称
    @RequestMapping("/editusernickname")
    @ResponseBody
    public Result editusernickname(int user_id,String nickname) {
        Result result = new Result();
        user.setUser_id(user_id);
        user.setNickname(nickname);
        int i = userService.EditUserNickName(user);
        if (i == 1) {
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("昵称修改及结果");
            result.setData("成功");
        }else {
            result.setCode(500);
            result.setSuccess(false);
            result.setMsg("昵称修改及结果");
            result.setData("失败");
        }
        return result;
    }

    //    获取用户关注的用户
    @RequestMapping("/userlikeuser")
    @ResponseBody
    public Result userlikeuser(int user_id) {
        Result result = new Result();
        List<UsersLike> usersLikes = userService.GetUserLikeUser(user_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取当前用户关注的用户");
        result.setData(usersLikes);
        return result;
    }

    //    获取用户粉丝
    @RequestMapping("/likeuser")
    @ResponseBody
    public Result likeuser(int user_id) {
        Result result = new Result();
        List<UsersLike> usersLikes = userService.GetLikeUser(user_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取当前用户的粉丝列表");
        result.setData(usersLikes);
        return result;
    }

    //    用户关注的吧
    @RequestMapping("/userlikebar")
    @ResponseBody
    public Result userlikebar(int user_id) {
        Result result = new Result();
        List<UserLikeBar> userLikeBarList = userService.UserLikeBar(user_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取当前用户关注的吧");
        result.setData(userLikeBarList);
        return result;
    }

//    用户收藏的帖子
    @RequestMapping("/usersaveposts")
    @ResponseBody
    public Result usersaveposts(int save_user_id) {
        Result result = new Result();
        List<UserSavePosts> userSavePostsList = userService.GetUserSave(save_user_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取用户收藏的帖子");
        result.setData(userSavePostsList);
        return result;
    }


//    My页面的四个数据
    @RequestMapping("/myfourinfo")
    @ResponseBody
    public Result myfourinfo(int user_id) {
        Result result = new Result();
        List<MyFourInfo> myFourInfos = userService.SelectMyFourInfo(user_id);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("我的四个信息");
        result.setData(myFourInfos);
        return result;
    }

//    查询是否有关注当前用户
    @RequestMapping("/selectuserlikeuser")
    @ResponseBody
    public Result selectuserlikeuser(int user_id, int user_b_id) {
        Result result = new Result();
        UsersLike usersLike = new UsersLike();
        usersLike.setUser_id(user_id);
        usersLike.setUser_b_id(user_b_id);
        int i = userService.SelectUserLikeUser(usersLike);
        if (i == 1) {
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("查询当前用户是否关注的要查询的用户");
            result.setData(1);
        }else {
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("查询当前用户是否关注的要查询的用户");
            result.setData(0);
        }
        return result;
    }

    //添加用户关注用户
    @RequestMapping("/insertuserlike")
    @ResponseBody
    public Result insertuserlike(int user_id, int user_b_id) {
        Result result = new Result();
        UsersLike usersLike = new UsersLike();
        usersLike.setUser_id(user_id);
        usersLike.setUser_b_id(user_b_id);
        int i = userService.InsertUserLike(usersLike);
        if (i == 1) {
            result.setData(1);
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("关注成功");
        }


        return  result;
    }

//    取消当前用户关注的用户
    @RequestMapping("/canceluserlikeuser")
    @ResponseBody
    public Result canceluserlikeuser(int user_id, int user_b_id) {
        Result result = new Result();
        UsersLike usersLike = new UsersLike();
        usersLike.setUser_id(user_id);
        usersLike.setUser_b_id(user_b_id);
        int i = userService.CancelUserLikeUser(usersLike);
        if (i == 1) {
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("取消关注成功");
            result.setData("取消关注成功");
        }
        return result;
    }




}
