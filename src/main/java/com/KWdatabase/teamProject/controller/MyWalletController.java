package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.dto.MyWalletResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyWalletController {

    @GetMapping("/myWallet")
    public ResponseEntity<MyWalletResponseDto> myWallet(@RequestParam("id") String id){
        return null;
    }
}
