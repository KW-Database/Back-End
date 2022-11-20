package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemTimeConditionServiceTest {
    @Autowired
    ItemTimeConditionService itemTimeConditionService;

    @Test
    public void getDataTest(){
        String itemcode = "001800";
        List<ItemTimeCondition> itemTimeConditionList=itemTimeConditionService.getItemTimeCondition(itemcode);
        for(ItemTimeCondition i : itemTimeConditionList){
            System.out.println(i.getClosingTime());
        }
    }

    @Test
    public void insertDataTest() throws Exception{
        String itemcode = "001800";
        itemTimeConditionService.pageCrawling(itemcode);
    }
}