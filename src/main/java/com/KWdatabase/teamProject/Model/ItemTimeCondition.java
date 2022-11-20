package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class ItemTimeCondition {
    String itemCode;
    LocalTime closingTime;
    float executionPrice;
    int volume;
}
