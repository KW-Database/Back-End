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

        String adminAuth = "1";
        int age = 24;
        String id = "kiki";
        String email="pg15320@gmail.com";
        String phoneNumber="01012341234";
        String pw="1234";
        char sex = 'M';
        String username = "ParkJiwon";

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