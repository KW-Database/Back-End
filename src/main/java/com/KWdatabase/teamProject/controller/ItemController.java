package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.ItemDayCondition;
import com.KWdatabase.teamProject.Model.ItemTimeCondition;
import com.KWdatabase.teamProject.Model.User;
import com.KWdatabase.teamProject.Service.ItemDayConditionService;
import com.KWdatabase.teamProject.Service.ItemTimeConditionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemDayConditionService itemDayConditionService;
    private final ItemTimeConditionService itemTimeConditionService;

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

    @GetMapping("/{id}/updateTime")
    public void updateTimeSise(@PathVariable String id) throws IOException {
        itemTimeConditionService.pageCrawling(id);
    }

    @GetMapping("/{id}/updateDay")
    public void updateDaySise(@PathVariable String id) throws IOException {
        itemDayConditionService.pageCrawling(id);
    }
}
