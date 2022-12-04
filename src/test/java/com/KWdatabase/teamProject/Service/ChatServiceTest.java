package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.Chat;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ChatServiceTest {
    @Autowired
    private ChatService chatService;

    @Test
    public void getChat(){
        String itemCode = "000060";
        List<Chat> chatList = chatService.getChatList(itemCode);
        for(Chat chat : chatList){
            System.out.println(chat.getId());
        }
    }

    @Test
    public void insertChat(){
        Chat chat = Chat.builder()
                .id("kiki")
                .contents("db tlqkf")
                .itemCode("000060")
                .postTime(LocalDateTime.now())
                .build();

        chatService.insertChat(chat);
    }
}