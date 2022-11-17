package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/update")
    public ResponseEntity<HttpStatus> updateUser(@RequestBody User user){
        userService.updateUser(user);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<HttpStatus> deleteUser(@RequestParam(name="id")String id){
        userService.deleteUser(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
