package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class company {
    private String itemCode;
    private String companyName;
    private int numItem;
    private String summary;
    private LocalDateTime publicDate;
}
