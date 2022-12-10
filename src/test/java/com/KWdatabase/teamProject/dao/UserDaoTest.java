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
    public void findUserTest() throws Exception{
        User user = userDao.findUser("kiki");
        System.out.println(user.getUsername());
        System.out.println("Aaaaaa");
    }
    @Test
    public void signUp_Test() throws Exception {

        String adminAuth = "0";
        int age = 24;
        String id = "gwondd";
        String email="pg2022@gmail.com";
        String phoneNumber="01010023534";
        String pw="1234";
        char sex = 'M';
        String nickname = "Parkwon";



        User user = User.builder()
                .adminAuth(adminAuth)
                .age(age)
                .id(id)
                .email(email)
                .phoneNumber(phoneNumber)
                .pw(new BCryptPasswordEncoder().encode(pw))
                .sex(sex)
                .nickname(nickname)
                .build();

        userDao.signUp(user);

        User user1 =userDao.findUser(user.getId());

        assertThat(user1.getId()).isEqualTo(user.getId());

    }
}