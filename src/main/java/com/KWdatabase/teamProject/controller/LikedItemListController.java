package com.KWdatabase.teamProject.controller;

import com.KWdatabase.teamProject.Service.LikedItemListService;
import com.KWdatabase.teamProject.Model.LikedItemsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LikedItemListController {

    private final LikedItemListService likedItemListService;

    @GetMapping("/user/likedItem")
    public ResponseEntity<List<LikedItemsResponseDto>> viewLikedItems(@RequestParam("id") String id){
        List<LikedItemsResponseDto> list = likedItemListService.getListInfo(id);
        return ResponseEntity.ok().body(list);
    }

    @DeleteMapping("/user/likedItem")
    public ResponseEntity<List<LikedItemsResponseDto>> deleteLikedItems(@RequestParam("id") String id, @RequestParam("itemCode") String itemCode){
        likedItemListService.deleteLikedItem(id, itemCode);
        List<LikedItemsResponseDto> list =likedItemListService.getListInfo(id);
        return ResponseEntity.ok().body(list);
    }
}
