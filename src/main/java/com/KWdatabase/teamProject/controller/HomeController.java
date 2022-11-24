package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Service.HomeService;
import com.KWdatabase.teamProject.dto.ItemInfoResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HomeController {

    private final HomeService homeService;


    //Test Method
    @GetMapping("/test/rank")
    public ResponseEntity<List<ItemInfoResponseDto>> rankTest(){
        List<ItemInfoResponseDto> list = homeService.getVolumeRank();
        
        return ResponseEntity.ok().body(list);
    }
}
