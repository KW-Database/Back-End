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

    private static final String stock_url_cospi =
            "https://finance.naver.com/sise/sise_market_sum.naver?sosok=0";
    private static final String stock_url_cosdaq =
            "https://finance.naver.com/sise/sise_market_sum.naver?sosok=1";

    private static final String new_stock_url_cospi =
            "https://finance.naver.com/sise/sise_new_stock.naver?sosok=0";

    private static final String new_stock_url_cosdac =
            "https://finance.naver.com/sise/sise_new_stock.naver?sosok=1";



    public void process() throws IOException {
        List<ItemCode> list1  =getDataList(new_stock_url_cospi);
        for(ItemCode l1 : list1){
            itemCodeDao.insertItemCode(l1);
        }


        List<ItemCode> list2  =getDataList(new_stock_url_cospi);
        for(ItemCode l2 : list2){
            itemCodeDao.insertItemCode(l2);
        }

        pageCrawling(stock_url_cospi);
        pageCrawling(stock_url_cosdaq);
    }

    @NonNull
    private List<ItemCode> getDataList(String URL) throws IOException {
        Document document = Jsoup.connect(URL).get();
        List<ItemCode> list = new ArrayList<>();
        Elements selects = document.select(".type_2 tbody tr");
        for(Element select : selects){
            Elements tdContents = select.select("td");
            if(tdContents.size()<2) continue;
            //System.out.println(select.html() + tdContents.size());
            String date= tdContents.get(1).text();
            Elements aContents = tdContents.get(2).select("a");
            String itemName;
            String itemURL;
            if(aContents.size()==0){
                aContents = tdContents.get(1).select("a");
                Element aTag = aContents.get(0);
                itemName = aTag.text();
                itemURL = aTag.getElementsByAttribute("href").attr("href");
            }
            else{
                Element aTag = aContents.get(0);
                itemName = aTag.text();
                itemURL = aTag.getElementsByAttribute("href").attr("href");
            }


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

    public void pageCrawling(String url) throws IOException {
        int pageNum = 1;
        int s=0;
        while (true) {
            s=0;
            String num = Integer.toString(pageNum);
            String URL = url + "&page=" + num;
            Document document = Jsoup.connect(URL).get();
            Elements pages = document.select(".Nnavi td");
            if (pageNum == 1) {
                if (pages.size() < 12) {
                    for (; pageNum <= pages.size() - 2; pageNum++) {
                        num = Integer.toString(pageNum);
                        URL = url + "&page=" + num;
                        List<ItemCode> list = getDataList(URL);
                        for (ItemCode l : list) {
                            itemCodeDao.insertItemCode(l);
                        }
                        if(list.size()<50){s=1;break;}
                    }
                    if(s==1)break;
                    break;
                }
                for (; pageNum <= 10; pageNum++) {
                    num = Integer.toString(pageNum);
                    URL = url + "&page=" + num;
                    List<ItemCode> list = getDataList(URL);
                    for (ItemCode l : list) {
                        itemCodeDao.insertItemCode(l);
                    }
                    if(list.size()<50){s=1;break;}
                }
                if(s==1)break;
            } else if (pages.size() < 12) {
                int pNum = pageNum;
                for (; pageNum <= pNum + pages.size() - 2; pageNum++) {
                    num = Integer.toString(pageNum);
                    URL = url + "&page=" + num;
                    List<ItemCode> list = getDataList(URL);
                    for (ItemCode l : list) {
                        itemCodeDao.insertItemCode(l);
                    }
                    if(list.size()<50){s=1;break;}
                }
                if(s==1)break;
                break;
            } else {
                int pNum = pageNum;
                for (; pageNum <= pNum + 9; pageNum++) {
                    num = Integer.toString(pageNum);
                    URL = url + "&page=" + num;
                    List<ItemCode> list = getDataList(URL);
                    for (ItemCode l : list) {
                        itemCodeDao.insertItemCode(l);
                    }
                    if(list.size()<50){s=1;break;}
                }
                if(s==1)break;
            }
        }
    }
}
