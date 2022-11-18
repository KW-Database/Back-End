package com.KWdatabase.teamProject.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MyWalletResponseDto {
    private int totalHoldings;//총 보유금액
    private int totalAppraisal;//총 평가 금액
    private int totalPurchase;//총 매수금액
    private int cash;//보유 현금
}
