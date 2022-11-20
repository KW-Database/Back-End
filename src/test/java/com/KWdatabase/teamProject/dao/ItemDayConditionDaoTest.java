package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemDayConditionDaoTest {

    @Autowired
    private ItemDayConditionDao itemConditionDao;

    @Test
    public void getDataTest(){
        String item_code = "000060";
        String string = "2022-11-16 12:26:50";
        LocalDate time = LocalDate.parse(string, DateTimeFormatter.ISO_DATE);
        float start_price = (float) 5.3;
        float end_price = (float) 7.3;
        float lowest_price = (float) 1.2;
        float highest_price = (float) 10.4;
        int vol = 12345678;

        ItemDayCondition itemDayCondition = ItemDayCondition.builder()
                .itemCode(item_code)
                .present(time)
                .startPrice(start_price)
                .endPrice(end_price)
                .highestPrice(highest_price)
                .lowestPrice(lowest_price)
                .volume(vol)
                .build();

        assertThat(itemDayCondition.getItemCode()).isEqualTo(item_code);
    }

    @Test
    public void insertDataTest(){
        String item_code = "000060";
        LocalDate pre = LocalDate.now();
        System.out.println(pre);
        float start_price = (float) 5.3;
        float end_price = (float) 7.3;
        float lowest_price = (float) 1.2;
        float highest_price = (float) 10.4;
        int vol = 12345678;

        ItemDayCondition itemDayCondition = ItemDayCondition.builder()
                .itemCode(item_code)
                .present(pre)
                .startPrice(start_price)
                .endPrice(end_price)
                .highestPrice(highest_price)
                .lowestPrice(lowest_price)
                .volume(vol)
                .build();

        itemConditionDao.insertItemDayCondition(itemDayCondition);

        List<ItemDayCondition> itemDayCondition1 = itemConditionDao.getItemDayCondition(item_code);
        assertThat(itemDayCondition1.get(0).getItemCode()).isEqualTo(item_code);
    }

}