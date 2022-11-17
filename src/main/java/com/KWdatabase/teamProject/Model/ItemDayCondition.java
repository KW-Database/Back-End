package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ItemDayCondition {
    private String itemCode;
    private LocalDate present;
    private float startPrice;
    private float endPrice;
    private float highestPrice;
    private float lowestPrice;
    private int volume;
}
