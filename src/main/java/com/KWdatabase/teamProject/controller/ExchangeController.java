package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.BuyRequestDto;
import com.KWdatabase.teamProject.Model.Company;
import com.KWdatabase.teamProject.Model.ItemDayCondition;
import com.KWdatabase.teamProject.Service.CompanyService;
import com.KWdatabase.teamProject.Service.ExchangeService;
import com.KWdatabase.teamProject.Service.ItemDayConditionService;
import com.KWdatabase.teamProject.Service.ItemTimeConditionService;
import com.KWdatabase.teamProject.dao.CompanyDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;
    private final CompanyDao companyDao;
    private final ItemDayConditionService itemDayConditionService;
    @PostMapping("/buy")
    public ResponseEntity<HttpStatus> buy(@RequestBody BuyRequestDto buyRequestDto){
        exchangeService.buy(buyRequestDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> companyInfo(@RequestParam String itemCode){
        Company company= companyDao.getCompany(itemCode);
        Map<String,Object> map = new HashMap<>();
        map.put("Company_info", company);
        List<ItemDayCondition> itemDayConditionList = itemDayConditionService.getItemDayCondition(itemCode);
        map.put("DayCondition", itemDayConditionList);
        return ResponseEntity.ok().body(map);
    }


}
