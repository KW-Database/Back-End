package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Holdings {
    private String itemCode;
    private String id;
    private int itemNumber;
    private float avgPrice;
}
