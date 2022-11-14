package com.KWdatabase.teamProject.Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class holdings {
    private String itemCode;
    private String ID;
    private int numItem;//주식수
    private float avgBidPrice;//평균구매가
}
