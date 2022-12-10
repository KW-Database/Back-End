package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.Model.NewPwDto;
import com.KWdatabase.teamProject.Service.UserService;
import com.KWdatabase.teamProject.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserDao userDao;

    private final HttpServletResponse response;

//
//    @GetMapping("/login")
//    public ResponseEntity<HttpStatus> login(@RequestBody Map<String,Object> json){
//        String id= (String) json.get("id");
//        User user = userService.findUser(id);
//        if(user==null||user.getPw().equals((String)json.get("pw"))) return null;
//    }

    
    @GetMapping("/loginFail")
    public void loginFail(){
        String redirectURL = "http://localhost:3000/login";
        try{
            response.sendRedirect(redirectURL);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @GetMapping("/login")
    public void loginSuccess(){
        String redirectURL = "http://localhost:3000";
        try{
            response.sendRedirect(redirectURL);
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @GetMapping("/logout")
    public void logoutSuccess(){
        String redirectURL = "http://localhost:3000/login";
        try{
            response.sendRedirect(redirectURL);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    @PostMapping("/signup")
    public ResponseEntity<HttpStatus> signUp(@RequestBody User user){
        String pw = user.getPw();
        user.setPw(new BCryptPasswordEncoder().encode(pw));
        userDao.signUp(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping("/signup/DupId")
    public ResponseEntity<HttpStatus> checkDupID(@RequestParam String id){
        if(userService.findUser(id)==null) return ResponseEntity.ok().body(HttpStatus.OK);
        else return null;
    }

    @GetMapping("/findID")//이름 전화번호 이메일
    public ResponseEntity<String> findId(@RequestParam String nickname, @RequestParam String email, @RequestParam String phone_number){

        String id = userDao.findID(nickname, email, phone_number);
        if(id==null) return null;
//        String t = userService.findID(json);
//        System.out.println(t);
        return ResponseEntity.ok().body(id);
    }

    //비밀번호찾기
    //ID 이름 전화번호 이메일
    @GetMapping("/findPW")//이름 전화번호 이메일
    public ResponseEntity<String> findPW(@RequestParam String nickname, @RequestParam String email, @RequestParam String phone_number, @RequestParam String id){
        String pw = userDao.findPW(id, nickname, email, phone_number);
        if(pw==null) return null;
        return ResponseEntity.ok().body(pw);
    }

    @PostMapping("/changePW")//이름 전화번호 이메일
    public ResponseEntity<HttpStatus> changePW(@RequestBody NewPwDto newPwDto){
        User user = userDao.findUser(newPwDto.id);
        user.setPw(newPwDto.pw);
        userDao.updateUser(user);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

}
