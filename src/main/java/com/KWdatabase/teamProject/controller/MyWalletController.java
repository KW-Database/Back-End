package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Service.MyWalletService;
import com.KWdatabase.teamProject.dto.MyWalletResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MyWalletController {

    private final MyWalletService myWalletService;

    @GetMapping("/myWallet")
    public ResponseEntity<MyWalletResponseDto> myWallet(@RequestParam("id") String id){
        MyWalletResponseDto myWalletResponseDto = myWalletService.getMyWallet(id);
        return ResponseEntity.ok(myWalletResponseDto);
    }
}
