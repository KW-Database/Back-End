package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MACD {
    private String itemCode;
    private String itemName;
    private int volume;
    private float changeAmount;
    private float changeRate;
    private float executionPrice;
}
