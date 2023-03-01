package com.ahnu.controller;

import com.ahnu.domain.*;
import com.ahnu.service.PostsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    //    获取吧表所有信息
    @RequestMapping("/postsbarall")
    @ResponseBody
    public Result postsbarall() {
        Result result = new Result();
        List<PostsBar> postsBarList = postsService.PostsBarAll();
        System.out.println("postsBarList=="+postsBarList);
        if (postsBarList.size() != 0) {
            result.setCode(200);
            result.setSuccess(true);
            result.setMsg("获取的吧表所有数据成功");
            result.setData(postsBarList);
        }else {
            result.setCode(500);
            result.setSuccess(false);
            result.setMsg("获取的吧表所有数据失败");
            result.setData(postsBarList);
        }
        return result;
    }

    //    发布帖子
    @RequestMapping("/publish")
    @ResponseBody
    public Result publish(MultipartFile[] uploadFile, int user_id, int bar_id, String titlevalue, String contentvalue, HttpServletRequest request) throws IOException {
        Result result = new Result();
        Posts posts = new Posts();
        posts.setUser_id(user_id);
        posts.setPosts_bar_id(bar_id);
        posts.setPosts_title(titlevalue);
        posts.setPosts_content(contentvalue);
//        将帖子信息插入数据库
        int PostsInfoSave = postsService.PostsInfoSave(posts);
        int posts_id = posts.getPosts_id();
        System.out.println("posts_id==="+posts_id);
        for (int i = 0; i < uploadFile.length; i ++) {
//            获取文件名
            String originalFilename = uploadFile[i].getOriginalFilename();
//            保存文件
            String path = request.getSession().getServletContext().getRealPath("/statics/posts_img");
            File file = new File(path);
            if (!file.exists()) {
                file.mkdirs();//没有则创建
            }
//            String posts_img_path = path + "\\" + posts_id + "_" + originalFilename;
//            将当前帖子的图片存入数据库
            String posts_img_path_sql = posts_id + "_" + originalFilename;
//            将图片存到服务器中
            uploadFile[i].transferTo(new File(path+"\\"+posts_id+"_"+originalFilename));
            PostsImg postsImg = new PostsImg();
            postsImg.setPosts_id(posts_id);
            postsImg.setPosts_img_path(posts_img_path_sql);
//            操作数据库
            postsService.publishAndImg(postsImg);
        }
        if (PostsInfoSave == 1) {
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("帖子发布成功");
            result.setData("成功");
        }else {
            result.setSuccess(false);
            result.setCode(500);
            result.setMsg("帖子发布失败");
            result.setData("失败");
        }
        return result;
    }

    //    首页帖子获取
        /*
        * 用户头像 1
        * 用户昵称 1
        * 帖吧名称 1
        * 帖子发布时间 1
        * 帖子标题 1
        * 帖子图片列表 1
        * 帖子评论数
        * 帖子点赞数
        * 我是否已经点赞
        * */
    @RequestMapping("/homepostslist")
    @ResponseBody
    public Result page( int pagenum, int pagesize, int yz_user_id) {
        System.out.println(pagenum);
        System.out.println(pagesize);
        System.out.println("yz_user_id==="+yz_user_id);
        Result result = new Result();
    //            设置分页的相关参数 当前页+每页显示的条数
        PageHelper.startPage(pagenum,pagesize);
        List<HomePosts> homePostsList = postsService.HomePostsAll(yz_user_id);
        for (HomePosts homePosts : homePostsList) {
            System.out.println(homePosts);
        }
    //            result.setData(JSON.toJSONString(homePostsList));
    //        获得与分页相关的参数
        PageInfo pageInfo = new PageInfo<>(homePostsList);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取的吧表所有数据");
        result.setData(pageInfo);
        System.out.println("当前页:"+pageInfo.getPageNum());
        System.out.println("每页显示条数:"+pageInfo.getPageSize());
        System.out.println("总条数:"+pageInfo.getTotal());
        System.out.println("总页数:"+pageInfo.getPages());
        System.out.println("上一页:"+pageInfo.getPrePage());
        System.out.println("下一页:"+pageInfo.getNextPage());
        System.out.println("是否是第一页:"+pageInfo.isIsFirstPage());
        System.out.println("是否是最后一页:"+pageInfo.isIsLastPage());
        return result;
    }


    //    吧帖子获取
    @RequestMapping("/barpostslist")
    @ResponseBody
    public Result barpostslist( int pagenum, int pagesize,int bar_id ,int yz_user_id) {
        Result result = new Result();
        BarPostsFz barPostsFz = new BarPostsFz();
        barPostsFz.setBar_id(bar_id);
        barPostsFz.setYz_user_id(yz_user_id);
        //            设置分页的相关参数 当前页+每页显示的条数
        PageHelper.startPage(pagenum,pagesize);
        List<HomePosts> homePostsList = postsService.BarPostsAll(barPostsFz);
        //        获得与分页相关的参数
        PageInfo pageInfo = new PageInfo<>(homePostsList);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取的吧表所有数据");
        result.setData(pageInfo);
        return result;
    }




