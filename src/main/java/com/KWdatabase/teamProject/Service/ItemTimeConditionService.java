package com.KWdatabase.teamProject.Service;

import com.KWdatabase.teamProject.Model.ItemCode;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import com.KWdatabase.teamProject.dao.ItemCodeDao;
import com.KWdatabase.teamProject.dao.ItemTimeConditionDao;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
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

    private final String KOSPITimeCondition =
            "https://finance.naver.com/sise/sise_index_time.naver?code=KOSPI";
    private final String KOSPI200TimeCondition =
            "https://finance.naver.com/sise/sise_index_time.naver?code=KOSPI200";
    private final String KOSDAQTimeCondition =
            "https://finance.naver.com/sise/sise_index_time.naver?code=KOSDAQ";


    @Autowired
    ItemTimeConditionDao itemTimeConditionDao;

    @Autowired
    ItemCodeDao itemCodeDao;

    public List<ItemTimeCondition> getItemTimeCondition(String itemCode){
        return itemTimeConditionDao.getItemTimeCondition(itemCode);
    }

    public void deleteAllData(){
        itemTimeConditionDao.deleteAllTimeSise();
    }



    //@Scheduled(cron = "0 * * * * *")
    public void process() throws IOException {
        List<ItemCode> itemCodeList = itemCodeDao.getItemCodeList();
        for(ItemCode itemCode : itemCodeList){
            String URL;
            if(itemCode.getItemCode().equals("KOSPI"))
                URL=KOSPITimeCondition;
            else if(itemCode.getItemCode().equals("KOSPI200"))
                URL = KOSPI200TimeCondition;
            else if(itemCode.getItemCode().equals("KOSDAQ"))
                URL = KOSDAQTimeCondition;
            else URL=timeCondition;
            pageCrawling(itemCode.getItemCode(),URL);
        }
    }
    public void pageCrawling(String itemCode, String url) throws IOException {
        int pageNum =1;
        //String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String now = "20221202091018";
        while(true){
            String num = Integer.toString(pageNum);
            String URL = url + itemCode+"&page="+num+"&thistime="+now;
            Document document = Jsoup.connect(URL).get();
            Elements pages = document.select(".Nnavi td");
            if(pageNum==1){
                if(pages.size()<12){
                    for(; pageNum<= pages.size()-2 ; pageNum++){
                        num = Integer.toString(pageNum);
                        URL = url + itemCode+"&page="+num+"&thistime="+now;
                        if(timeCrawling(URL, itemCode)==false)return;
                    }
                    break;
                }
                for(; pageNum<= 10 ; pageNum++){
                    num = Integer.toString(pageNum);
                    URL = url + itemCode+"&page="+num+"&thistime="+now;
                    if(timeCrawling(URL, itemCode)==false)return;
                }
            }
            else if(pages.size()<12) {
                int pNum = pageNum;
                for(; pageNum<= pNum+pages.size()-2 ; pageNum++){
                    num = Integer.toString(pageNum);
                    URL = url + itemCode+"&page="+num+"&thistime="+now;
                    if(timeCrawling(URL, itemCode)==false)return;
                }
                break;
            }
            else{
                int pNum = pageNum;
                for(; pageNum<= pNum+ 9 ; pageNum++){
                    num = Integer.toString(pageNum);
                    URL = timeCondition + itemCode+"&page="+num+"&thistime="+now;
                    if(timeCrawling(URL, itemCode)==false)return;
                }
            }
        }
    }

    public boolean timeCrawling(String URL, String itemCode) throws IOException {
        Document document=Jsoup.connect(URL).get();

        ItemTimeCondition checkNew = itemTimeConditionDao.getNewCondition(itemCode);
        if(checkNew==null){
            List<ItemTimeCondition> itemTimeConditionList;
            if(itemCode.equals("KOSPI")||itemCode.equals("KPI200")||itemCode.equals("KOSDAQ"))
                itemTimeConditionList= getTimeData_k(document, itemCode);
            else itemTimeConditionList= getTimeData(document, itemCode);

            for(ItemTimeCondition timeCondition : itemTimeConditionList){
                //System.out.println(timeCondition.getClosingTime());
                itemTimeConditionDao.insertItemTimeCondition(timeCondition);
            }
        }
        else{
            List<ItemTimeCondition> itemTimeConditionList;
            if(itemCode.equals("KOSPI")||itemCode.equals("KPI200")||itemCode.equals("KOSDAQ"))
                itemTimeConditionList= getTimeData_k(document, itemCode);
            else itemTimeConditionList= getTimeData(document, itemCode);
            LocalTime recent= itemTimeConditionDao.getNewCondition(itemCode).getClosingTime();
            for(ItemTimeCondition timeCondition : itemTimeConditionList){
                if(recent!=null && recent.equals(timeCondition.getClosingTime()))return false;
                //System.out.println(timeCondition.getClosingTime());
                itemTimeConditionDao.insertItemTimeCondition(timeCondition);
            }
        }

        return true;
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

    private List<ItemTimeCondition> getTimeData_k(Document document, String itemcode){
        List<ItemTimeCondition> itemTimeConditionList = new ArrayList<>();
        Elements rows = document.select(".type_1 tbody tr");

        for(Element row : rows){
            Elements tds = row.select("td");
            if(tds.size()<2) continue;
            String time = tds.get(0).text();
            if(time.isEmpty())break;
            String execution_price = tds.get(1).text();
            String volume = tds.get(5).text();

            LocalTime localTime = LocalTime.parse(time, DateTimeFormatter.ISO_TIME);

            execution_price = convertPrice(execution_price);
            volume = convertPrice(volume);

            ItemTimeCondition itemTimeCondition = ItemTimeCondition.builder()
                    .itemCode(itemcode)
                    .closingTime(localTime)
                    .executionPrice(Float.parseFloat(execution_price))
                    .volume(Integer.parseInt(volume))
                    .build();

            itemTimeConditionList.add(itemTimeCondition);
        }
        return itemTimeConditionList;
    }
    
}
