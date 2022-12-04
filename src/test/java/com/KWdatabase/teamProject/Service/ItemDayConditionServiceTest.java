package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemDayConditionServiceTest {
    @Autowired
    ItemDayConditionService itemDayConditionService;

    @Test
    public void getDataTest(){
        String itemcode = "KOSPI";
        List<ItemDayCondition> itemDayConditionList=itemDayConditionService.getItemDayCondition(itemcode);
        for(ItemDayCondition i : itemDayConditionList){
            System.out.println(i.getPresent());
        }
    }

    @Test
    public void insertDataTest() throws Exception{
        itemDayConditionService.process();
    }
}