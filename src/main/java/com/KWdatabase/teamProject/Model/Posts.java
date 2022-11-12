package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Posts {
    private int post_id;
    private String ID;
    private String title;
    private String contents;
    private LocalDateTime post_time;
    private int hit_count;
}
