package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Posts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PostsDao {
    public Posts getPost(int postId);
    void insertPost(@Param("post")Posts posts);
    void updatePost(@Param("post")Posts posts);

    void deletePost(int postId);
}
