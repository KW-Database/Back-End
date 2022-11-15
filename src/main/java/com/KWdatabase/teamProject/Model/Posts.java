package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class Posts {
    private int postId;
    private String id;
    private String title;
    private String contents;
    private LocalDateTime postTime;
    private int hitCount;
}
