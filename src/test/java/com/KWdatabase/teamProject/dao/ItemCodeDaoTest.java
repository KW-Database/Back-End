package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.ItemCode;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemCodeDaoTest {
    @Autowired
    private ItemCodeDao itemCodeDao;

    @Test
    public void readItemCode_Test() throws Exception{
        String item_code = "1234";
        String item_name = "samsung";
        int like_number=1;
        ItemCode itemCode = ItemCode.builder()
                .itemCode(item_code)
                .itemName(item_name)
                .likeNumber(like_number)
                .build();

        ItemCode itemCode1 = itemCodeDao.getItemCode(itemCode);
        assertThat(itemCode1.getItemCode()).isEqualTo(item_code);
    }

    @Test
    public void InsertItemCode_Test() throws Exception{
        String item_code = "0209";
        String item_name = "Jiwon";
        int like_number=50;
        ItemCode itemCode = ItemCode.builder()
                .itemCode(item_code)
                .itemName(item_name)
                .likeNumber(like_number)
                .build();

        itemCodeDao.insertItemCode(itemCode);

        ItemCode itemCode1 = itemCodeDao.getItemCode(itemCode);
        assertThat(itemCode1.getItemCode()).isEqualTo(item_code);
    }

}