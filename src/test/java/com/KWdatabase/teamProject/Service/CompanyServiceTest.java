package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.Company;
import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.dao.CompanyDao;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class CompanyServiceTest {
    @Autowired
    CompanyService companyService;

    @Test
    public void insertDataTest() throws Exception{
        companyService.process("148930");
    }
}