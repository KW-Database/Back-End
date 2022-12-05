package com.KWdatabase.teamProject.dao;

import com.KWdatabase.teamProject.Model.Company;
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
class CompanyDaoTest {

    @Autowired
    private CompanyDao companyDao;
//    @Test
//    public void getCompanyDataTest() {
//        String item_code = "148930";
//        String company_name = "에이치와이티씨";
//        int item_Number =10095213;
//        String company_summary = "동사는 배터리 제조 공정 중 극판공정 및 조립공정에 사용되는 2차전지 장비의 초정밀 제품을 생산하고 있음.주요 제품으로 극판 공정 장비에 사용되는 PX-SHAFT 및 KNIFE UNIT과 KNIFE-SPACER, 조립 공정 장비에 사용되는 권심, CUTTER와 초음파 혼, ANVIL, 절연판 금형 등을 생산.MES 시스템 도입을 통한 전 생산공정에 대한 공정관리를 수행하고 있으며, 공정별 품질검사를 통하여 높은 품질을 유지.";
//        String date = "2022-08-09";
//        LocalDate public_date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
//        Company company = Company.builder()
//                .itemCode(item_code)
//                .companyName(company_name)
//                .itemNumber(item_Number)
//                .companySummary(company_summary)
//                .publicDate(public_date)
//                .build();
//
//        Company company1 = companyDao.getCompany(company);
//        assertThat(company1.getItemCode()).isEqualTo(item_code);
//    }

//    @Test
//    public void updateCompanyDataTest() {
//        String item_code = "000060";
//        String company_name = "메리츠화재";
//        int item_Number =11378300;
//        String company_summary = "1922년 국내 최초로 설립된 손해보험사로, 메리츠금융그룹 기업집단에 속해있으며 손해보험업 및 이에 관련된 부대사업을 영위하고 있음.";
//        String date = "1234-01-23";
//        LocalDate public_date = LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
//        Company company = Company.builder()
//                .itemCode(item_code)
//                .companyName(company_name)
//                .itemNumber(item_Number)
//                .companySummary(company_summary)
//                .publicDate(public_date)
//                .build();
//
//        companyDao.updateCompany(company);
//        Company company1 = companyDao.getCompany(company);
//        assertThat(company1.getPublicDate()).isEqualTo(public_date);
//    }
}