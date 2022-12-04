package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Chat;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ChatDao {
    public List<Chat> listChat();

    public List<Chat> getChat(@Param("chatDto")String itemCode);
    public void insertChat(@Param("chatDto") Chat chat);
}
