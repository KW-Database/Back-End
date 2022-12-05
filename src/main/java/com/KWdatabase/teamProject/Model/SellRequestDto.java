package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SellRequestDto {
    private String id;
    private String itemCode;
    private long itemNumber;
    private float price;
}
