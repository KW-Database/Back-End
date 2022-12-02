package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class Shareholder {
    private String itemCode;
    private String majorHolders;
    private int itemNumber;
    private double shares;
}
