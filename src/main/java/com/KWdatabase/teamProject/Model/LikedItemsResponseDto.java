package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class LikedItemsResponseDto {
    private String itemCode;
    private String itemName;
    private float price;
    private float changeAmount;
    private float changeRate;
    private int likedNum;

}
