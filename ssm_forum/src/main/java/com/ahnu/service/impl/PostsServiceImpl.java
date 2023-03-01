package com.ahnu.service.impl;

import com.ahnu.domain.*;
import com.ahnu.mapper.PostsMapper;
import com.ahnu.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostsServiceImpl implements PostsService {

    @Autowired
    private PostsMapper postsMapper;

    @Override
    public List<PostsBar> PostsBarAll() {
        return postsMapper.PostsBarAll();
    }

    @Override
    public int PostsInfoSave(Posts posts) {
        return postsMapper.PostsInfoSave(posts);
    }

    @Override
    public int publishAndImg(PostsImg postsImg) {
        return postsMapper.publishAndImg(postsImg);
    }

    @Override
    public List<HomePosts> HomePostsAll(int yz_user_id) {
        return postsMapper.HomePostsAll(yz_user_id);
    }

    @Override
    public List<HomePosts> BarPostsAll(BarPostsFz barPostsFz) {
        return postsMapper.BarPostsAll(barPostsFz);
    }

    @Override
    public int SelectUserLike(PostsLike postsLike) {
        return postsMapper.SelectUserLike(postsLike);
    }

    @Override
    public int DeleteUserLike(PostsLike postsLike) {
        return postsMapper.DeleteUserLike(postsLike);
    }

    @Override
    public int InsertUserLike(PostsLike postsLike) {
        return postsMapper.InsertUserLike(postsLike);
    }

    @Override
    public List<PostsDetail> PostsDetail(PostsDetailFz postsDetailFz) {
        return postsMapper.PostsDetail(postsDetailFz);
    }

    @Override
    public int InsertPostsCom(PostsCom postsCom) {
        return postsMapper.InsertPostsCom(postsCom);
    }

    @Override
    public int InsertPostsComCom(PostsComCom postsComCom) {
        return postsMapper.InsertPostsComCom(postsComCom);
    }

    @Override
    public int DeleteCom(int posts_com_id) {
        return postsMapper.DeleteCom(posts_com_id);
    }

    @Override
    public int DeleteComCom(int posts_com_id) {
        return postsMapper.DeleteComCom(posts_com_id);
    }

    @Override
    public int SelectPostsHistory(PostsHistory postsHistory) {
        return postsMapper.SelectPostsHistory(postsHistory);
    }

    @Override
    public int InsertPostsHistory(PostsHistory postsHistory) {
        return postsMapper.InsertPostsHistory(postsHistory);
    }

    @Override
    public int DeletePostsHistory(PostsHistory postsHistory) {
        return postsMapper.DeletePostsHistory(postsHistory);
    }

    @Override
    public List<PostsHistory> GetPostsHistory(int user_id) {
        return postsMapper.GetPostsHistory(user_id);
    }

    @Override
    public List<SearchResult> SearchBar(SearchResultFz searchResultFz) {
        return postsMapper.SearchBar(searchResultFz);
    }

    @Override
    public int SelectPostsSave(UserSavePosts userSavePosts) {
        return postsMapper.SelectPostsSave(userSavePosts);
    }

    @Override
    public int PostsSave(UserSavePosts userSavePosts) {
        return postsMapper.PostsSave(userSavePosts);
    }

}
