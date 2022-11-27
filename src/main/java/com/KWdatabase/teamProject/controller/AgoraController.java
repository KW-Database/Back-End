package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.Posts;
import com.KWdatabase.teamProject.dao.PostsDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AgoraController {

    private final PostsDao postsDao;

    @GetMapping("/agora")
    public ResponseEntity<List<Posts>> viewPosts(){
        List<Posts> list = postsDao.getPostsList();
        return ResponseEntity.ok().body(list);
    }
}
