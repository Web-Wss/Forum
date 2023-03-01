package com.ahnu.mapper;

import com.ahnu.domain.*;

import java.util.List;

public interface PostsMapper {

    //    获取吧表所有数据
    public List<PostsBar> PostsBarAll();

    //    发布帖子
    public int PostsInfoSave(Posts posts);

    //    将帖子图片存入数据库
    public int publishAndImg(PostsImg postsImg);

    //    首页帖子
    public List<HomePosts> HomePostsAll(int yz_user_id);

    //    吧里帖子
    public List<HomePosts> BarPostsAll(BarPostsFz barPostsFz);

//    查询当前用户有没有给当前帖子点赞
    public int SelectUserLike(PostsLike postsLike);

    //    删除当前用户的点赞
    public int DeleteUserLike(PostsLike postsLike);

    //    添加点赞记录
    public int InsertUserLike(PostsLike postsLike);

    //    帖子详情页
    public List<PostsDetail> PostsDetail(PostsDetailFz postsDetailFz);

//    帖子评论
    public int InsertPostsCom(PostsCom postsCom);

    //    子帖子评论
    public int InsertPostsComCom(PostsComCom postsComCom);

//    删除主帖子评论
    public int DeleteCom(int posts_com_id);
    //    删除帖子评论
    public int DeleteComCom(int posts_com_id);

//    查询数据库中是否有当前帖子的记录
    public int SelectPostsHistory(PostsHistory postsHistory);
    //    将浏览贴吧的历史存入数据库
    public int InsertPostsHistory(PostsHistory postsHistory);
//    删除当前帖子浏览历史记录
    public int DeletePostsHistory(PostsHistory postsHistory);

//    获取浏览历史
    public List<PostsHistory> GetPostsHistory(int user_id);

//    贴吧模糊搜索
    public List<SearchResult> SearchBar(SearchResultFz searchResultFz);

//    添加帖子收藏
    public int SelectPostsSave(UserSavePosts userSavePosts);
    public int PostsSave(UserSavePosts userSavePosts);

}
