package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Service.HomeService;
import com.KWdatabase.teamProject.Model.ItemInfoResponseDto;
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


    //Test Method
    @GetMapping("/test/rank")
    public ResponseEntity<Map<String,Object>> rankTest(){
        Map<String,Object> rank = new HashMap<>();
        rank.put("volumeRank",homeService.getVolumeRank());
        rank.put("upRank",homeService.getUpRank());
        rank.put("downRank",homeService.getDownRank());
        rank.put("publicList",homeService.getPublicDateList());
        return ResponseEntity.ok().body(rank);
    }
}
