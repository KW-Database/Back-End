package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class PublicDateRankDto {

    private String itemName;
    private float executionPrice;
    private float changeAmount;
    private float changeRate;
    private LocalDate publicDate;
}
