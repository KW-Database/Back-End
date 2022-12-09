package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.*;
import com.KWdatabase.teamProject.Service.*;
import com.KWdatabase.teamProject.dao.CompanyDao;
import com.KWdatabase.teamProject.dao.HoldingsDao;
import com.KWdatabase.teamProject.dao.ItemTimeConditionDao;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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
    private final HoldingsDao holdingsDao;
    private final ChatService chatService;
    private final ItemTimeConditionService itemTimeConditionService;
    @PostMapping("/buy")
    public ResponseEntity<HttpStatus> buy(@RequestBody BuyRequestDto buyRequestDto){
        if(exchangeService.buy(buyRequestDto) == false) return null;
        return ResponseEntity.ok().body(HttpStatus.OK);
    }

    @PostMapping("/sell")
    public ResponseEntity<HttpStatus> sell(@RequestBody SellRequestDto sellRequestDto) {
        if (exchangeService.sell(sellRequestDto) == false) return null;
        Holdings holdings = holdingsDao.findHolding(sellRequestDto.getId(),sellRequestDto.getItemCode());
        System.out.println(holdings.getItemNumber());
        if(holdings.getItemNumber()==0) holdingsDao.deleteHoldings(sellRequestDto.getId(),sellRequestDto.getItemCode());
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
    @GetMapping
    public ResponseEntity<Map<String,Object>> info(@RequestParam("id") String id, @RequestParam("itemCode") String itemCode){

        Company company= companyDao.getCompany(itemCode);
        Map<String,Object> map = new HashMap<>();
        map.put("companyInfo", company);
        List<ItemDayCondition> itemDayConditionList = itemDayConditionService.getItemDayCondition(itemCode);
        map.put("dayCondition", itemDayConditionList);
        List<HolderAge> holderAgeList = holderAgeService.getData(itemCode);
        map.put("holderAge", holderAgeList);
        MyWalletResponseDto myWalletResponseDto = myWalletService.getMyWallet(id);
        map.put("myWalletInfo", myWalletResponseDto);
        List<Holdings> holdings = holdingsDao.getHoldings(id);
        map.put("holdings", holdings);
        float curPrice = itemTimeConditionService.getCurPrice(itemCode);
        map.put("curPrice", curPrice);

        return ResponseEntity.ok().body(map);
    }

    @GetMapping("/renewChat")
    public ResponseEntity<List<Chat>> renewChat(@RequestParam String itemCode){
        //String itemCode= json.get("itemCode").toString();
        List<Chat> chatList = chatService.getChatList(itemCode);
        return ResponseEntity.ok().body(chatList);
    }

    @PostMapping("/chat")
    public ResponseEntity<HttpStatus> chat(@RequestBody Chat chat){
        chat.setPostTime(LocalDateTime.now());
        chatService.insertChat(chat);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