//    用户点赞，根据当前用户及帖子id
//    用户点了赞，请求则取消点赞
//    用户没点赞，请求则点赞
    @RequestMapping("/userlike")
    @ResponseBody
    public Result userlike(@Param("user_id") int user_id,@Param("posts_id") int posts_id) {
        Result result = new Result();
        System.out.println("user_id==="+user_id);
        System.out.println("posts_id==="+posts_id);
//        查询当前用户有没有给当前帖子点赞
        PostsLike postsLike = new PostsLike();
        postsLike.setUser_id(user_id);
        postsLike.setPosts_id(posts_id);
        int IsLike = postsService.SelectUserLike(postsLike);
        System.out.println("IsLike=="+IsLike);
        if (IsLike != 0) {
//            删除当前点赞
            int IsDel = postsService.DeleteUserLike(postsLike);
            if (IsDel == 1) {
                result.setSuccess(true);
                result.setMsg("删除点赞成功");
                result.setCode(200);
                result.setData("删除点赞成功");
            }else {
                result.setSuccess(false);
                result.setMsg("删除点赞失败");
                result.setCode(500);
                result.setData("删除点赞失败");
            }
        }else {
//            添加点赞记录
            int IsInsert = postsService.InsertUserLike(postsLike);
            if (IsInsert == 1) {
                result.setSuccess(true);
                result.setMsg("添加点赞记录成功");
                result.setCode(200);
                result.setData("添加点赞记录成功");
            }else {
                result.setSuccess(false);
                result.setMsg("添加点赞记录失败");
                result.setCode(500);
                result.setData("添加点赞记录失败");
            }
        }

        return result;
    }

//    查询帖子详情页
    @RequestMapping("/postsdetail")
    @ResponseBody
    public Result postsdetail(int posts_id, int yz_user_id) {

        Result result = new Result();
        PostsDetailFz postsDetailFz = new PostsDetailFz();
        postsDetailFz.setPosts_id(posts_id);
        postsDetailFz.setYz_user_id(yz_user_id);
//        获取帖子详情页数据
        List<PostsDetail> postsDetails = postsService.PostsDetail(postsDetailFz);
        System.out.println(postsDetails);
        result.setSuccess(true);
        result.setCode(200);
        result.setMsg("获取帖子详情");
        result.setData(postsDetails);
        return result;
    }

//    帖子评论
    @RequestMapping("/postscom")
    @ResponseBody
    public Result postscom(int posts_id, String posts_com_content, int user_id) {
        Result result = new Result();
        PostsCom postsCom = new PostsCom();
        postsCom.setPosts_id(posts_id);
        postsCom.setPosts_com_content(posts_com_content);
        postsCom.setUser_id(user_id);
//        添加评论
        int i = postsService.InsertPostsCom(postsCom);
        if (i == 1) {
            result.setSuccess(true);
            result.setMsg("评论添加成功");
            result.setCode(200);
            result.setData("评论添加成功");
        }else {
            result.setSuccess(false);
            result.setMsg("评论添加失败");
            result.setCode(500);
            result.setData("评论添加失败");
        }
        return result;
    }

    //    子帖子评论
    @RequestMapping("/postscomcom")
    @ResponseBody
    public Result postscomcom(int posts_com_id, String posts_com_com_content, int user_id) {
        Result result = new Result();
        PostsComCom postsComCom = new PostsComCom();
        postsComCom.setPosts_com_id(posts_com_id);
        postsComCom.setPosts_com_com_content(posts_com_com_content);
        postsComCom.setUser_id(user_id);
        int i = postsService.InsertPostsComCom(postsComCom);
        if (i == 1) {
            result.setSuccess(true);
            result.setMsg("评论添加成功");
            result.setCode(200);
            result.setData("评论添加成功");
        }else {
            result.setSuccess(false);
            result.setMsg("评论添加失败");
            result.setCode(500);
            result.setData("评论添加失败");
        }
        return result;
    }

