package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ItemCondition {
    private String itemCode;
    private LocalDateTime present;
    private float startPrice;
    private float endPrice;
    private float highestPrice;
    private float lowestPrice;
    private int volume;
}
