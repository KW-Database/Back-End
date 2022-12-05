package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.Company;
import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.dao.CompanyDao;
import com.KWdatabase.teamProject.dao.ItemCodeDao;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
public class CompanyService {

    @Autowired
    private CompanyDao companyDao;

    @Autowired
    private ItemCodeDao itemCodeDao;

    private static final String stockUrl =
            "https://finance.naver.com/item/main.naver?code=";

    private static final String stockInfoUrl_page1=
            "https://navercomp.wisereport.co.kr/v2/company/c1010001.aspx?cmp_cd=";
    private static final String stockInfoUrl_page2 =
            "https://navercomp.wisereport.co.kr/v2/company/c1020001.aspx?cmp_cd=";

    public void insertData(Company company){
        System.out.println(company.getItemCode());
        companyDao.insertCompany(company);
    }

    public void insertDataList() throws IOException {
        List<ItemCode> itemCodeList= itemCodeDao.getItemCodeList();
        for(ItemCode itemCode: itemCodeList){
            process(itemCode.getItemCode());
        }
    }


    public void process(String itemCode) throws IOException {
        String URL = stockUrl+itemCode;
        Connection conn = Jsoup.connect(URL);

        Document document = null;
        try{
            //document 객체 가져옴.
            document =conn.get();
        }catch (IOException e){
            e.printStackTrace();
        }

        Company c1 =getDataList(document);
        companyDao.insertCompany(c1);
    }

    @NonNull
    private Company getDataList(Document document) throws IOException {
        Elements name = document.select(".wrap_company h2 a");
        String companyName = name.get(0).text();

        Elements codes = document.select(".description .code");
        String itemCode = codes.get(0).text();

        System.out.println(itemCode);
        Elements info = document.select(".first table tbody tr ");
        Elements Num;
        if(info.size()<3){
            Num= info.get(1).select("em");
        }
        else{
            Num= info.get(2).select("em");
        }
        String itemNumText = Num.get(0).text();
        String itemNum ="";
        StringTokenizer tokenizer = new StringTokenizer(itemNumText, ",");
        while(tokenizer.hasMoreTokens()){
            itemNum = itemNum + tokenizer.nextToken();
        }

        Document d1 = Jsoup.connect(stockInfoUrl_page1+itemCode).get();

        Elements summary = d1.select(".cmp_comment ul li");
        String companySummary="";
        for(Element contents : summary){
            companySummary = companySummary + contents.text();
        }

        Document d2 = Jsoup.connect(stockInfoUrl_page2+itemCode).get();

        Elements dates = d2.select("#cTB201 tbody tr td");

        if(dates.isEmpty()){
            String publicDate = "0001-01-01";
            Company company = Company.builder()
                    .itemCode(itemCode)
                    .companyName(companyName)
                    .itemNumber(Long.parseLong(itemNum))
                    .publicDate(LocalDate.parse(publicDate,DateTimeFormatter.ISO_DATE))
                    .companySummary(companySummary)
                    .build();

            return company;
        }

        String publicDateText = dates.get(3).text();
        String publicDate="";

        StringTokenizer tokenizer1 = new StringTokenizer(publicDateText, "설립일: ");
        while(tokenizer1.hasMoreTokens()){
            publicDate = tokenizer1.nextToken();
        }
        StringTokenizer tokenizer2 = new StringTokenizer(publicDate, ")");
        while(tokenizer2.hasMoreTokens()){
            publicDate = tokenizer2.nextToken();
        }

        StringTokenizer tokenizer3 = new StringTokenizer(publicDate, "/");
        publicDate="";
        while(tokenizer3.hasMoreTokens()){
            publicDate= publicDate+tokenizer3.nextToken();
            if(tokenizer3.hasMoreTokens())publicDate=publicDate+"-";

        }


        Company company = Company.builder()
                .itemCode(itemCode)
                .companyName(companyName)
                .itemNumber(Long.parseLong(itemNum))
                .publicDate(LocalDate.parse(publicDate,DateTimeFormatter.ISO_DATE))
                .companySummary(companySummary)
                .build();

        return company;
    }
}
