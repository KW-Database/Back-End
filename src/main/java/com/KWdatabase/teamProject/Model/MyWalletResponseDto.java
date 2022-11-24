package com.KWdatabase.teamProject.Model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyWalletResponseDto {
    private long totalHoldings;//총 보유금액
    private long totalAppraisal;//총 평가 금액
    private long totalPurchase;//총 매수금액
    private float totalRate;//총 수익률
    private long cash;//보유 현금
    private List<RatePerCompany> RatePerCompany;
}
