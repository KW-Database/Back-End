package com.KWdatabase.teamProject.Model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class Posts {
    private int postId;
    private String id;
    private String title;
    private String contents;
    private LocalDateTime postTime;
    private int hitCount;

    public Posts(){}
    public Posts(int postId, String id,String title,String contents,LocalDateTime postTime,int hitCount){
        this.postId=postId;
        this.id=id;
        this.title= title;
        this.contents= contents;
        this.postTime=postTime;
        this.hitCount=hitCount;
    }
}
