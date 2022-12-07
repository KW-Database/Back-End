package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.User;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor
class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void signUp_Test() throws Exception {

        String adminAuth = "USER";
        int age = 24;
        String id = "gwon";
        String email="pg15320@gmail.com";
        String phoneNumber="01012323534";
        String pw="1234";
        char sex = 'M';
        String username = "ParkJiwon";



        User user = User.builder()
                .adminAuth(adminAuth)
                .age(age)
                .id(id)
                .email(email)
                .phoneNumber(phoneNumber)
                .pw(new BCryptPasswordEncoder().encode(pw))
                .sex(sex)
                .username(username)
                .build();

        userDao.signUp(user);

        User user1 =userDao.findUser(user.getId());

        assertThat(user1.getId()).isEqualTo(user.getId());

    }
}