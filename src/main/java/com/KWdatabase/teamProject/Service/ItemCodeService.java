package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.dao.ItemCodeDao;
import lombok.NonNull;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class ItemCodeService {

    @Autowired
    private ItemCodeDao itemCodeDao;
    private static final String new_stock_url_cospi =
            "https://finance.naver.com/sise/sise_new_stock.naver?sosok=0";

    private static final String new_stock_url_cosdac =
            "https://finance.naver.com/sise/sise_new_stock.naver?sosok=1";

    public void process(){
        Connection conn1 = Jsoup.connect(new_stock_url_cospi);
        Connection conn2 = Jsoup.connect(new_stock_url_cosdac);

        Document document1 = null;
        Document document2 = null;
        try{
            //document 객체 가져옴.
            document1 =conn1.get();
            document2 =conn2.get();
        }catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("코스피");
        List<ItemCode> list1  =getDataList(document1);
        for(ItemCode l1 : list1){
            itemCodeDao.insertItemCode(l1);
        }


        System.out.println("\n코스닥");
        List<ItemCode> list2  =getDataList(document2);
        for(ItemCode l2 : list2){
            itemCodeDao.insertItemCode(l2);
        }
    }

    @NonNull
    private List<ItemCode> getDataList(Document document){
        List<ItemCode> list = new ArrayList<>();
        Elements selects = document.select(".type_2 tbody tr");

        for(Element select : selects){
            Elements tdContents = select.select("td");
            if(tdContents.size()<2) continue;
            //System.out.println(select.html() + tdContents.size());
            String date= tdContents.get(1).text();
            Elements aContents = tdContents.get(2).select("a");
            Element aTag = aContents.get(0);
            String itemName = aTag.text();
            String itemURL = aTag.getElementsByAttribute("href").attr("href");


            StringTokenizer tokenizer = new StringTokenizer(itemURL, "/item/main.naver?code=");
            String itemCode = tokenizer.nextToken();
            System.out.println(itemCode);
            ItemCode itemCode1 = ItemCode.builder()
                    .itemCode(itemCode)
                    .itemName(itemName)
                    .likeNumber(0)
                    .build();
            list.add(itemCode1);
        }
        return list;
    }

}
