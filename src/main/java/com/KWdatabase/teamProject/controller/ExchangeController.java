package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.*;
import com.KWdatabase.teamProject.Service.*;
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
    private final HolderAgeService holderAgeService;
    private final MyWalletService myWalletService;
    @PostMapping("/buy")
    public ResponseEntity<HttpStatus> buy(@RequestBody BuyRequestDto buyRequestDto){
        exchangeService.buy(buyRequestDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> info(@RequestBody Map<String, Object> json ){
        String itemCode = json.get("itemCode").toString();
        String id = json.get("id").toString();

        Company company= companyDao.getCompany(itemCode);
        Map<String,Object> map = new HashMap<>();
        map.put("companyInfo", company);
        List<ItemDayCondition> itemDayConditionList = itemDayConditionService.getItemDayCondition(itemCode);
        map.put("dayCondition", itemDayConditionList);
        List<HolderAge> holderAgeList = holderAgeService.getData(itemCode);
        map.put("holderAge", holderAgeList);
        MyWalletResponseDto myWalletResponseDto = myWalletService.getMyWallet(id);
        map.put("myWalletInfo", myWalletResponseDto);
        return ResponseEntity.ok().body(map);
    }


}
