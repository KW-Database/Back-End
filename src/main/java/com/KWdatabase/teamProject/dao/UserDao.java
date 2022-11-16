package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    public User findUser(String id);
    public void signUp(@Param("user") User user);
    public void updateUser(@Param("user") User user);
}
