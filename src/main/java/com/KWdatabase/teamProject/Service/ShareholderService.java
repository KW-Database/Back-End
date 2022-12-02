package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.Shareholder;
import com.KWdatabase.teamProject.dao.ShareholderDao;
import lombok.RequiredArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.jsoup.nodes.Document;
import java.util.List;
import java.util.StringTokenizer;

@Service
@RequiredArgsConstructor
public class ShareholderService {

    @Autowired
    private ShareholderDao shareholderDao;

    private static final String stockInfoUrl_page1=
            "https://navercomp.wisereport.co.kr/v2/company/c1010001.aspx?cmp_cd=";

    public List<Shareholder> getShareholderList(String itemCode){
        return shareholderDao.getShareholderList(itemCode);
    }

    public void insertShareholder(String itemCode) throws Exception {
        String URL = stockInfoUrl_page1+itemCode;
        Document document = Jsoup.connect(URL).get();

        Elements elements = document.select("#cTB13 tbody tr");
        int i=1;
        for(Element element : elements){
            Elements shareHolder = element.select("td");
            String majorHolders = shareHolder.get(0).getElementsByAttribute("title").attr("title");
            System.out.println(majorHolders);
            if(majorHolders.isEmpty())break;
            String number = shareHolder.get(1).text();
            StringTokenizer tokenizer = new StringTokenizer(number, ",");
            number="";
            while(tokenizer.hasMoreTokens()){
                number = number + tokenizer.nextToken();
            }
            String portion = shareHolder.get(2).text();
            if(portion.isEmpty())portion="0";
            int itemNumber = Integer.parseInt(number);
            double shares = Double.parseDouble(portion);
            Shareholder shareholder = Shareholder.builder()
                    .itemCode(itemCode)
                    .majorHolders(majorHolders)
                    .itemNumber(itemNumber)
                    .shares(shares)
                    .build();
            shareholderDao.insertShareholderData(shareholder);
            i++;
        }

    }

    public void updateShareholder(Shareholder shareholder){
        Shareholder changes= Shareholder.builder()
                .itemNumber(677777)
                .majorHolders(shareholder.getMajorHolders())
                .itemCode(shareholder.getItemCode())
                .shares(0.33)
                .build();

        shareholderDao.updateShareholderData(shareholder, changes);
    }
}
