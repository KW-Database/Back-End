package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao {

    public List<User> getUserList();
    public User findUser(String id);
    public void signUp(@Param("user") User user);
    public void updateUser(@Param("user") User user);
    public void deleteUser(String id);
    public User findID(String name, String email, String phone);
    public User findPW(String ID, String name, String email, String phone);
}
