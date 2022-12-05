package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BuyRequestDto {
    private String id;
    private String itemCode;
    private long itemNumber;
    private float price;
}
