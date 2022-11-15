package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/profile")
public class ProfileController {
    private final UserService userService;

    @GetMapping("/info")
    public ResponseEntity<User> viewProfile(@RequestParam(name ="id")String id){
        User user = userService.findUser(id);
        return ResponseEntity.ok().body(user);
    }
}
