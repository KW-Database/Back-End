package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemCode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ItemCodeServiceTest {
    @Autowired
    ItemCodeService itemCodeService;

    @Test
    public void getDataTest() throws Exception{
        itemCodeService.process();
    }
}