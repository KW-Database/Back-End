package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import com.KWdatabase.teamProject.dao.ItemDayConditionDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Service
public class ItemDayConditionService {
    @Autowired
    public ItemDayConditionDao itemDayConditionDao;

    private final String timeCondition =
            "https://finance.naver.com/item/sise_time.naver?code=";// 예시 : https://finance.naver.com/item/sise_time.naver?code=001800&thistime=20221116161103
    private final String dayCondition =
            "https://finance.naver.com/item/sise_day.naver?code=";// 예시 : https://finance.naver.com/item/sise_day.naver?code=001800

    public void pageCrawling(String itemCode) throws IOException {
        int pageNum =1;
        while(true){
            String num = Integer.toString(pageNum);
            String URL = dayCondition + itemCode+"&page="+num;
            Document document = Jsoup.connect(URL).get();
            Elements pages = document.select(".Nnavi td");
            if(pageNum==1){
                if(pages.size()<12){
                    for(; pageNum<= pages.size()-2 ; pageNum++){
                        num = Integer.toString(pageNum);
                        String url = dayCondition + itemCode+"&page="+num;
                        dayCrawling(url, itemCode);
                    }
                    break;
                }
                for(; pageNum<= 10 ; pageNum++){
                    num = Integer.toString(pageNum);
                    String url = dayCondition + itemCode+"&page="+num;
                    dayCrawling(url, itemCode);
                }
            }
            else if(pages.size()<12) {
                int pNum = pageNum;
                for(; pageNum<= pNum+pages.size()-2 ; pageNum++){
                    num = Integer.toString(pageNum);
                    String url = dayCondition + itemCode+"&page="+num;
                    dayCrawling(url, itemCode);
                }
                break;
            }
            else{
                int pNum = pageNum;
                for(; pageNum<= pNum+ 9 ; pageNum++){
                    num = Integer.toString(pageNum);
                    String url = dayCondition + itemCode+"&page="+num;
                    dayCrawling(url, itemCode);
                }
            }
        }
    }

    public void dayCrawling(String URL, String itemCode) throws IOException {
        Document document=Jsoup.connect(URL).get();

        List<ItemDayCondition> itemDayConditionList = getTimeData(document, itemCode);

        for(ItemDayCondition dayCondition : itemDayConditionList){
            itemDayConditionDao.insertItemDayCondition(dayCondition);
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

    private List<ItemDayCondition> getTimeData(Document document, String itemcode){
        List<ItemDayCondition> itemDayConditionList = new ArrayList<>();
        Elements rows = document.select(".type2 tbody tr");

        for(Element row : rows){
            Elements tds = row.select("td span");
            if(tds.size()<2) continue;
            String date = tds.get(0).text();
            String end_price = tds.get(1).text();
            String start_price = tds.get(3).text();
            String highest_price = tds.get(4).text();
            String lowest_price = tds.get(5).text();
            String volume = tds.get(6).text();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd");
            LocalDate localDate = LocalDate.parse(date, formatter);

            end_price = convertPrice(end_price);
            start_price = convertPrice(start_price);
            highest_price = convertPrice(highest_price);
            lowest_price = convertPrice(lowest_price);
            volume = convertPrice(volume);

            ItemDayCondition itemDayCondition = ItemDayCondition.builder()
                    .itemCode(itemcode)
                    .present(localDate)
                    .endPrice(Integer.parseInt(end_price))
                    .startPrice(Integer.parseInt(start_price))
                    .highestPrice(Integer.parseInt(highest_price))
                    .lowestPrice(Integer.parseInt(lowest_price))
                    .volume(Integer.parseInt(volume))
                    .build();

            itemDayConditionList.add(itemDayCondition);
        }
        return itemDayConditionList;
    }
}
