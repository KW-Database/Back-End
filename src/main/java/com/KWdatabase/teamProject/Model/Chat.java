package com.KWdatabase.teamProject.Model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor 
public class Chat {
    private String itemCode;
    private String id;
    private String contents;
    private LocalDateTime postTime;
}
