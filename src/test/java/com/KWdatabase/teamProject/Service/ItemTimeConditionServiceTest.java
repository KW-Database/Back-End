package com.KWdatabase.teamProject.Service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemTimeConditionServiceTest {
    @Autowired
    ItemTimeConditionService itemTimeConditionService;

    @Test
    public void insertDataTest() throws Exception{
        String itemcode = "102370";
        itemTimeConditionService.pageCrawling(itemcode);
    }
}