package com.KWdatabase.teamProject.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Builder
public class LikedItemsResponseDto {
    private String itemName;
    private float price;
    private float changeAmount;
    private float changeRate;
    private int likedNum;

}
