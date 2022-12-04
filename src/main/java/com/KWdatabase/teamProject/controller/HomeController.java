package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import com.KWdatabase.teamProject.Service.HomeService;
import com.KWdatabase.teamProject.Model.ItemInfoResponseDto;
import com.KWdatabase.teamProject.Service.ItemDayConditionService;
import com.KWdatabase.teamProject.Service.ItemTimeConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;
    private final ItemDayConditionService itemDayConditionService;
    private final ItemTimeConditionService itemTimeConditionService;

    //Test Method
    @GetMapping("/test/rank")
    public ResponseEntity<Map<String,Object>> rankTest(){
        Map<String,Object> rank = new HashMap<>();
        rank.put("volumeRank",homeService.getVolumeRank());
        rank.put("upRank",homeService.getUpRank());
        rank.put("downRank",homeService.getDownRank());
        List<ItemDayCondition> itemDayConditionList_KOSPI = itemDayConditionService.getItemDayCondition("KOSPI");
        List<ItemDayCondition> itemDayConditionList_KOSPI200 = itemDayConditionService.getItemDayCondition("KOSPI200");
        List<ItemDayCondition> itemDayConditionList_KOSDAQ = itemDayConditionService.getItemDayCondition("KOSDAQ");

        List<ItemTimeCondition> itemTimeConditionList_KOSPI = itemTimeConditionService.getItemTimeCondition("KOSPI");
        List<ItemTimeCondition> itemTimeConditionList_KOSPI200 = itemTimeConditionService.getItemTimeCondition("KOSPI200");
        List<ItemTimeCondition> itemTimeConditionList_KOSDAQ = itemTimeConditionService.getItemTimeCondition("KOSDAQ");

        rank.put("daysise_KOSPI",itemDayConditionList_KOSPI);
        rank.put("daysise_KOSPI200",itemDayConditionList_KOSPI200);
        rank.put("daysise_KOSDAQ",itemDayConditionList_KOSDAQ);

        rank.put("timesise_KOSPI", itemTimeConditionList_KOSPI);
        rank.put("timesise_KOSPI200", itemTimeConditionList_KOSPI200);
        rank.put("timesise_KOSDAQ", itemTimeConditionList_KOSDAQ);

        return ResponseEntity.ok().body(rank);
    }
}
