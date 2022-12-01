package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Builder
public class Company {
    private String itemCode;
    private String companyName;
    private long itemNumber;
    private String companySummary;
    private LocalDate publicDate;
}
