package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.Model.Shareholder;
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
class ShareholderServiceTest {

    @Autowired
    private ShareholderService shareholderService;

    @Autowired
    private ItemCodeDao itemCodeDao;

    @Test
    public void insertData() throws Exception {
        List<ItemCode> itemCodeList = itemCodeDao.getItemCodeList();
        for(ItemCode itemCode : itemCodeList){
            shareholderService.insertShareholder(itemCode.getItemCode());
        }
    }

    @Test
    public void updateData() throws Exception {

        Shareholder shareholder= Shareholder.builder()
                .shares(0.32)
                .itemNumber(677387)
                .itemCode("003535")
                .majorHolders("자사주")
                .build();

        shareholderService.updateShareholder(shareholder);

    }
}