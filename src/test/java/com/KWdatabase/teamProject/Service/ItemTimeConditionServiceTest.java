package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.Model.ItemDayCondition;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;

import com.KWdatabase.teamProject.dao.ItemCodeDao;
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
        String itemcode = "KOSPI";
        List<ItemTimeCondition> itemTimeConditionList=itemTimeConditionService.getItemTimeCondition(itemcode);
        for(ItemTimeCondition i : itemTimeConditionList){
            System.out.println(i.getClosingTime());
        }
    }

    @Autowired
     private ItemCodeDao itemCodeDao;
    @Test
    public void insertDataTest() throws Exception{
        itemTimeConditionService.process();
    }


}