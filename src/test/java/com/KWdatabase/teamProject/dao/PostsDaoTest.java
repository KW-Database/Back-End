package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Posts;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class PostsDaoTest {

    @Autowired
    private PostsDao postsDao;


    @Test
    public void insertDataTest(){
        int postId= 1;
        String id ="KIKI";
        String title = "Test";
        String contents = "Test Contents";
        LocalDateTime postTime =LocalDateTime.now();
        int hitCount = 0;

        Posts posts = Posts.builder()
                .id(id)
                .postId(postId)
                .title(title)
                .contents(contents)
                .postTime(postTime)
                .hitCount(hitCount)
                .build();
        postsDao.insertPost(posts);

        Posts posts1 = postsDao.getPost(postId);
        assertThat(posts1.getId()).isEqualTo(posts.getId());

    }

    @Test
    public void updateDataTest(){
        int postId= 1;
        String id ="KIKI";
        String title = "modified";
        String contents = "modified";
        Posts posts1 = postsDao.getPost(postId);
        LocalDateTime postTime =posts1.getPostTime();
        int hitCount = posts1.getHitCount();

        Posts posts = Posts.builder()
                .id(id)
                .postId(postId)
                .title(title)
                .contents(contents)
                .postTime(postTime)
                .hitCount(hitCount)
                .build();
        postsDao.updatePost(posts);

        assertThat(posts1.getId()).isEqualTo(posts.getId());

    }

    @Test
    void deleteDataTest(){
        int postId=1;
        postsDao.deletePost(1);
        Posts posts = postsDao.getPost(postId);
    }

}