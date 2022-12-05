package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.Service.UserService;
import com.KWdatabase.teamProject.dao.UserDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;


//
//    @GetMapping("/login")
//    public ResponseEntity<HttpStatus> login(@RequestBody Map<String,Object> json){
//        String id= (String) json.get("id");
//        User user = userService.findUser(id);
//        if(user==null||user.getPw().equals((String)json.get("pw"))) return null;
//    }

    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signUp(@RequestBody User user){
        return userDao.signUp(user);
    }

    @GetMapping("/signup/newId")
    public ResponseEntity<HttpStatus> checkDupID(@RequestParam String id){
        if(userService.findUser(id)==null) return ResponseEntity.ok().body(HttpStatus.OK);
        else return null;
    }

    @GetMapping("/findID")//이름 전화번호 이메일
    public ResponseEntity<String> findId(@RequestParam String username,@RequestParam String email,@RequestParam String phone_number){

//        String t = userService.findID(json);
//        System.out.println(t);
        return ResponseEntity.ok().body(userDao.findID(username,email,phone_number).getId());
    }

    //비밀번호찾기
    //ID 이름 전화번호 이메일
    @GetMapping("/findPW")//이름 전화번호 이메일
    public ResponseEntity<String> findPW(@RequestParam String id,@RequestParam String username,@RequestParam String email,@RequestParam String phone_number){
        return ResponseEntity.ok().body(userDao.findPW(id,username,email,phone_number).getPw());
    }

}
