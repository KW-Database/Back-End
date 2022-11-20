package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import com.KWdatabase.teamProject.dao.ItemTimeConditionDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class ItemTimeConditionService {

    private final String timeCondition =
            "https://finance.naver.com/item/sise_time.naver?code=";// 예시 : https://finance.naver.com/item/sise_time.naver?code=001800&thistime=20221116161103

    @Autowired
    ItemTimeConditionDao itemTimeConditionDao;

    public List<ItemTimeCondition> getItemTimeCondition(String itemCode){
        return itemTimeConditionDao.getItemTimeCondition(itemCode);
    }

    public void deleteAllData(){
        itemTimeConditionDao.deleteAllTimeSise();
    }

    public void pageCrawling(String itemCode) throws IOException {
        int pageNum =1;
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        System.out.println(now);
        while(true){
            String num = Integer.toString(pageNum);
            String URL = timeCondition + itemCode+"&page="+num+"&thistime="+now;
            Document document = Jsoup.connect(URL).get();
            Elements pages = document.select(".Nnavi td");
            System.out.println(pages.size());
            if(pageNum==1){
                if(pages.size()<12){
                    for(; pageNum<= pages.size()-2 ; pageNum++){
                        num = Integer.toString(pageNum);
                        URL = timeCondition + itemCode+"&page="+num+"&thistime="+now;
                        timeCrawling(URL, itemCode);
                    }
                    break;
                }
                for(; pageNum<= 10 ; pageNum++){
                    num = Integer.toString(pageNum);
                    URL = timeCondition + itemCode+"&page="+num+"&thistime="+now;
                    timeCrawling(URL, itemCode);
                }
            }
            else if(pages.size()<12) {
                int pNum = pageNum;
                for(; pageNum<= pNum+pages.size()-2 ; pageNum++){
                    num = Integer.toString(pageNum);
                    URL = timeCondition + itemCode+"&page="+num+"&thistime="+now;
                    timeCrawling(URL, itemCode);
                }
                break;
            }
            else{
                int pNum = pageNum;
                for(; pageNum<= pNum+ 9 ; pageNum++){
                    num = Integer.toString(pageNum);
                    URL = timeCondition + itemCode+"&page="+num+"&thistime="+now;
                    timeCrawling(URL, itemCode);
                }
            }
        }
    }

    public void timeCrawling(String URL, String itemCode) throws IOException {
        Document document=Jsoup.connect(URL).get();

        List<ItemTimeCondition> itemTimeConditionList = getTimeData(document, itemCode);

        for(ItemTimeCondition timeCondition : itemTimeConditionList){
            System.out.println(timeCondition.getClosingTime());
            itemTimeConditionDao.insertItemTimeCondition(timeCondition);
        }
    }

    private String convertPrice(String price){
        StringTokenizer tokenizer = new StringTokenizer(price, ",");
        price="";
        while(tokenizer.hasMoreTokens()){
            price = price + tokenizer.nextToken();
        }

        return price;
    }

    private List<ItemTimeCondition> getTimeData(Document document, String itemcode){
        List<ItemTimeCondition> itemTimeConditionList = new ArrayList<>();
        Elements rows = document.select(".type2 tbody tr");

        for(Element row : rows){
            Elements tds = row.select("td span");
            if(tds.size()<2) continue;
            String time = tds.get(0).text();
            String execution_price = tds.get(1).text();
            String volume = tds.get(5).text();

            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_TIME);

            execution_price = convertPrice(execution_price);
            volume = convertPrice(volume);

            ItemTimeCondition itemTimeCondition = ItemTimeCondition.builder()
                    .itemCode(itemcode)
                    .closingTime(localTime)
                    .executionPrice(Integer.parseInt(execution_price))
                    .volume(Integer.parseInt(volume))
                    .build();

            itemTimeConditionList.add(itemTimeCondition);
        }
        return itemTimeConditionList;
    }
    
}
