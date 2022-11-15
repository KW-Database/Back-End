package com.KWdatabase.teamProject.service;

import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.dao.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDao userDao;

    public User findUser(String id){
        return userDao.findUser(id);
    }
}