package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.*;
import com.KWdatabase.teamProject.Service.HolderAgeService;
import com.KWdatabase.teamProject.Service.ItemDayConditionService;
import com.KWdatabase.teamProject.Service.ItemTimeConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
public class ItemController {

    private final ItemDayConditionService itemDayConditionService;
    private final ItemTimeConditionService itemTimeConditionService;

    private final HolderAgeService holderAgeService;

    @RequestMapping("/item")

    public ResponseEntity<Map<String,Object>> itemTest(@RequestParam("code") String code){
        Map<String,Object> item = new HashMap<>();
        item.put("timeSise", itemTimeConditionService.getItemTimeCondition(code));
        item.put("daySise",itemDayConditionService.getItemDayCondition(code));
        item.put("holderAges", holderAgeService.getData(code));
        return ResponseEntity.ok().body(item);
    }

    @GetMapping("/{id}/timeSise")
    public ResponseEntity<List<ItemTimeCondition>> viewTimeSise(@PathVariable String id){
        List<ItemTimeCondition> itemTimeConditionList = itemTimeConditionService.getItemTimeCondition(id);
        return ResponseEntity.ok().body(itemTimeConditionList);
    }
    @GetMapping("/{id}/daySise")
    public ResponseEntity<List<ItemDayCondition>> viewDaySise(@PathVariable String id){
        List<ItemDayCondition> itemDayConditionList = itemDayConditionService.getItemDayCondition(id);
        return ResponseEntity.ok().body(itemDayConditionList);
    }

    @DeleteMapping("/timeDelete")
    public void deleteTimeSise(){
        itemTimeConditionService.deleteAllData();
    }

}
