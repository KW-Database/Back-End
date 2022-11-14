package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class itemCondition {
    private String itemCode;
    private LocalDateTime purchaseDate;
    private float startPrice;
    private float endPrice;
    private float highestPrice;
    private float lowestPrice;
    private int volume;
}
