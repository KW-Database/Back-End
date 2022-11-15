package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ItemCondition {
    private String iCode;
    private LocalDateTime pDate;
    private float sPrice;
    private float ePrice;
    private float hPrice;
    private float lPrice;
    private int volume;
}
