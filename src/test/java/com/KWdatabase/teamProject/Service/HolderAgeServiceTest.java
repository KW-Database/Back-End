package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.HolderAge;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class HolderAgeServiceTest {
    @Autowired
    private HolderAgeService holderAgeService;

    @Test
    public void insertDataTest(){
        HolderAge holderAge = HolderAge.builder()
                .itemNumber(0)
                .itemCode("000060")
                .ages(0)
                .build();
        holderAgeService.insert(holderAge);
    }

    @Test
    public void updateDataTest(){
        HolderAge holderAge = HolderAge.builder()
                .itemNumber(12342134)
                .itemCode("000060")
                .ages(73)
                .build();
        holderAgeService.updateData(holderAge);
    }
}