package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Service.LikedItemListService;
import com.KWdatabase.teamProject.dto.LikedItemsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class LikedItemListController {

    private final LikedItemListService likedItemListService;

    @GetMapping("/user/likedItem")
    public ResponseEntity<List<LikedItemsResponseDto>> viewLikedItems(@RequestParam("id") String id){
        List<LikedItemsResponseDto> list = likedItemListService.getListInfo(id);
        return ResponseEntity.ok().body(list);
    }
}
