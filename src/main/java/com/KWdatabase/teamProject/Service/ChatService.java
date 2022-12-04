package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.Chat;
import com.KWdatabase.teamProject.dao.ChatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {
    @Autowired
    private ChatDao chatDao;

    public List<Chat> getChatList(String itemCode){
        return chatDao.getChat(itemCode);
    }

    public void insertChat(Chat chat){
        chatDao.insertChat(chat);
    }
}
