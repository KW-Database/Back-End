package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Model.BuyRequestDto;
import com.KWdatabase.teamProject.Service.ExchangeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/exchange")
public class ExchangeController {
    private final ExchangeService exchangeService;
    @PostMapping("/buy")
    public ResponseEntity<HttpStatus> buy(@RequestBody BuyRequestDto buyRequestDto){
        exchangeService.buy(buyRequestDto);
        return ResponseEntity.ok().body(HttpStatus.OK);
    }
}
