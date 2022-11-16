package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.Model.ItemCondition;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemConditionDaoTest {

    @Autowired
    private ItemConditionDao itemConditionDao;

    @Test
    public void getDataTest(){
        String item_code = "000060";
        String string = "2022-11-16 12:26:50";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.parse(string, formatter);
        float start_price = (float) 5.3;
        float end_price = (float) 7.3;
        float lowest_price = (float) 1.2;
        float highest_price = (float) 10.4;
        int vol = 12345678;

        ItemCondition itemCondition = ItemCondition.builder()
                .itemCode(item_code)
                .present(time)
                .startPrice(start_price)
                .endPrice(end_price)
                .highestPrice(highest_price)
                .lowestPrice(lowest_price)
                .volume(vol)
                .build();

        ItemCondition itemCondition1 = itemConditionDao.getItemCondition(itemCondition);
        assertThat(itemCondition1.getItemCode()).isEqualTo(item_code);
    }

    @Test
    public void insertDataTest(){
        String item_code = "000060";
        LocalDateTime pre = LocalDateTime.now();
        LocalDateTime time = pre.withNano(0000);
        System.out.println(pre);
        float start_price = (float) 5.3;
        float end_price = (float) 7.3;
        float lowest_price = (float) 1.2;
        float highest_price = (float) 10.4;
        int vol = 12345678;

        ItemCondition itemCondition = ItemCondition.builder()
                .itemCode(item_code)
                .present(time)
                .startPrice(start_price)
                .endPrice(end_price)
                .highestPrice(highest_price)
                .lowestPrice(lowest_price)
                .volume(vol)
                .build();

        itemConditionDao.insertItemCondition(itemCondition);

        ItemCondition itemCondition1 = itemConditionDao.getItemCondition(itemCondition);
        assertThat(itemCondition1.getItemCode()).isEqualTo(item_code);
    }

}