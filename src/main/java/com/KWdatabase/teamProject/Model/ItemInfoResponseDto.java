package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ItemInfoResponseDto {
    private String itemCode;
    private String itemName;
    private int volume;
    private float changeAmount;
    private float changeRate;
    private float executionPrice;
}
