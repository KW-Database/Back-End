package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Chat {
    private String item_code;
    private String id;
    private String contents;
    private LocalDateTime postTime;
}
