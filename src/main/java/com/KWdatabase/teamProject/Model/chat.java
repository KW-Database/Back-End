package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class chat {
    private String iCode;
    private String ID;
    private String contents;
    private LocalDateTime post_time;
}
