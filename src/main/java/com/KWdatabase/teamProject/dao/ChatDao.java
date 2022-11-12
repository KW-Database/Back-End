package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Chat;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ChatDao {
    public List<Chat> listChat();
}