//    删除帖子评论
@RequestMapping("/postscomdelete")
@ResponseBody
public Result postscomdelete(int posts_com_id) {
    Result result = new Result();
    int i = postsService.DeleteCom(posts_com_id);
    int j = postsService.DeleteComCom(posts_com_id);
    if (i == 1 || j == 1) {
        result.setSuccess(true);
        result.setMsg("评论删除成功");
        result.setCode(200);
        result.setData("评论删除成功");
    }else {
        result.setSuccess(false);
        result.setMsg("评论删除失败");
        result.setCode(500);
        result.setData("评论删除失败");
    }
    return result;
}

    //    添加帖子浏览记录
    @RequestMapping("/postshistory")
    @ResponseBody
    public Result postshistory(int user_id, int posts_id, String posts_title, String posts_bar_name) {
        System.out.println("posts_title"+posts_title);
        System.out.println("posts_bar_name"+posts_bar_name);
        Result result = new Result();
        PostsHistory postsHistory = new PostsHistory();
        postsHistory.setUser_id(user_id);
        postsHistory.setPosts_id(posts_id);
        postsHistory.setPosts_title(posts_title);
        postsHistory.setPosts_bar_name(posts_bar_name);
//        查询数据库中是否有当前帖子浏览记录
        int i1 = postsService.SelectPostsHistory(postsHistory);
        if (i1 == 1) {
//            删除记录
            int i4 = postsService.DeletePostsHistory(postsHistory);
//            添加记录
            int i2 = postsService.InsertPostsHistory(postsHistory);
            if (i2 == 1) {
                result.setSuccess(true);
                result.setMsg("添加帖子记录成功");
                result.setCode(200);
                result.setData("添加帖子记录成功");
            }
        }else {
//            添加记录
            int i3 = postsService.InsertPostsHistory(postsHistory);
            if (i3 == 1) {
                result.setSuccess(true);
                result.setMsg("添加帖子记录成功");
                result.setCode(200);
                result.setData("添加帖子记录成功");
            }
        }
        return result;
    }


    //    帖子浏览记录
    @RequestMapping("/getpostshistory")
    @ResponseBody
    public Result getpostshistory(int user_id) {
        Result result = new Result();
        List<PostsHistory> postsHistoryList = postsService.GetPostsHistory(user_id);
        System.out.println(postsHistoryList);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取浏览历史");
        result.setData(postsHistoryList);
        return result;
    }

//    贴吧搜索
    @RequestMapping("/searchpostsbar")
    @ResponseBody
    public Result searchpostsbar(String posts_bar_name,int user_id) {
        Result result = new Result();
        SearchResultFz searchResultFz = new SearchResultFz();
        searchResultFz.setPosts_bar_name(posts_bar_name);
        searchResultFz.setUser_id(user_id);
//        获取搜索结果
        List<SearchResult> searchResultList = postsService.SearchBar(searchResultFz);
        result.setCode(200);
        result.setSuccess(true);
        result.setMsg("获取贴吧搜索结果");
        result.setData(searchResultList);
        return result;
    }

//帖子收藏
    @RequestMapping("/postslove")
    @ResponseBody
    public Result postslove(int posts_id, int save_user_id) {
        Result result = new Result();
        UserSavePosts userSavePosts = new UserSavePosts();
        userSavePosts.setPosts_id(posts_id);
        userSavePosts.setSave_user_id(save_user_id);
//        查询数据库中是否有当前收藏记录
        int i1 = postsService.SelectPostsSave(userSavePosts);
        if (i1 == 1) {
            result.setSuccess(true);
            result.setCode(200);
            result.setMsg("数据库中已有当前收藏记录");
            result.setData("收藏帖子成功");
        }else{
            int i2 = postsService.PostsSave(userSavePosts);
            if (i2 == 1) {
                result.setSuccess(true);
                result.setCode(200);
                result.setMsg("收藏帖子成功");
                result.setData("收藏帖子成功");
            }
        }
        return result;
    }

}
