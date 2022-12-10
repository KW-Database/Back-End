package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.Posts;
import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.dao.PostsDao;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/agora")
public class PostController {

    @Autowired
    PostsDao postsDao;

    @GetMapping("/{postId}")
    public ResponseEntity<Posts> viewPostContent(@PathVariable int postId){
        Posts posts = postsDao.getPost(postId);
        return ResponseEntity.ok().body(posts);
    }

    @PostMapping("/{postId}")
    public ResponseEntity<HttpStatus> hitCount(@PathVariable int postId){
        Posts posts = postsDao.getPost(postId);
        posts.setHitCount(posts.getHitCount()+1);
        postsDao.updatePost(posts);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/write")
    public ResponseEntity<HttpStatus> writeNewPost(@RequestBody Posts posts){
        postsDao.insertPost(posts);
        Posts posts1 = postsDao.getPost(posts.getPostId());
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/{postId}/update")
    public ResponseEntity<HttpStatus> updatePost(@PathVariable int postId, @RequestBody Posts posts){
        postsDao.updatePost(posts);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{postId}/delete")
    public ResponseEntity<HttpStatus> deletePost(@PathVariable int postId){
        postsDao.deletePost(postId);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
