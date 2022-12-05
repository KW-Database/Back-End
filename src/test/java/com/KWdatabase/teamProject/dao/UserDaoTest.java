package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void signUp_Test() throws Exception {

        String adminAuth = "0";
        int age = 34;
        String id = "jiwon";
        String email="pg@gmail.com";
        String phoneNumber="01032341234";
        String pw="3234";
        char sex = 'W';
        String username = "Jiwon";

        User user = User.builder()
                .adminAuth(adminAuth)
                .age(age)
                .id(id)
                .email(email)
                .phoneNumber(phoneNumber)
                .pw(pw)
                .sex(sex)
                .username(username)
                .build();

        userDao.signUp(user);

        User user1 =userDao.findUser(user.getId());

        assertThat(user1.getId()).isEqualTo(user.getId());

    }
}