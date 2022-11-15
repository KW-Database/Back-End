package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class Company {
    private String iCode;
    private String cName;
    private int nItem;
    private String summary;
    private LocalDateTime public_date;
}
